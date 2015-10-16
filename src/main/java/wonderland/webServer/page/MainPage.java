package main.java.wonderland.webServer.page;

import main.java.wonderland.webServer.Page;
import main.java.wonderland.webServer.login.LoginLevel;
import spark.Response;

public class MainPage extends Page {

	public MainPage() {
		super("/", "res/mainPage.html", LoginLevel.All);
	}

	@Override
	protected void onPost(String key, String value){
		System.out.println(key + " : " + value);
	}

	@Override
	protected void onPostEnd(Response response) {
		//response.redirect("");
	}
}
