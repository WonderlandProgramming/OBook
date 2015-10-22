package main.java.wonderland.webServer.page.pages.orders;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;
import spark.Response;

public class Finish extends BasePage {

	public Finish() {
		super("/orders/finish", "/orders/finish.ftl", LoginLevel.User, "Fertigstellen");
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
