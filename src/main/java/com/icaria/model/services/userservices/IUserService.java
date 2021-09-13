/**
 * 
 */
package com.icaria.model.services.userservices;

import java.io.IOException;

import com.icaria.model.domain.User;
import com.icaria.model.domain.exceptions.InvalidUserException;
import com.icaria.model.services.factory.IService;


/**
 * @author Keanan
 *
 */
public interface IUserService extends IService {
	public final String SERVICE_NAME = "IUserSvc";
	
	public boolean authenticateUser(User user, int userID);
	public User registerUser(User user) throws InvalidUserException, IOException;
	public void updateUser(User user) throws InvalidUserException;	
	public User retrieveUser(int userid);
	public int getUserID(String username);
	public boolean deleteUser(int userID);
	
}
