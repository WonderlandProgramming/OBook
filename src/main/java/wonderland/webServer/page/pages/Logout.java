package main.java.wonderland.webServer.page.pages;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.Page;
import spark.Filter;
import spark.Request;
import spark.Response;

public class Logout extends Page {

	public Logout() {
		super("/logout", "", LoginLevel.NotLoggedIn);

		this.addBeforeFilter(new Filter() {
			@Override
			public void handle(Request request, Response response) throws Exception {
				response.removeCookie("OBOOKUID");
				response.redirect("/login");
			}
		});
	}

	@Override
	protected void setupPage(User u) {

	}

	@Override
	protected void onPost(String key, String value, User u) {

	}

	@Override
	protected void onPostEnd(Response response, User u) {

	}

}
