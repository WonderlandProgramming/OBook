package main.java.wonderland.components.reader;

import java.util.ArrayList;
import java.util.List;

import main.java.wonderland.general.core.OrderEntry;

/**
 * Control and Management unit for the OBook-Reader component.
 * 
 * @author Lukas Kannenberg
 * @since 16-10-2015
 * @version 18-10-2015 15:30
 *
 */
public class ReaderController {

	private OrderQueue queue;
	private List<PanelGroup> panelsGroups = new ArrayList<>();

	/**
	 * Constructs a new Reader Controller.
	 * 
	 * @param page the associated web controller page object
	 */
	public ReaderController() {
		this.queue = new OrderQueue(this, 500);

		Thread timestamps = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					for (PanelGroup panelGroup : panelsGroups) {
						panelGroup.checkTimePriority();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		timestamps.start();
	}

	public OrderEntry getNextOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public PanelGroup getFreePanelGroup() {
		PanelGroup group = null;
		int leastAmount = Integer.MAX_VALUE;
		for (PanelGroup panelGroup : panelsGroups) {
			if (panelGroup.getFreePanel() != null) {
				if (panelGroup.getOccupiedPanels() < leastAmount) {
					leastAmount = panelGroup.getOccupiedPanels();
					group = panelGroup;
				}
			}
		}
		if (group != null)
			return group;
		return null;
	}

	public void addPanelGroup(String userID, int panels) {
		panelsGroups.add(new PanelGroup(queue, userID, panels));
	}

	public PanelGroup getPanelGroup(String userID) {
		for (PanelGroup panelGroup : panelsGroups) {
			if (panelGroup.getUserID() == userID) {
				return panelGroup;
			}
		}
		return null;
	}

}