package main.java.wonderland.webServer;

import static spark.Spark.port;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.wonderland.webServer.login.User;

public class WebServer {
	private List<Page> webPages;
	private static HashMap<String, User> userList = new HashMap<>();
	
	public WebServer(){
		port(80);
		webPages = new ArrayList<>();
	}
	
	public void addPage(Page page){
		webPages.add(page);
	}
	

	public static User getUser(String UID){
		return userList.get(UID);
	}
}
