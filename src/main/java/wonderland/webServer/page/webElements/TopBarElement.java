package main.java.wonderland.webServer.page.webElements;

import java.util.HashMap;
import java.util.Map;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.page.IWebElement;

public class TopBarElement implements IWebElement{

	private String topMessage, message;
	private int time;
	private LoginLevel seeLevel;

	public TopBarElement(String topMessage, String message, int time, LoginLevel seeLevel) {
		this.topMessage = topMessage;
		this.message = message;
		this.time = time;
		this.seeLevel = seeLevel;
	}

	@Override
	public Object getAsWebElement(LoginLevel level) {
		if (level.getLevel() < this.seeLevel.getLevel())
			return null;
		
		Map<String, Object> element = new HashMap<>();
		element.put("topMessage", topMessage);
		element.put("time", time);
		element.put("message", message);
		return element;
	}

	public LoginLevel getSeeLevel() {
		return seeLevel;
	}
	
}