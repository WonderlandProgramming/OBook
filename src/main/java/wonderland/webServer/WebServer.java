package main.java.wonderland.webServer;

import static spark.SparkBase.externalStaticFileLocation;
import static spark.SparkBase.port;
import static spark.SparkBase.stop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;

public class WebServer {
	private List<Page> webPages;
	private static HashMap<String, User> userList = new HashMap<>();

	public WebServer() {
		port(80);
		externalStaticFileLocation("res");
		webPages = new ArrayList<>();
	}

	public void addPage(Page page) {
		webPages.add(page);
	}

	public void close() {
		stop();
	}

	public static User getUser(String UID) {
		return userList.get(UID);
	}
	
	public static User getUserByName(String name) {
		for (User u : userList.values()) {
			if(u.isUsername(name)) return u;
		}
		return null;
	}
	
	public static void addUser(User u, LoginLevel accessLevel){
		u.setLoginLevel(accessLevel);
		userList.put(u.getUserID(), u);
	}
}
