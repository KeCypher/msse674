
package com.icaria.model.services.userservices;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.icaria.model.domain.User;
import com.icaria.model.domain.exceptions.InvalidUserException;
import com.icaria.utils.HibernateUtil;

public class MySQLUserImpl implements IUserService{
	
    private static final Logger logger = LogManager.getLogger("otherlogger");

	@Override
	public boolean authenticateUser(User user, int userID) {
		try {
			String finduser = String.format("FROM User WHERE userID = '%d' AND username = '%s' AND password = '%s'", userID, user.getUsername(), user.getPassword());
			if (HibernateUtil.query(finduser).size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.fatal(e);
			return false;
		}
	}

	@Override
	public User registerUser(User user) throws InvalidUserException, IOException {
		Session session = getSession();
		try {
			session.getTransaction().begin();
			session.persist(user);
		} catch (Exception e) {
			logger.fatal(e);
			throw new InvalidUserException("Hibernate broke");
		} finally {
	        session.getTransaction().commit();			
		}
		return null;
	}

	@Override
	public void updateUser(User user) throws InvalidUserException {
		Session session = getSession();
		try {
			session.getTransaction().begin();
			session.update(user);
	        session.getTransaction().commit();
		} catch (Exception e) {
			logger.fatal(e);
			throw new InvalidUserException("Hibernate broke");
		} finally {
	        session.getTransaction().commit();			
		}
	}

	@Override
	public User retrieveUser(int userid) {
		try {
			String finduser = String.format("FROM User WHERE userID = '%d'", userid);
			User user = (User) HibernateUtil.queryUnique(finduser);
			return user;
		} catch (Exception e) {
			logger.fatal(e);
			return null;
		}
	}

	@Override
	public int getUserID(String username) {
		try {
			String finduser = String.format("SELECT userID FROM User WHERE username = '%s'", username);
			for (Object o : HibernateUtil.query(finduser)) {
				return (int)o;
			};
		} catch (Exception e) {
			logger.fatal(e);
			return 0;
		}
		return 0;
	}

	@Override
	public boolean deleteUser(int userID) {
		User user = retrieveUser(userID);
		Session session = getSession();
		try {
			session.getTransaction().begin();
			session.delete(user);
	        session.getTransaction().commit();
	        return true;
		} catch (Exception e) {
			logger.fatal(e);
			return false;
		} finally {
	        session.getTransaction().commit();			
		}
	}
	
}
