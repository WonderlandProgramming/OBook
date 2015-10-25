package main.java.wonderland.database.criteria;

import main.java.wonderland.database.DBVariables;

/**
 * Holds the criteria available for a book search.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 20:10
 *
 */
public enum BookCriteria {
	ID, NAME, SUBJECT, ALL;

	public static String convertToDatabase(BookCriteria crit) {
		switch (crit) {
		case ID:
			return DBVariables.BOOKS_ID;
		case NAME:
			return DBVariables.BOOKS_NAME;
		case SUBJECT:
			return DBVariables.BOOKS_SUBJECT;
		default:
			return null;
		}
	} 
	
}
