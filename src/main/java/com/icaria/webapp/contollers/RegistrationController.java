package com.icaria.webapp.contollers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icaria.model.business.UserManager;
import com.icaria.model.domain.User;
import com.icaria.webapp.beans.Registration;

/**
 * @author Keanan Cypher
 * 
 * This basic registration controller handles user reservation in the Icaria Travel System
 *
 */
@Controller
@RequestMapping("register")
public class RegistrationController extends HttpServlet{
	 private static final long serialVersionUID = 1L;

	 //These get calls should never realistically be called, but they are very useful for troubleshooting
	    @RequestMapping(method = RequestMethod.GET)
	    public ModelAndView showForm() {
	        return new ModelAndView("register", "registration", new Registration());
	    }

	 @RequestMapping(method = RequestMethod.POST)
	 public String submit( @Valid @ModelAttribute("registration") Registration regreq, 
     BindingResult result, ModelMap model)  {
	    if (result.hasErrors()) {
	        return "errorBind";
	    }
	    String tempID = UUID.randomUUID().toString();
	    model.addAttribute("session", tempID);
	    model.addAttribute("username", regreq.getUsername());
	    model.addAttribute("firstname", regreq.getFirstname());
	    model.addAttribute("lastname", regreq.getLastname());
	    model.addAttribute("password", regreq.getEmail());
	    model.addAttribute("password2", regreq.getPassword());
	    model.addAttribute("email", regreq.getPassword2());
		 User user = new User();
		 user.setSessionID(tempID);
		 user.setUsername(regreq.getUsername());
		 user.setFirstname(regreq.getFirstname());
		 user.setLastname(regreq.getLastname());
		 user.setLastname(regreq.getEmail());
		 user.setPassword(regreq.getPassword());
		 boolean confirm = regreq.getPassword2().equals(user.getPassword());
		 model.addAttribute("confirm", confirm);
	   	 UserManager manager = UserManager.getInstance();
		 boolean success = manager.performAction("register", user);
		 model.addAttribute("success", success);
	   	 if (confirm && success) {
		   	 return "registrationComplete";	   		 
	   	 } else {
	   		 return "errorCode";
	   	 }
	 }
	 
	 public static void main(String[] args) {
		 User user = new User();
		 user.setSessionID("1234567890");
		 user.setUsername("test");
		 user.setFirstname("test");
		 user.setLastname("test");
		 user.setEmail("test");
		 user.setPassword("test");
		 boolean confirm = "test".equals(user.getPassword());
	   	 UserManager manager = UserManager.getInstance();
		 boolean success = manager.performAction("register", user);
	   	 if (confirm && success) {
	   		 System.out.print("Yay");
	   	 } else {
	   		 System.out.print("Boo");
	   	 }
	 }
	 
	/**
	 * 
	 */
	public RegistrationController() {
		// TODO Auto-generated constructor stub
	}

}
