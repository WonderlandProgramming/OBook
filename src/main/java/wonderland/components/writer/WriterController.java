package main.java.wonderland.components.writer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.wonderland.error.InvalidOrderException;
import main.java.wonderland.general.core.Grade;
import main.java.wonderland.general.core.Order;
import main.java.wonderland.general.core.OrderEntry;

public class WriterController {
	
	private static Logger log = LogManager.getLogger(WriterController.class.getName());
	
	private final static WriterController writerController = new WriterController();
	
	public static WriterController instance(){
		return writerController;
	}
	
	private ListControl listControl;
	
	/**
	 * Constructs a new WriterController.
	 * 
	 * @param page
	 */
	public WriterController() {
		log.log(Level.INFO, "Starting OBook WriterController Service.");
		log.log(Level.INFO, "OBook WriterController Service initialized.");
	}
	
	/**
	 * FCreates a new order if possible.
	 * 
	 * @throws InvalidOrderException if the order is not valid
	 */
	public void createOrder(Order order) throws InvalidOrderException {
		if(order.isValid()) {
			OrderEntry entry = new OrderEntry(order);
			entry.createInDatabase();
		} else {
			throw new InvalidOrderException(order);
		}
	}
	
	/**
	 * Initializes the ListControl with a given grade.
	 * 
	 * @param grade the grade
	 * @param defaultSize the default list size
	 */
	public void initialiseListControl(Grade grade, int defaultSize) {
		listControl = new ListControl(grade, defaultSize);
	}

	/**
	 * @return the list
	 */
	public ListControl getListControl() {
		return listControl;
	}
}
