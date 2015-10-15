package wonderland.general.core;

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
	public static Color getResultingColor(Grade grade, BookItem bookItem) {
		// Priority: BookGroups > Subjects > Categories
		Book book = bookItem.getBook();
		if (grade.hasBookGroups()) {
			BookGroup[] bookGroups = grade.getBookGroups();
			for (BookGroup bookGroup : bookGroups) {
				if (bookGroup.hasColor() && bookGroup.containsBookItem(bookItem)) {
					return bookGroup.getColor();
				}
			}
		}
		if (book.hasSubject() && book.getSubject().hasColor()) {
			return book.getSubject().getColor();
		}
		if (book.hasSubject() && book.getSubject().hasCategory() && book.getSubject().getCategory().hasColor()) {
			return book.getSubject().getCategory().getColor();
		}
		return null;
	}

}
