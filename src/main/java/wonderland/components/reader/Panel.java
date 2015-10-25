package main.java.wonderland.components.reader;

import java.sql.Timestamp;

import main.java.wonderland.database.action.DBOrderEntry;
import main.java.wonderland.error.InvalidPermissionEcxeption;
import main.java.wonderland.general.core.OrderEntry;
import main.java.wonderland.general.core.OrderStatus;

public class Panel {

	private String ID;
	private PanelState status;
	private OrderEntry orderEntry;
	private int panelCounter;
	private Timestamp time;

	public Panel(String ID) {
		this.ID = ID;
		this.status = PanelState.OPEN;
	}

	public void assign(OrderEntry order) {
		if (status == PanelState.OPEN) {
			setOrderEntry(order);
			setTimestamp(new Timestamp(System.currentTimeMillis()));
			setStatus(PanelState.ASSIGNED);
		}
	}

	/**
	 * Updates target panel, setting its status to the next possible one.
	 * 
	 * @param panel the panel
	 */
	public void update() throws InvalidPermissionEcxeption {
		OrderEntry order = DBOrderEntry.getOrderEntryByID(orderEntry.getID());
		if (order != null && order.getStatus() == orderEntry.getStatus()
				&& order.getTimestamp(OrderStatus.ASSIGNED) == orderEntry.getTimestamp(OrderStatus.ASSIGNED)) {
			switch (getStatus()) {
			case ASSIGNED:
				setStatus(PanelState.IN_PROGESS);
				break;
			case IN_PROGESS:
				setStatus(PanelState.OPEN);
				orderEntry = null;
				break;
			case PRIORITY:
				setStatus(PanelState.OPEN);
				orderEntry = null;
				break;
			default:
				System.err.println("No forwarding on this panel possible.");
				break;
			}
		} else {
			throw new InvalidPermissionEcxeption("The order you want to update does not match with the information in this panel.");
		}
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @return the status
	 */
	public PanelState getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(PanelState status) {
		this.status = status;
	}

	/**
	 * @return the orderEntry
	 */
	public OrderEntry getOrderEntry() {
		return orderEntry;
	}

	/**
	 * @param orderEntry the orderEntry to set
	 */
	public void setOrderEntry(OrderEntry orderEntry) {
		this.orderEntry = orderEntry;
	}

	/**
	 * @return the panelCounter
	 */
	public int getPanelCounter() {
		return panelCounter;
	}

	/**
	 * @param panelCounter the panelCounter to set
	 */
	public void setPanelCounter(int panelCounter) {
		this.panelCounter = panelCounter;
	}

	/**
	 * @return the time
	 */
	public Timestamp getTimestamp() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	private void setTimestamp(Timestamp time) {
		this.time = time;
	}
}