package main.java.wonderland.general.core;

public abstract class Serial {

	private String id;

	/**
	 * @return the id
	 */
	public String getID() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	protected void setID(String id) {
		this.id = id;
	}
	
	/**
	 * @return true, if the ID has been set, otherwise false
	 */
	public boolean hasID() {
		if (id != null && !id.isEmpty())
			return true;
		return false;
	}

	public abstract boolean isValid();
	
}
