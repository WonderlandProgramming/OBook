package main.java.wonderland.webServer.page;

import java.util.Map;

import main.java.wonderland.webServer.Page;
import main.java.wonderland.webServer.login.LoginLevel;
import spark.Response;

public class ConfigPage extends Page {

	public ConfigPage() {
		super("/config", "masterTemplate.ftl", LoginLevel.Moderator);
	}

	@Override
	protected void onPost(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onPostEnd(Response response) {
		
	}

	@Override
	protected void setupPage(Map<String, Object> map) {
		map.put("pageTitle", "Public Timeline");
	}

}
