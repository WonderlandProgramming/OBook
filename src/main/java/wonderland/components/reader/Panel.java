package main.java.wonderland.components.reader;

import java.sql.Timestamp;
import java.util.Timer;

import org.w3c.dom.css.Counter;

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
	
	private void update() {
		
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
	public void setOrder(Order order) {
		if(status == PanelState.OPEN) {
			this.setOrder(order);
			time = new Timestamp(System.currentTimeMillis());
			update();
		}
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
	public void setTime(Timestamp time) {
		this.time = time;
	}
}