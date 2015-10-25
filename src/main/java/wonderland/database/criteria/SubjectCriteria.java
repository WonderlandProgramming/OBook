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
public enum SubjectCriteria {
	NAME, SHORT_NAME, CATEGORY, ALL;

	public static String convertToDatabase(SubjectCriteria crit) {
		switch (crit) {
		case NAME:
			return DBVariables.SUBJECTS_NAME;
		case SHORT_NAME:
			return DBVariables.SUBJECTS_SHORTNAME;
		case CATEGORY:
			return DBVariables.SUBJECTS_CATEGORY;
		default:
			return null;
		}
	}

}
