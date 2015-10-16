package main.java.wonderland.components.reader;

import java.sql.Timestamp;

import main.java.wonderland.general.core.Order;

public class Panel {

	private String ID;
	private PanelState status;
	private Order order;
	private int panelCounter;
	private Timestamp time;

	public Panel(String ID) {
		this.ID = ID;
	}
	
	public void update(Order order) {
		if(status == PanelState.OPEN) {
			setOrder(order);
			setTime(new Timestamp(System.currentTimeMillis()));
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
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	private void setOrder(Order order) {
		this.order = order;
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
	public Timestamp getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	private void setTime(Timestamp time) {
		this.time = time;
	}
}