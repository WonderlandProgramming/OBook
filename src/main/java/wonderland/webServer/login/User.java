package main.java.wonderland.webServer.login;

import main.java.wonderland.webServer.HTMLUtils;
import main.java.wonderland.webServer.WebServer;

public class User {
	
	private String userID;
	private String username;
	private String password;
	
	private LoginLevel loginLevel;
	
	public User(String username, String password, LoginLevel level){
		String userID = HTMLUtils.generateUserID();
		while(WebServer.getUser(userID) != null)
			userID = HTMLUtils.generateUserID();
		this.username = username;
		this.loginLevel = level;
	}
	
	public boolean isPassword(String compareTo){
		return password == compareTo;
	}
	
	public boolean isUsername(String compareTo){
		return username == compareTo;
	}

	public String getUserID() {
		return userID;
	}

	public LoginLevel getLoginLevel() {
		return loginLevel;
	}
	
}
