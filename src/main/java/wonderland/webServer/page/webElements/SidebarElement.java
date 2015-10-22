package main.java.wonderland.webServer.page.webElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.page.IWebElement;

public class SidebarElement implements IWebElement {

	private String name;
	private String iconPath;

	private String linkPath;

	private LoginLevel accessLevel;

	private List<SidebarElement> subElements;

	public SidebarElement(String name, String iconPath) {
		this(name, null, iconPath, LoginLevel.User);
	}

	public SidebarElement(String name, String iconPath, String linkPath) {
		this(name, linkPath, iconPath, LoginLevel.User);
	}
	
	public SidebarElement(String name, String iconPath, LoginLevel level) {
		this(name, null, iconPath, level);
	}
	
	public SidebarElement(String name, String path, boolean userlessVariable) {
		this(name, path, null, LoginLevel.User);
	}

	public SidebarElement(String name, String linkPath, String iconPath, LoginLevel level) {
		this.name = name;
		this.linkPath = linkPath;
		this.iconPath = iconPath;
		this.accessLevel = level;
		subElements = new ArrayList<>();
	}

	public void addElement(SidebarElement element) {
		subElements.add(element);
	}

	public String getIconPath() {
		return iconPath;
	}

	public String getName() {
		return name;
	}

	public String getLinkPath() {
		return linkPath;
	}

	@Override
	public Object getAsWebElement(LoginLevel level) {
		if (level.getLevel() < this.accessLevel.getLevel())
			return null;

		HashMap<String, Object> map = new HashMap<>();
		map.put("name", this.name);
		map.put("iconPath", this.iconPath);
		if (linkPath != null) {
			map.put("linkPath", this.linkPath);
		} else {
			List<Object> subMenues = new ArrayList<>();
			for (SidebarElement element : this.subElements) {
				Object webElement = ((IWebElement) element).getAsWebElement(level);
				if (webElement != null)
					subMenues.add(webElement);
			}
			map.put("subMenues", subMenues);
		}
		return map;
	}
}
