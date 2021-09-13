package com.icaria.webapp.contollers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icaria.model.business.UserManager;
import com.icaria.model.domain.User;

/**
 * @author Keanan Cypher
 * 
 * This basic registration controller handles user reservation in the Icaria Travel System
 *
 */
public class RegistrationController extends HttpServlet{
	 private static final long serialVersionUID = 1L;

	 //These get calls should never realistically be called, but they are very useful for troubleshooting
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
	   	 out.println(" Registration Controller Get Executed");
	   	 out.flush();
	   	 out.close();
	 }
	 
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException  {
		 PrintWriter out = response.getWriter();
		 User user = new User();
		 user.setSessionID(request.getParameter("sessionid"));
		 user.setUsername(request.getParameter("username"));
		 user.setFirstname(request.getParameter("firstName"));
		 user.setLastname(request.getParameter("lastName"));
		 user.setPassword(request.getParameter("password"));
		 boolean confirm = request.getParameter("password2").equals(user.getPassword());
		 boolean success = user.validate();
	   	 out.println("Registration Controller Post Executed");
	   	 out.println("You are registering the following user information:");
	   	 out.println(user.toString());
	   	 out.println("This user is " + ((success) ? "valid" : "not valid"));
	   	 out.println("Additionally your password and confirmation " + ((confirm) ? "match!" : "don't match."));
	   	 out.println("This means your registration would " + ((success && confirm) ? "succeed" : "fail"));
	   	 out.println("Persisting user...");
	   	 UserManager manager = UserManager.getInstance();
	   	 if (manager.performAction("register", user)) {
	   		out.println("Persistence Successful");
	   	 } else {
	   		 out.println("Persistence failed");
	   	 }
	   	 out.flush();
	   	 out.close();
	 }
	 
	/**
	 * 
	 */
	public RegistrationController() {
		// TODO Auto-generated constructor stub
	}

}
