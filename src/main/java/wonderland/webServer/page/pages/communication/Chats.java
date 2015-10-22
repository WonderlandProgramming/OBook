package main.java.wonderland.webServer.page.pages.communication;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;
import spark.Response;

public class Chats extends BasePage {

	public Chats() {
		super("/communication/chats", "/communication/chats.ftl", LoginLevel.User, "Chats");
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
