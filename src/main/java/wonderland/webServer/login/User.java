package main.java.wonderland.webServer.login;

import main.java.wonderland.webServer.HTMLUtils;
import main.java.wonderland.webServer.WebServer;

public class User {
	
	private String userID;
	private String username;
	private String password;
	
	private LoginLevel loginLevel;
	
	public User(String username, String password){
		String userID = HTMLUtils.generateUserID();
		
		while(WebServer.getUser(userID) != null)
			userID = HTMLUtils.generateUserID();
		
		this.userID = userID;
		
		this.username = username;
		this.password = PasswordUtil.hashPassword(password);
		this.loginLevel = LoginLevel.All;
	}
	
	public boolean isPassword(String compareTo){
		return PasswordUtil.verifyPassword(compareTo, password);
	}
	
	public boolean isUsername(String compareTo){
		return username.equals(compareTo);
	}
	
	public void setLoginLevel(LoginLevel newValue){
		this.loginLevel = newValue;
	}
	
	public String getUserID() {
		return userID;
	}

	public LoginLevel getLoginLevel() {
		return loginLevel;
	}
	
	public boolean isValid(){
		if(username == null || username.isEmpty()){
			return false;
		}
		if(password == null || password.isEmpty()){
			return false;
		}
		return true;
	}
}
