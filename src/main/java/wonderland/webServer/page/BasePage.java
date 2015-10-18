package main.java.wonderland.webServer.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.webElements.SidebarElement;
import spark.Response;

public class BasePage extends Page {

	private List<SidebarElement> sideBarElements;
	// private List<TopBarElements> topBarElements;

	public BasePage(String subPath, String ftlFile, LoginLevel loginLevel) {
		super(subPath, ftlFile, loginLevel);
		sideBarElements = new ArrayList<>();
	}

	@Override
	protected void setupPage(User u) {
		Map<String, Object> map = u.getPageConfiguration();
		
		map.put("title", "BasePage");
		
		List<Object> addToUserMap = new ArrayList<>();
		for (SidebarElement element : sideBarElements) {
			Object webElement = ((IWebElement) element).getAsWebElement(u.getLoginLevel());
			if (webElement != null)
				addToUserMap.add(webElement);
		}
		map.put("sideBarElements", addToUserMap);
		map.put("user", u.getAsWebElement(null));
		
		u.setPageConfiguration(map);
	}
	
	public void addSideBarElement(SidebarElement element){
		sideBarElements.add(element);
	}
	
	@Override
	protected void onPost(String key, String value, User u) {

	}

	@Override
	protected void onPostEnd(Response response, User u) {

	}

}
