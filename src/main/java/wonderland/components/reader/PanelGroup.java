package main.java.wonderland.components.reader;

import java.util.ArrayList;
import java.util.List;

import main.java.wonderland.general.UIDGenerator;
import main.java.wonderland.general.core.OrderEntry;
import main.java.wonderland.general.core.OrderStatus;
import main.java.wonderland.main.Settings;

public class PanelGroup {

	private String userID;
	private boolean isActive = true;
	private List<Panel> panels = new ArrayList<>();

	private int targetPanelCount;
	private boolean lockPanels = false;

	/**
	 * Constructs a new PanelGroup.
	 * 
	 * @param orderQueue the order queue
	 * @param userID the user ID
	 * @param initSize the initial size
	 */
	public PanelGroup(OrderQueue orderQueue, String userID, int initSize) {
		this.userID = userID;
		initPanels(initSize);
	}

	/**
	 * Validates if each panel still matches the maximum time criteria,
	 * otherwise it will be put into priority mode. Orders from Panels already
	 * in Priority mode will be removed after a set amount of time and if
	 * occurred, the panel group will be set to inactive.
	 */
	public void checkTimePriority() {
		long current = System.currentTimeMillis();
		for (Panel panel : panels) {
			if (panel.getStatus() == PanelState.ASSIGNED) {
				if ((panel.getTimestamp().getTime() - current) >= Settings.NEEDED_PRIORITY_DURATION) {
					panel.setStatus(PanelState.PRIORITY);
				}
			} else if (panel.getStatus() == PanelState.PRIORITY) {
				if ((panel.getTimestamp().getTime() - current) >= Settings.NEEDED_INACTIVE_DURATION) {
					panel.getOrderEntry().setStatus(OrderStatus.UNASSIGNED);
					panel.setStatus(PanelState.OPEN);
					setActive(false);
				}
			}
		}
	}

	/**
	 * Validates if each panel still matches the maximum count criteria,
	 * otherwise it will be put into priority mode.
	 */
	public void checkCountPriority() {
		for (Panel panel : panels) {
			if (panel.getStatus() == PanelState.ASSIGNED) {
				if (panel.getPanelCounter() >= Settings.NEEDED_COUNT) {
					panel.setStatus(PanelState.PRIORITY);
				}
			}
		}
	}

	/**
	 * Returns the first panel that matches {@link PanelState#OPEN}. If none is
	 * found, null will be returned instead.
	 * 
	 * @return the next free panel if existing
	 */
	public Panel getFreePanel() {
		for (Panel panel : panels) {
			if (panel.getStatus() == PanelState.OPEN && lockPanels == false)
				return panel;
		}
		return null;
	}

	/**
	 * Updates the current amount of panels, depending on the active and target
	 * panel count. If no more panels can be removed (as it would result in loss
	 * of data) the process will be stopped, but all panels will be locked.
	 * 
	 * @return true, if the operation could be completed
	 */
	public boolean updatePanelCount() {
		if (getPanelCount() < targetPanelCount) {
			for (int i = getPanelCount(); i <= targetPanelCount; i++) {
				panels.add(new Panel(UIDGenerator.genNextID())); // TODO Set
																	// panel ID
			}
		} else if (getPanelCount() > targetPanelCount) {
			for (int i = getPanelCount(); i >= targetPanelCount; i--) {
				Panel panel = getFreePanel();
				if (panel != null) {
					panels.remove(panel);
				} else {
					if (lockPanels == false)
						lockPanels = true;
					System.err.println("Cannot remove any panels: All panels still occupied.");
					return false;
				}
			}
		}
		if (lockPanels == true)
			lockPanels = false;
		return true;
	}

	/**
	 * Removes a panel and changes the order status if this panels still holds
	 * an order.
	 * 
	 * @param panel the panel
	 * @param targetStatus the target status
	 */
	public void removePanel(Panel panel, OrderStatus targetStatus) {
		OrderEntry order = panel.getOrderEntry();
		if (order != null) {
			order.setStatus(targetStatus);
			panels.remove(panel);
		}

	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the amount of panels
	 */
	public int getPanelCount() {
		return panels.size();
	}

	/**
	 * @return the futurePanelCount
	 */
	public int getTargetPanelCount() {
		return targetPanelCount;
	}

	/**
	 * @param futurePanelCount the futurePanelCount to set
	 */
	public void setTargetPanelCount(int targetPanelCount) {
		this.targetPanelCount = targetPanelCount;
	}

	/**
	 * @return the lockPanels
	 */
	public boolean isLockPanels() {
		return lockPanels;
	}

	/**
	 * @param lockPanels the lockPanels to set
	 */
	public void setLockPanels(boolean lockPanels) {
		this.lockPanels = lockPanels;
	}

	private void initPanels(int panels) {
		for (int i = 0; i < panels; i++) {
			this.panels.add(new Panel(UIDGenerator.genNextID()));
		}
	}

	public Panel[] getPanels() {
		return panels.toArray(new Panel[0]);
	}

	public Panel getPanel(String ID) {
		for (Panel panel : panels) {
			if (panel.getID().equals(ID)) {
				return panel;
			}
		}
		return null;
	}

	public int getOccupiedPanels() {
		int occupied = 0;
		for (Panel panel : panels) {
			if (PanelState.hasOrder(panel.getStatus())) {
				occupied++;
			}
		}
		return occupied;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

}
