package main.java.wonderland.webServer.page.pages.libary;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;
import spark.Response;

public class Create extends BasePage {

	public Create() {
		super("/libary/create", "/libary/create.ftl", LoginLevel.Moderator, "Neu Erstellen");
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
