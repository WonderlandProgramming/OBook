package main.java.wonderland.components.writer;

import java.sql.Timestamp;
import java.util.HashMap;

import main.java.wonderland.general.core.Book;
import main.java.wonderland.general.core.Grade;

public class StatisticalAnalyt {

	private static HashMap<Grade, HashMap<Book, AnalytEntry>> stats = new HashMap<>();

	public static void addEntry(Grade grade, Book book) {
		if (stats.get(grade) != null) {
			if (stats.get(grade).get(book) != null) {
				stats.get(grade).get(book).addTimestamp();
			} else {
				stats.get(grade).put(book, new AnalytEntry());
			}
		} else {
			stats.put(grade, new HashMap<>());
			addEntry(grade, book);
		}
	}

	public static int getAmountPastTime(Grade grade, Book book, int time) {
		int amount = 0;
		long current = System.currentTimeMillis();
		if (stats.get(grade) != null && stats.get(grade).get(book) != null) {
			Timestamp[] timestamps = stats.get(grade).get(book).getTimestamps();
			for (Timestamp timestamp : timestamps) {
				if (current - timestamp.getTime() < time) {
					amount++;
				}
			}
		}
		return amount;
	}
}
