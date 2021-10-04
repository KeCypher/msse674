/**
 * 
 */
package com.icaria.model.business;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.icaria.model.domain.User;
import com.icaria.model.domain.exceptions.InvalidUserException;
import com.icaria.model.services.exceptions.ServiceLoadException;
import com.icaria.model.services.userservices.IUserService;

import redis.clients.jedis.Jedis;

/** User Manager Class handles business layer logic relating to user registration, and authentication
 * @author Keanan
 *
 */
public class UserManager extends BusinessManager {
	/**
	 * Private single instace of User Manager follows Fowler's singleton methodology
	 */
	private static UserManager userMgr;
    private static final Logger logger = LogManager.getLogger(UserManager.class);
	
	/**
	 * Singleton constructor pattern
	 */
	private UserManager() {	}

	/**
	 * @return singleton instance of User Manager 
	 */
	public static UserManager getInstance() {
		//Prefer block level synchronization avoiding use of this in case additional non-sync'd code is required.
		synchronized(UserManager.class) {
			if (userMgr == null) {
				userMgr = new UserManager();
			}
		}
		return userMgr;
	}
		
	/**
	 * For this project we only need to register the User
	 * @param user
	 * @return true if successful
	 */
	public static boolean register(User user) {
		boolean retVal = false;
		try {
			IUserService userSvc = (IUserService) getService(IUserService.SERVICE_NAME);
			userSvc.registerUser(user);
			retVal = true;
		} catch (ServiceLoadException | InvalidUserException | IOException e) {
			logger.fatal(e);
		}
		return retVal;		
	}

	
	/**
	 * Pass a User and User ID to authentcate and retrieve existing user information
	 * @param user
	 * @param id - We use the Integer wrapped class because our reflection method dictates it
	 * @return true is user is authenticated
	 */
	public static boolean authenticate(User user) {
		boolean retVal = false;
		try {
			IUserService userSvc = (IUserService) getService(IUserService.SERVICE_NAME);
			int userID = userSvc.getUserID(user.getUsername());
			retVal = userSvc.authenticateUser(user, userID);
		} catch (ServiceLoadException e) {
			e.printStackTrace();
		}
		try (Jedis jedis = new Jedis("host.docker.internal", 6379)) {
			jedis.hset(user.getSessionID(), "username", user.getUsername());
			jedis.hset(user.getSessionID(), "loggedIn", LocalDateTime.now().toString());
		}
		return retVal;		
		
	}

	/**
	 * This manager should only ever be reflecting against itself
	 * And ensure any method called with a different type fails
	 */	
	@Override
	public boolean performAction(final String commandString, Object... args) {
		boolean retVal = false;
		Class<?>[] argClasses = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			argClasses[i] = args[i].getClass();
		}
		try {
			Method method = UserManager.class.getDeclaredMethod(commandString, argClasses);
			retVal = (boolean) method.invoke(userMgr, args);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			retVal = false;
		}
		return retVal;
	}

}