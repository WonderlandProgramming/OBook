package main.java.wonderland.components.writer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AnalytEntry {

	private static List<Timestamp> timestamps = new ArrayList<>();
	
	public AnalytEntry() {
		
	}
	
	public void addTimestamp() {
		timestamps.add(new Timestamp(System.currentTimeMillis()));
	}
	
	public Timestamp[] getTimestamps() {
		return timestamps.toArray(new Timestamp[0]);
	}
	
}
