package main.java.wonderland.webServer.page.pages.mobile;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;
import spark.Response;

public class MobileTestPage extends BasePage {

	public MobileTestPage() {
		super("/mobile", "/mobile/test.ftl", LoginLevel.User, "Mobile Test");
	}

	@Override
	protected void setupSpecialPage(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onPost(String key, String value, User u) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onPostEnd(Response response, User u) {
		// TODO Auto-generated method stub

	}

}
