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
public enum CategoryCriteria {
	NAME, SHORT_NAME, ALL;

	public static String convertToDatabase(CategoryCriteria crit) {
		switch (crit) {
		case NAME:
			return DBVariables.CATEGORIES_NAME;
		case SHORT_NAME:
			return DBVariables.CATEGORIES_SHORTNAME;
		default:
			return null;
		}
	}

}
