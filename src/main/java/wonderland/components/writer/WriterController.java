package main.java.wonderland.components.writer;

import main.java.wonderland.error.InvalidOrderException;
import main.java.wonderland.general.core.Grade;
import main.java.wonderland.general.core.Order;

public class WriterController {
	
	private ListControl listControl;
	
	/**
	 * Constructs a new WriterController.
	 * 
	 * @param page
	 */
	public WriterController() {
	
	}
	
	/**
	 * FCreates a new order if possible.
	 * 
	 * @throws InvalidOrderException if the order is not valid
	 */
	public static void createOrder(Order order) throws InvalidOrderException {
		if(order.isValid()) {
			// TODO Add order to list
		} else {
			throw new InvalidOrderException("The order is not valid.");
		}
	}
	
	public void initialiseListControl(Grade grade) {
		listControl = new ListControl(grade);
	}

	/**
	 * @return the list
	 */
	public ListControl getListControl() {
		return listControl;
	}
}
