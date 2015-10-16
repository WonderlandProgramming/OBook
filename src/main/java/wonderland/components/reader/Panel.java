package main.java.wonderland.components.reader;

import main.java.wonderland.general.core.Order;

public class Panel {

	private PanelState status;
	private Order order;

	private void update() {
		// TODO Auto-generated method stub
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
			update();
		}
	}
}