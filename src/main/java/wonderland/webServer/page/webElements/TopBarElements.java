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
	private String bottomText;
	private String noElementsMessage = "";
	private String link = "#";
	
	/**
	 * 
	 * @param symbol
	 * @param noElementsMessage
	 * @param link
	 * @param bottomText
	 */
	public TopBarElements(String symbol, String noElementsMessage, String link, String bottomText) {
		elements = new ArrayList<>();
		this.symbol = symbol;
		this.noElementsMessage = noElementsMessage;
		this.link = link;
		this.bottomText = bottomText;
	}
	
	public int getShownNumber() {
		return shownNumber;
	}
	public void addTopBarElement(TopBarElement element) {
		this.elements.add(element);
	}

	public List<TopBarElement> getElements() {
		return elements;
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
			shownNumber = 0;
		}else{
			shownNumber = elements.size();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("topBarElements", canAdd);
		map.put("shownNumber", shownNumber);
		map.put("symbol", symbol);
		map.put("noElementsMessage", noElementsMessage);
		map.put("link", link);
		map.put("bottomText", bottomText);
		
		return map;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getBottomText() {
		return bottomText;
	}

	public String getNoElementsMessage() {
		return noElementsMessage;
	}

	public String getLink() {
		return link;
	}

	public void setElements(List<TopBarElement> elements) {
		this.elements = elements;
	}
}
