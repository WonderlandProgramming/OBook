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
import main.java.wonderland.webServer.page.pages.Dashboard;
import main.java.wonderland.webServer.page.pages.Login;
import main.java.wonderland.webServer.page.pages.Logout;
import main.java.wonderland.webServer.page.pages.SelfUserConfig;
import main.java.wonderland.webServer.page.pages.Statistic;
import main.java.wonderland.webServer.page.pages.TestPage;
import main.java.wonderland.webServer.page.pages.communication.Chats;
import main.java.wonderland.webServer.page.pages.communication.Log;
import main.java.wonderland.webServer.page.pages.management.Config;
import main.java.wonderland.webServer.page.pages.management.UserConfig;
import main.java.wonderland.webServer.page.pages.mobile.MobileTestPage;
import main.java.wonderland.webServer.page.pages.orders.Create;
import main.java.wonderland.webServer.page.pages.orders.Edit;
import main.java.wonderland.webServer.page.pages.orders.Finish;
import main.java.wonderland.webServer.page.pages.orders.Process;

public class WebServer {
	private List<Page> webPages;
	private static HashMap<String, User> userList = new HashMap<>();

	public WebServer() {
		port(getHerokuAssignedPort());
		staticFileLocation("/public");
		webPages = new ArrayList<>();

		//Redirects to the Index if just the ip is called
		get("/", (req, res) -> {
			res.redirect("/dashboard");
			return "";
		});
		init();
		initUsers();
	}
	
	static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 80; //return default port if heroku-port isn't set (i.e. on localhost)
    }
	
	private void initUsers(){
		addUser(new User("admin", "admin"), LoginLevel.Administrator);
		addUser(new User("User", "User"), LoginLevel.User);
		addUser(new User("LPeer", "admin"), LoginLevel.SuperAdministrator);
	}
	
	public static List<User> getUser(){
		List<User> rt = new ArrayList<>();
			
		for (User u : userList.values()) {
			rt.add(u);
		}
		
		return rt;
	}
	
	private void init(){
		
		addPage(new Login());
		addPage(new Logout());
		addPage(new Dashboard());
		addPage(new SelfUserConfig());
		
		//Order Pages
		addPage(new Create());
		addPage(new Edit());
		addPage(new Finish());
		addPage(new Process());
		
		//Libary Pages
		addPage(new main.java.wonderland.webServer.page.pages.libary.Create());
		addPage(new main.java.wonderland.webServer.page.pages.libary.Edit());
		
		//Communication Pages
		addPage(new Chats());
		addPage(new Log());
		
		//Mangement
		addPage(new Config());
		addPage(new UserConfig());
		
		//Statistic
		addPage(new Statistic());
		
		//Testpage
		addPage(new TestPage());
		addPage(new MobileTestPage());
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
