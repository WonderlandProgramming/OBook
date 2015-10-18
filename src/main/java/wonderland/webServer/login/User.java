package main.java.wonderland.webServer.login;

import java.util.HashMap;
import java.util.Map;

import main.java.wonderland.webServer.HTMLUtils;
import main.java.wonderland.webServer.WebServer;
import main.java.wonderland.webServer.page.IWebElement;

public class User implements IWebElement{

	private String userID;
	private String username;
	private String password;

	private LoginLevel loginLevel;

	private Map<String, Object> pageConfigurationList;

	public User(String username, String password) {
		String userID = HTMLUtils.generateUserID();

		while (WebServer.getUser(userID) != null)
			userID = HTMLUtils.generateUserID();

		this.userID = userID;

		this.username = username;
		this.password = PasswordUtil.hashPassword(password);
		this.loginLevel = LoginLevel.All;

		this.pageConfigurationList = new HashMap<>();
	}

	public User() {
		this.username = "SHORTTERM USER";
		String userID = HTMLUtils.generateUserID();
		while (WebServer.getUser(userID) != null)
			userID = HTMLUtils.generateUserID();
		this.userID = userID;
		this.password = PasswordUtil.hashPassword("password");
		this.loginLevel = LoginLevel.NotLoggedIn;
		this.pageConfigurationList = new HashMap<>();
	}

	public boolean isPassword(String compareTo) {
		return PasswordUtil.verifyPassword(compareTo, password);
	}

	public boolean isUsername(String compareTo) {
		return username.equals(compareTo);
	}

	public String getUsername() {
		return username;
	}

	public void setLoginLevel(LoginLevel newValue) {
		this.loginLevel = newValue;
	}

	public String getUserID() {
		return userID;
	}

	public LoginLevel getLoginLevel() {
		return loginLevel;
	}

	public boolean isValid() {
		if (username == null || username.isEmpty()) {
			return false;
		}
		if (password == null || password.isEmpty()) {
			return false;
		}
		return true;
	}

	public Map<String, Object> getPageConfiguration() {
		return pageConfigurationList;
	}

	public void clearCurrentConfiguration() {
		String page = (String) pageConfigurationList.get("§PAGEURL");
		pageConfigurationList.clear();
		pageConfigurationList.put("§PAGEURL", page);
	}

	public void clearSubConfiguration(String index) {
		pageConfigurationList.put(index, null);
	}

	public void setCurrentPage(String page) {
		if (!page.equals(pageConfigurationList.get("§PAGEURL"))) {
			pageConfigurationList.clear();
			pageConfigurationList.put("§PAGEURL", page);
			this.pageConfigurationList.put("§PAGEORIGINAL", "true");
		}
	}

	public boolean isCurrentPage(String page) {
		return page.equals(this.pageConfigurationList.get("§PAGEURL"));
	}

	public boolean needsOriginalPage() {
		return this.pageConfigurationList.get("§PAGEORIGINAL").equals("true");
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", passwordHash=" + password + ", loginLevel="
				+ loginLevel + ", pageConfigurationList=" + pageConfigurationList + "]";
	}

	/**
	 * USE WITH CARE!
	 * 
	 * @param map
	 */
	public void setPageConfiguration(Map<String, Object> map) {
		this.pageConfigurationList = map;
	}
	
	public void diableOriginal(){
		this.pageConfigurationList.put("§PAGEORIGINAL", "false");
	}

	public void activeOriginal() {
		this.pageConfigurationList.put("§PAGEORIGINAL", "true");
	}

	@Override
	public Object getAsWebElement(LoginLevel level) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("username", username);
		map.put("password", password);
		map.put("userID", userID);
		map.put("loginlevel", loginLevel.toString());
		
		return map;
	}
}
