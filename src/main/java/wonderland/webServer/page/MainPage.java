package main.java.wonderland.webServer.page;

import java.util.Map;

import main.java.wonderland.webServer.login.LoginLevel;
import spark.Response;

public class MainPage extends Page {

	public MainPage() {
		super("/", "timeline.ftl", LoginLevel.All);
	}

	@Override
	protected void onPost(String key, String value){
	}

	@Override
	protected void onPostEnd(Response response) {
	}
	
	@Override
	protected void setupPage(Map<String, Object> map) {
		map.put("pageTitle", "MainPage");
	}
}
