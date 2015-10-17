package main.java.wonderland.webServer.page;

import java.util.Map;

import main.java.wonderland.webServer.WebServer;
import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import spark.Filter;
import spark.Request;
import spark.Response;

public class LoginPage extends Page {

	private String pw, lg;

	private String loginError;

	public LoginPage() {
		super("/login", "login.ftl", LoginLevel.NotLoggedIn);

		this.addBeforeFilter(new Filter() {
			@Override
			public void handle(Request request, Response response) throws Exception {
				String UIDinCookies = request.cookies().get("OBOOKUID");
				if (WebServer.getUser(UIDinCookies) != null) {
					response.redirect("/");
				}
			}
		});
	}

	@Override
	protected void onPost(String key, String value) {
		if (key.equals("password"))
			pw = value;
		if (key.equals("username"))
			lg = value;
	}

	@Override
	protected void onPostEnd(Response response) {
		User u = WebServer.getUserByName(lg);
		if (u != null && u.isValid() && u.isPassword(pw)) {
			response.cookie("OBOOKUID", u.getUserID(), 18000);
			System.out.println("Login successful for " + lg);
			pw = null;
			lg = null;
			response.redirect("/");
		} else {
			if (u == null)
				loginError = "Can not find User!";
			else if (!u.isPassword(pw))
				loginError = "Password is wrong!";

			System.out.println("Login not successful for " + lg + " with " + pw);
			pw = null;
			lg = null;
		}
	}

	@Override
	protected void setupPage(Map<String, Object> map) {
		map.put("pageTitle", "Login");
		map.put("error", loginError);
		
		map.put("username", "admin");
		
		loginError = null;
	}
}
