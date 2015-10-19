package main.java.wonderland.webServer.page.webElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.page.IWebElement;

public class TopBarElements implements IWebElement{

	private int shownNumber = 0;
	private List<TopBarElement> elements;
	private String symbol;
	
	public TopBarElements(String symbol) {
		elements = new ArrayList<>();
		this.symbol = symbol;
	}
	
	public int getShownNumber() {
		return shownNumber;
	}
	public void addTopBarElement(TopBarElement element) {
		this.elements.add(element);
	}

	public void setShownNumber(int shownNumber) {
		this.shownNumber = shownNumber;
	}

	public List<TopBarElement> getElements() {
		return elements;
	}

	public void setElements(List<TopBarElement> elements) {
		this.elements = elements;
	}

	@Override
	public Object getAsWebElement(LoginLevel level) {
		List<Object> canAdd = new ArrayList<>();
		for (TopBarElement topBarElement : elements) {
			Object element = ((IWebElement)topBarElement).getAsWebElement(level);
			if(element != null){
				canAdd.add(element);
				if(canAdd.size() >= 3){
					break;
				}
			}
		}
		if(canAdd.size() == 0){
			canAdd = null;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("topBarElements", canAdd);
		map.put("shownNumber", shownNumber);
		map.put("symbol", symbol);
		
		return map;
	}
}
