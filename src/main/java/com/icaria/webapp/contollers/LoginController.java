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
 * 
 */

/**
 * @author Keanan Cypher
 * 
 * This basic login controller it the initial step in rebuilding the Daedalus Flight Reservation into the Icaria Travel Reservation System.
 *
 */
public class LoginController extends HttpServlet {

	 private static final long serialVersionUID = 1L;

	 
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
    	 out.println(" Login Controller Get Executed");
    	 out.flush();
    	 out.close();
	 }
	 
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException  {
		 User user = new User();
		 user.setSessionID(request.getParameter("sessionid"));
		 user.setUsername(request.getParameter("username"));
		 user.setPassword(request.getParameter("password"));
		 PrintWriter out = response.getWriter();
    	 out.println("Login Controller Post Executed, You Submitted the Following User:");
    	 out.println(user.toString());
	   	 UserManager manager = UserManager.getInstance();
		 String loginResult = (manager.performAction("authenticate", user)) ? "Successful" : "Unsuccessful";
    	 out.println("With this information, Login was " + loginResult);
    	 out.flush();
    	 out.close();
		 
	 }
	/**
	 * 
	 */
	public LoginController() {
		// TODO Auto-generated constructor stub
	}

}
