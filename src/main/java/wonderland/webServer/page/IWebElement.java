package main.java.wonderland.webServer.page;

import main.java.wonderland.webServer.login.LoginLevel;

public interface IWebElement {
	public Object getAsWebElement(LoginLevel level);
}
