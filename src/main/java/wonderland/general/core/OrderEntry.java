package main.java.wonderland.general.core;

import java.sql.Timestamp;
import java.util.HashMap;

public class OrderEntry extends Order {

	private HashMap<OrderStatus, Timestamp> timestamps = new HashMap<>();
	private OrderStatus status;
	
	/**
	 * Constructs a new OrderEntry.
	 * 
	 * @param ID the ID
	 * @param number the number
	 * @param grade the grade
	 * @param books the books
	 */
	public OrderEntry(String ID, int number, Grade grade, Book[] books) {
		super(ID, number, grade, books);
		setStatus(OrderStatus.EMPTY);
	}
	
	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp(OrderStatus status) {
		return timestamps.get(status);
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(OrderStatus status, Timestamp timestamp) {
		timestamps.put(status, timestamp);
	}
	
	/**
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}
	
	public boolean matches(OrderEntry order) {
		// TODO
		return true;
	}
	
	public boolean matchesAny(OrderEntry[] order) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		timestamps.put(status, timestamp);
	}

}
