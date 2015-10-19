package main.java.wonderland.webServer;

import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;
import static spark.SparkBase.stop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.Page;

public class WebServer {
	private List<Page> webPages;
	private static HashMap<String, User> userList = new HashMap<>();

	public WebServer() {
		port(80);
		staticFileLocation("/public");
		webPages = new ArrayList<>();

		//Redirects to the Index if just the ip is called
		get("/", (req, res) -> {
			res.redirect("/index");
			return "";
		});
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
			if (u.isUsername(name))
				return u;
		}
		return null;
	}

	public static void addUser(User u, LoginLevel accessLevel) {
		u.setLoginLevel(accessLevel);
		userList.put(u.getUserID(), u);
	}
}
