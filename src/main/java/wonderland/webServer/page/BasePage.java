package main.java.wonderland.webServer.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.webElements.SidebarElement;
import main.java.wonderland.webServer.page.webElements.TopBarElement;
import main.java.wonderland.webServer.page.webElements.TopBarElements;
import spark.Response;

public class BasePage extends Page {

	private List<SidebarElement> sideBarElements;
	private List<TopBarElements> topBarElements;
	
	private String title;

	public BasePage(String subPath, String ftlFile, LoginLevel loginLevel, String title) {
		super(subPath, ftlFile, loginLevel);
		sideBarElements = new ArrayList<>();
		topBarElements = new ArrayList<>();
		this.title = title;
		init();
	}
	
	private void init(){
		SidebarElement e1 = new SidebarElement("Submenüs", "icon-tasks");
		e1.addElement(new SidebarElement("Link1", "http://google.com", "icon-angle-right", LoginLevel.Administrator));
		e1.addElement(new SidebarElement("Link2", "icon-angle-right", "http://yahoo.com"));
		
		this.addSideBarElement(e1);
		
		
		SidebarElement e2 = new SidebarElement("Zur Testseite", "icon-pencil", "/test");
		
		this.addSideBarElement(e2);
		
		
		TopBarElements topBar1 = new TopBarElements("icon-envelope-alt");
		topBar1.addTopBarElement(new TopBarElement("Lukas Peer", "Das ist ein Test", 5, LoginLevel.Schreiben));
		topBar1.setShownNumber(1);
		
		this.addTopBarElement(topBar1);
	}
	
	protected void setupSpecialPage(User u){
		
	}

	@Override
	protected void setupPage(User u) {
		Map<String, Object> map = u.getPageConfiguration();
		
		map.put("title", title);
		
		List<Object> addToUserMap = new ArrayList<>();
		for (SidebarElement element : sideBarElements) {
			Object webElement = ((IWebElement) element).getAsWebElement(u.getLoginLevel());
			if (webElement != null)
				addToUserMap.add(webElement);
		}
		map.put("sideBarElements", addToUserMap);
		
		List<Object> addToUserMapTop = new ArrayList<>();
		for (TopBarElements element : topBarElements) {
			Object webElement = ((IWebElement) element).getAsWebElement(u.getLoginLevel());
			if (webElement != null)
				addToUserMapTop.add(webElement);
		}
		map.put("topBarElements", addToUserMapTop);
		
		map.put("user", u.getAsWebElement(null));
		
		u.setPageConfiguration(map);
		
		setupSpecialPage(u);
	}
	
	public void addSideBarElement(SidebarElement element){		
		sideBarElements.add(element);
	}
	
	public void addTopBarElement(TopBarElements element){
		topBarElements.add(element);
	}
	
	public List<TopBarElements> getTopBarElements(){
		return topBarElements;
	}
	
	@Override
	protected void onPost(String key, String value, User u) {

	}

	@Override
	protected void onPostEnd(Response response, User u) {

	}

}
