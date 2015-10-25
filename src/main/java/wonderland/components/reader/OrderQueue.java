package main.java.wonderland.components.reader;

import main.java.wonderland.general.core.OrderEntry;

public class OrderQueue {

	private ReaderController controller;
	private int updateRate;
	private boolean isRunning = false;

	/**
	 * @param panel
	 * @param maxQueueSize
	 * @param updateRate
	 */
	public OrderQueue(ReaderController controller, int updateRate) {
		this.controller = controller;
		this.updateRate = updateRate;
		start();
	}

	private void start() {
		Thread put = new Thread(new Runnable() {

			@Override
			public void run() {
				isRunning = true;
				while (isRunning) {
					OrderEntry order = controller.getNextOrder();
					Panel panel = controller.getFreePanelGroup().getFreePanel();
					if (panel != null && order != null) {
						panel.assign(order);
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