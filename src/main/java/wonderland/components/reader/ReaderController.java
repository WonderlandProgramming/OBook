package main.java.wonderland.components.reader;

import java.util.ArrayList;
import java.util.List;

public class ReaderController {

	private int panelCount;
	private List<Panel> panels = new ArrayList<>();

	public ReaderController() {
		
	}
	
	public Panel getFreePanel() {
		for (Panel panel : panels) {
			if(panel.getStatus() == PanelState.OPEN) return panel;
		}
		return null;
	}

	/**
	 * @return the panelCount
	 */
	public int getPanelCount() {
		return panelCount;
	}

	/**
	 * @param panelCount the panelCount to set
	 */
	public void setPanelCount(int panelCount) {
		this.panelCount = panelCount;
	}

}