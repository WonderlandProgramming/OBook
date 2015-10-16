package main.java.wonderland.webServer.page;

import main.java.wonderland.webServer.Page;
import main.java.wonderland.webServer.WebServer;
import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import spark.Response;

public class LoginPage extends Page {

	private String pw, lg;

	public LoginPage() {
		super("/login", "res/login.html", LoginLevel.NotLoggedIn);
	}

	@Override
	protected void onPost(String key, String value) {
		if (key.equals("password"))
			pw = value;
		if (key.equals("login"))
			lg = value;
	}

	@Override
	protected void onPostEnd(Response response) {
		User u = WebServer.getUserByName(lg);
		if (u != null && u.isValid() && u.isPassword(pw)) {
			response.cookie("OBOOKUID", u.getUserID(), 18000);
			pw = null;
			lg = null;
			response.redirect("/");
		} else {
			System.out.println("Login not successful for " + lg + " with " + pw);
		}
	}
}
