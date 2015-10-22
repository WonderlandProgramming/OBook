package main.java.wonderland.webServer.page.pages.management;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;
import spark.Response;

public class Config extends BasePage {

	public Config() {
		super("/management/config", "/management/config.ftl", LoginLevel.Administrator, "Konfiguration");
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
