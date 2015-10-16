package main.java.wonderland.components.reader;

import main.java.wonderland.components.ClientControl;
import main.java.wonderland.general.core.Order;

public class ObjectQueue {
	
	private ReaderController controller;
	private int updateRate;
	private boolean isRunning = false;
	
	/**
	 * @param panel
	 * @param maxQueueSize
	 * @param updateRate
	 */
	public ObjectQueue(ReaderController controller, int maxQueueSize, int updateRate) {
		start();
		this.controller = controller;
		this.updateRate = updateRate;
	}

	private void start() {
		Thread put = new Thread(new Runnable() {
			
			@Override
			public void run() {
				isRunning = true;
				while (isRunning) {
					if(ClientControl.hasOrders() && controller.getFreePanel() != null) {
						Order order = ClientControl.getNextOrder();
						if(order != null) {
							controller.getFreePanel().setOrder(order);
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
	
}