package main.java.wonderland.error;

public class InvalidPermissionEcxeption extends Exception {

	private static final long serialVersionUID = 7229711155801581572L;

	/**
	 * Throws a new InvalidOrderException.
	 */
	public InvalidPermissionEcxeption() {

	}

	public InvalidPermissionEcxeption(String message) {
		super(message);
	}

	public InvalidPermissionEcxeption(Throwable cause) {
		super(cause);
	}

	public InvalidPermissionEcxeption(String message, Throwable cause) {
		super(message, cause);
	}
}
