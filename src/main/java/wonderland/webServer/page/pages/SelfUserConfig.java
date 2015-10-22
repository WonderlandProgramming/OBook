package main.java.wonderland.webServer.page.pages;

import java.util.Map;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;
import spark.Response;

public class SelfUserConfig extends BasePage {

	private String oldPassword;
	private String password;
	private String comparePassword;

	public SelfUserConfig() {
		super("/user/self", "userselfconfig.ftl", LoginLevel.User, "Einstellungen");
	}

	@Override
	protected void setupSpecialPage(User u) {
		Map<String, Object> map = u.getPageConfiguration();
		if (oldPassword != null && password != null && comparePassword != null) {
			if (!oldPassword.isEmpty() && !password.isEmpty() && !comparePassword.isEmpty()) {
				if (u.isPassword(oldPassword)) {
					if (password.equals(comparePassword)) {
						u.updatePassword(password);
						map.put("error", "Passwort erfolgreich geändert!");
					} else {
						map.put("error", "Neue Passwoerter stimmen nicht überein!");
					}
				} else {
					map.put("error", "Aktuelles Passwort nicht korrekt!");
				}
			} else {
				map.put("error", "Bitte alle Felder angeben!");
			}
		}

		password = null;
		oldPassword = null;
		comparePassword = null;
		u.setPageConfiguration(map);
	}

	@Override
	protected void onPost(String key, String value, User u) {
		if (key.equals("oldPassword"))
			oldPassword = value;
		if (key.equals("password"))
			password = value;
		if (key.equals("comparePassword"))
			comparePassword = value;
		
	}

	@Override
	protected void onPostEnd(Response response, User u) {
		Map<String, Object> map = u.getPageConfiguration();
		map.remove("error");
		u.setPageConfiguration(map);
	}
}
