package wonderland.components.reader;

import java.util.ArrayList;
import java.util.List;

import wonderland.components.server.ServerControl;
import wonderland.general.core.Order;

public class ObjectQueue {

	private PanelController controller;
	private int maxQueueSize;
	private int updateRate;
	private List<Order> orders = new ArrayList<>();
	private boolean isRunning = false;

	/**
	 * Main Constructor.
	 * 
	 * @param panel
	 * @param maxQueueSize
	 * @param updateRate
	 */
	public ObjectQueue(PanelController controller, int maxQueueSize, int updateRate) {
		start();
		this.controller = controller;
		this.maxQueueSize = maxQueueSize;
		this.updateRate = updateRate;
	}

	private void start() {
		Thread put = new Thread(new Runnable() {
			
			@Override
			public void run() {
				isRunning = true;
				while (isRunning) {
					if(ServerControl.hasOrders() && orders.size() < maxQueueSize) {
						Order order = ServerControl.getNextOrder();
						if(order != null) {
							orders.add(order);
							controller.addOrder(order);
						}
					}
					try {
						Thread.sleep(updateRate);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		put.start();
	}
	
	/**
	 * Stops the running operation Thread.
	 */
	public void stop() {
		isRunning = false;
	}

	/**
	 * @return the maxQueueSize
	 */
	public int getMaxQueueSize() {
		return maxQueueSize;
	}

	/**
	 * @param maxQueueSize the maxQueueSize to set
	 */
	public void setMaxQueueSize(int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
	}

	/**
	 * @return the updateRate
	 */
	public int getUpdateRate() {
		return updateRate;
	}

	/**
	 * @param updateRate the updateRate to set
	 */
	public void setUpdateRate(int updateRate) {
		this.updateRate = updateRate;
	}

	/**
	 * @return the orders
	 */
	public Order[] getOrders() {
		return orders.toArray(new Order[0]);
	}

	/**
	 * @param order the order to remove
	 */
	public void removeOrder(Order order) {
		orders.remove(order);
	}
}
