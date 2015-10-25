package main.java.wonderland.error;

public class InvalidOrderException extends Exception {

	private static final long serialVersionUID = -2924282720264175140L;

	/**
	 * Throws a new InvalidOrderException.
	 */
	public InvalidOrderException() {

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

}
