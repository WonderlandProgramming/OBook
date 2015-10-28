package main.java.wonderland.general;

import java.awt.Color;

import main.java.wonderland.general.core.Book;
import main.java.wonderland.general.core.BookGroup;

public class ColorManager {

	/**
	 * Returns the resulting color for a book depending on the colors of book
	 * groups, subjects and categories.
	 * 
	 * @param book the book
	 * @param grade the grade
	 * @return the resulting color if existing, otherwise null
	 */
	public static Color getResultingColor(BookGroup bookGroup, Book book) {
		// Priority: BookGroups > Subjects > Categories
		if(bookGroup.hasColor()) {
			return bookGroup.getColor();
		} else if(book.hasSubject()) {
			return book.getSubject().getColor();
		} else if (book.getSubject() != null && book.getSubject().hasCategory()){
			return book.getSubject().getCategory().getColor();
		}
		return null;
	}

}
