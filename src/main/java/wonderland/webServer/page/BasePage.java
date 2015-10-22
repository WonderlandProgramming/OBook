package main.java.wonderland.webServer.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.webElements.SidebarElement;
import main.java.wonderland.webServer.page.webElements.TopBarElement;
import main.java.wonderland.webServer.page.webElements.TopBarElements;

public abstract class BasePage extends Page {

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
		//Sidebar Elements
		SidebarElement dashBoard = new SidebarElement("Dashboard", "icon-home", "/dashboard");
		this.addSideBarElement(dashBoard);
		
		SidebarElement auftrag = new SidebarElement("Aufträge", "icon-pencil");
		auftrag.addElement(new SidebarElement("Erstellen", "/orders/create", false));
		auftrag.addElement(new SidebarElement("Bearbeiten", "/orders/process", false));
		auftrag.addElement(new SidebarElement("Fertigstellen", "/orders/finish", false));
		auftrag.addElement(new SidebarElement("Übersicht", "/orders/edit", null, LoginLevel.Moderator));
		this.addSideBarElement(auftrag);
		
		SidebarElement bibliothek = new SidebarElement("Bibliothek", "icon-book", LoginLevel.Moderator);
		bibliothek.addElement(new SidebarElement("Neu Erstellen", "/libary/create", false));
		bibliothek.addElement(new SidebarElement("Bearbeiten", "/libary/edit", false));
		this.addSideBarElement(bibliothek);
		
		SidebarElement kommunikation = new SidebarElement("Kommunikation", "icon-comments");
		kommunikation.addElement(new SidebarElement("Information", "/communication/log", false));
		kommunikation.addElement(new SidebarElement("Chats", "/communication/chats", false));
		this.addSideBarElement(kommunikation);
		
		SidebarElement nutzer = new SidebarElement("Verwaltung", "icon-lock", LoginLevel.Administrator);
		nutzer.addElement(new SidebarElement("Benutzer", "/management/user", false));
		nutzer.addElement(new SidebarElement("Konfiguration", "/management/config", false));
		this.addSideBarElement(nutzer);
		
		SidebarElement statistik = new SidebarElement("Statistik", "icon-bar-chart", "/statistic");
		this.addSideBarElement(statistik);
		
		
		
		//TopBar Elements
		TopBarElements topBar1 = new TopBarElements("icon-envelope-alt", "Kein Inhalt", "#", "Zu allen Inhalten");
		topBar1.addTopBarElement(new TopBarElement("Lukas Peer", "Das ist ein Test", 5, LoginLevel.User));
		
		this.addTopBarElement(topBar1);
	}
	
	protected abstract void setupSpecialPage(User u);

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

}
