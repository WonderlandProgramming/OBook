package main.java.wonderland.webServer.page.pages;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;
import spark.Response;

public class Dashboard extends BasePage {
	
	public Dashboard() {
		super("/dashboard", "dashboard.ftl", LoginLevel.User, "Dashboard");
	}

	@Override
	protected void setupSpecialPage(User u) {
	}

	@Override
	protected void onPost(String key, String value, User u) {
		
	}

	@Override
	protected void onPostEnd(Response response, User u) {
		
	}

}
