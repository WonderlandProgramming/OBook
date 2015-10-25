package main.java.wonderland.webServer.page.pages.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.java.wonderland.webServer.WebServer;
import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;
import spark.Response;

public class UserConfig extends BasePage {
	
	public UserConfig() {
		super("/management/user", "/management/user.ftl", LoginLevel.Administrator, "Benutzer");
	}

	@Override
	protected void setupSpecialPage(User u) {
		Map<String, Object> map = u.getPageConfiguration();

		List<Object> userData = new ArrayList<>();
		List<User> users = WebServer.getUser();
		
		for (User user : users) {
			userData.add(user.getAsWebElement(null));
		}
		
		map.put("users", userData);
		
		u.setPageConfiguration(map);
	}

	@Override
	protected void onPost(String key, String value, User u) {

	}

	@Override
	protected void onPostEnd(Response response, User u) {

	}

}
