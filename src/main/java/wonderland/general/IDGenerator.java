package main.java.wonderland.general;

public class IDGenerator {

	private int start = 0;
	private int end = 0;
	private int current;

	/**
	 * Constructs a new IDGenerator.
	 * 
	 * @param start
	 * @param end
	 */
	public IDGenerator(int start, int end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Generates a new ID. If the ID would exceed the defined maximum it will be
	 * set back to the start.
	 * 
	 * @return the next ID
	 */
	public int getNextID() {
		int number = current;
		current++;
		if (current > end)
			current = start;
		return number;
	}

	/**
	 * Sets the current ID if is within the defined range. The ID is in range if
	 * it neither exceeds the maximum nor deceeds the minimum.
	 * 
	 * @param ID the new current ID
	 */
	public void setCurrentID(int ID) {
		if (ID <= end && ID >= start)
			current = ID;
	}

	/**
	 * Resets the current ID. (the current number will be set back to the
	 * starting point)
	 */
	public void reset() {
		current = start;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

}
