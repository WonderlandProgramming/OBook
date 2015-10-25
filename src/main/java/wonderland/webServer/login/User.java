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

	private LoginLevel loginlevel;

	private Map<String, Object> pageConfigurationList;

	public User(String username, String password) {
		String userID = HTMLUtils.generateUserID();

		while (WebServer.getUser(userID) != null)
			userID = HTMLUtils.generateUserID();

		this.userID = userID;

		this.username = username;
		this.password = PasswordUtil.hashPassword(password);
		this.loginlevel = LoginLevel.All;

		this.pageConfigurationList = new HashMap<>();
	}

	public User(String username, String password, String UID, LoginLevel loginLevel) {
		this.userID = UID;
		this.username = username;
		this.password = password;
		this.loginlevel = loginLevel;

		this.pageConfigurationList = new HashMap<>();
	}

	public User() {
		this.username = "SHORTTERM USER";
		String userID = HTMLUtils.generateUserID();
		while (WebServer.getUser(userID) != null)
			userID = HTMLUtils.generateUserID();
		this.userID = userID;
		this.password = PasswordUtil.hashPassword("password");
		this.loginlevel = LoginLevel.NotLoggedIn;
		this.pageConfigurationList = new HashMap<>();
	}

	public boolean isPassword(String compareTo) {
		return PasswordUtil.verifyPassword(compareTo, password);
	}

	public boolean isUsername(String compareTo) {
		return username.equals(compareTo);
	}

	public void updatePassword(String passwordToHash) {
		this.password = PasswordUtil.hashPassword(passwordToHash);
	}

	public String getUsername() {
		return username;
	}

	public void setLoginLevel(LoginLevel newValue) {
		this.loginlevel = newValue;
	}

	public String getUserID() {
		return userID;
	}

	public LoginLevel getLoginLevel() {
		return loginlevel;
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

	//TODO change it so there is safe storage area for transmission between different subpages!
	public void setCurrentPage(String page) {
		if (!page.equals(pageConfigurationList.get("§PAGEURL"))) {
			Object pagewide = pageConfigurationList.get("pagewide");
			pageConfigurationList.clear();
			pageConfigurationList.put("pagewide", pagewide);
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
				+ loginlevel + ", pageConfigurationList=" + pageConfigurationList + "]";
	}

	/**
	 * USE WITH CARE!
	 * 
	 * @param map
	 */
	public void setPageConfiguration(Map<String, Object> map) {
		this.pageConfigurationList = map;
	}

	public void diableOriginal() {
		this.pageConfigurationList.put("§PAGEORIGINAL", "false");
	}

	public void activeOriginal() {
		this.pageConfigurationList.put("§PAGEORIGINAL", "true");
	}

	@Override
	public Object getAsWebElement(LoginLevel level) {
		HashMap<String, Object> userdata = new HashMap<>();
		userdata.put("username", username);
		userdata.put("loginlevel", loginlevel.name());
		userdata.put("userID", userID);
		return userdata;
	}
}
