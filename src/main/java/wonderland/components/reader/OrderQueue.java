package main.java.wonderland.components.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.wonderland.general.core.OrderEntry;

public class OrderQueue {

	private static Logger log = LogManager.getLogger(OrderQueue.class.getName());
	
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
		log.log(Level.INFO, "Starting OBook OrderQueue.");
		Thread put = new Thread(new Runnable() {

			@Override
			public void run() {
				isRunning = true;
				while (isRunning) {
					OrderEntry order = controller.getNextOrder();
					PanelGroup panelGroup = controller.getFreePanelGroup();
					Panel panel = null;
					if (panelGroup != null) panel = panelGroup.getFreePanel();
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