package main.java.wonderland.error;

import main.java.wonderland.general.core.Order;

public class InvalidOrderException extends Exception {

	private static final long serialVersionUID = -2924282720264175140L;

	/**
	 * Throws a new InvalidOrderException.
	 */
	public InvalidOrderException(Order order) {
		super(getMessage(order));
	}

	public InvalidOrderException(String message) {
		super(message);
	}

	public InvalidOrderException(Throwable cause) {
		super(cause);
	}

	public InvalidOrderException(String message, Throwable cause) {
		super(message, cause);
	}
	
	private static String getMessage(Order order) {
		if (!order.hasID()) {
			return "Der Auftrag hat keine ID.";
		}
		if (!order.hasNumber()) {
			return "Der Auftrag hat keine Auftragsnummer.";
		}
		if (!order.hasBooks()) {
			return "Der Auftrag hat keine Bücher zugewiesen.";
		}
		return null;
	}

}
