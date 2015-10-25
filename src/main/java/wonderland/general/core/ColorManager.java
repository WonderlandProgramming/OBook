package main.java.wonderland.general.core;

import java.awt.Color;

public class ColorManager {

	/**
	 * Returns the resulting color for a book depending on the colors of book
	 * groups, subjects and categories.
	 * 
	 * @param book the book
	 * @param grade the grade
	 * @return the resulting color if existing, otherwise null
	 */
	public static Color getResultingColor(Grade grade, BookGroup bookGroup) {
		// Priority: BookGroups > Subjects > Categories
		//TODO
		return null;
	}

}
