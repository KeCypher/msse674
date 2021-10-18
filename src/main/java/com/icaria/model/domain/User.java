package com.icaria.model.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Keanan Cypher
 * Base User Class
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6715961829060897458L;
	
	/**
	 * Setting up some static defaults for comparisons
	 * 
	 * Worth noting previous iterations of this project refrained from gathering identifying information until they were in then "traveler," 
	 * since we're rapidly iterating in this course, we'll leave it here to simplify the initial project
	 */
	public static final int DEFAULT_ID = 0;
	public static final String DEFAULT_SESSION_ID = "00000000-0000-0000-0000-000000000000";
	public static final String DEFAULT_FIRSTNAME = "John";
	public static final String DEFAULT_LASTNAME = "Doe";
	public static final String DEFAULT_EMAIL = "Doe";
	public static final String DEFAULT_USERNAME = "Anonymous User";
	public static final String DEFAULT_PASSWORD = "Anonymous User";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userID;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="firstname")
	private String firstname;
	@Column(name="lastname")
	private String lastname;
	@Column(name="email")
	private String email;
	
	/**
	 * We're marking session ID as Transient for now. Ideally it'll be managed via Redis for proper session/cache management
	 * Moreover, eventually Redis will be used to manager a virtual user until they are logged in, which will require its own initialization methodology
	 */
	@Transient
	private String sessionID;
	

	/**
	 * Default constructor will probably never actually be called, but very useful for testing.
	 */
	public User() {
		super();
		this.userID = DEFAULT_ID;
		this.sessionID = DEFAULT_SESSION_ID;
		this.username = User.DEFAULT_USERNAME;
		this.password = User.DEFAULT_PASSWORD;
	}
	
	/**
	 * User construction with Session ID
	 * @param sessionId
	 * 
	 * This allows us to create an anonymous user, but still store their data, eg an itinerary they've picked out prior to logging in. I think this could eventually use the actual Session to create everything, but that's excessive for now.
	 */
	public User(String sessionId) {
		super();
		this.userID = DEFAULT_ID;
		this.sessionID = sessionId;
		this.username = User.DEFAULT_USERNAME;
		this.password = User.DEFAULT_PASSWORD;		
	}
	
	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int newUserID) {
		userID = newUserID;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	//We're leaving these functions around, but commented out as they can be super useful for debugging.
//	public boolean authenticate(User user) {
//		return user.getUsername().equals("Tester") && user.getPassword().equals("Test!");
//	}
//	
//	//Moreover, without a service layer this step is very much smoke and mirrors
//	public boolean register(User user) {
//		return true;
//	}	
	
	
	/**
	 * Validates the User Object
	 * @return true if all non-null values are set
	 */
	public boolean validate() {
		return !Objects.isNull(userID)
				&& !Objects.isNull(username)
				&& !Objects.isNull(lastname)
				&& !Objects.isNull(firstname)
				&& !Objects.isNull(password)
				&& !Objects.isNull(email)
				&& !Objects.isNull(sessionID);			
	}

	/**
	 * We're not including session ID in comparisons because its very possible for two users to be the same using different sessions
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		return Objects.equals(userID, other.userID) 
				&& Objects.equals(username, other.username)
				&& Objects.equals(lastname, other.lastname)
				&& Objects.equals(firstname, other.firstname)
				&& Objects.equals(email, other.email)
				&& Objects.equals(password, other.password);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(
				userID,
				username,
				lastname, 
				firstname,
				password,
				sessionID,
				email
				);
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("(User)@" + this.hashCode())
		.append("\nUserID=" + userID)
		.append("\nusername=" + username)
		.append("\nlastname=" + lastname)
		.append("\nfirstname=" + firstname)
		.append("\nemail=" + email)
		.append("\npassword=" + password)
		.append("\nsessionID=" + sessionID);
		return sb.toString();
	}
}
