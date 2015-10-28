package main.java.wonderland.general.core;

import java.sql.Timestamp;
import java.util.HashMap;

import main.java.wonderland.database.action.DBOrderEntry;

public class OrderEntry extends Order {

	private HashMap<OrderStatus, Timestamp> timestamps = new HashMap<>();
	private OrderStatus status;
	
	/**
	 * Creates a new order entry an order
	 * 
	 * @param order the order
	 */
	public OrderEntry(Order order) {
		super(order.getNumber(), order.getGrade(), order.getDescription(), order.getBooks());
		setStatus(OrderStatus.EMPTY);
	}
	
	/**
	 * Creates a new order entry containing an id and an order.
	 * 
	 * @param id the id
	 * @param order the order
	 */
	public OrderEntry(String id, Order order) {
		super(id, order.getNumber(), order.getGrade(), order.getDescription(), order.getBooks());
		setStatus(OrderStatus.EMPTY);
	}
		
	
	/**
	 * Creates this order entry in the database.
	 */
	public void createInDatabase() {
		DBOrderEntry.insertOrderEntry(this);
	}

	/**
	 * Updates the database entry for this order entry.
	 */
	public void syncWithDatabase() {
		DBOrderEntry.updateOrder(this);
	}
	
	/**
	 * Removes this order entry from the database.
	 */
	public void removeFromDatabase() {
		DBOrderEntry.removeOrderEntryByID(getID());
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
	 * @return the timestamps as JSon
	 */
	public String getTimestampsAsJSon() {
		//TODO
		return null;
	}
	
	/**
	 * Sets the Timestamps using a JSon string.
	 * 
	 * @param string the string
	 */
	public void setTimestampsFromJSon(String string) {
		// TODO
	}
	
	/**
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		timestamps.put(status, timestamp);
	}
	
	/**
	 * Returns all accessible properties of this subject in a HashMap.
	 * 
	 * @return the HashMap containing this subjects properties, but never null
	 */
	public HashMap<String, Object> getAsWebElement() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("number", getNumber());
		map.put("grade", getGrade());
		map.put("description", getDescription());
		map.put("books", getBooks());
		map.put("status", getStatus());
		map.put("valid", isValid());
		return map;
	}
	
	@Override
	public String toString() {
		return String.format("OrderEntry[status=%s, id=%s, number=%s, grade=%s, description=%s, books=%s]", status.toString(), getID(), getNumber(),
				getGrade().getName(), getDescription(), getBooks().length);
	}

}
