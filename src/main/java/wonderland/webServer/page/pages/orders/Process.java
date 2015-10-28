package main.java.wonderland.webServer.page.pages.orders;

import java.util.Map;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;

public class Process extends BasePage {

	public Process() {
		super("/orders/process", "/orders/process.ftl", LoginLevel.User, "Bearbeiten");
	}

	@Override
	protected void setupSpecialPage(User u) {

	}

	@Override
	protected void onPostComplete(User u, Map<String, Object> returnValues){
		
	}
}
