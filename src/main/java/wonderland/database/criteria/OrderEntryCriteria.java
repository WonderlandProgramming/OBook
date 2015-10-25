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
public enum OrderEntryCriteria {
	ID, STATUS, NUMBER, GRADE, ALL;

	public static String convertToDatabase(OrderEntryCriteria crit) {
		switch (crit) {
		case ID:
			return DBVariables.ORDERS_ID;
		case STATUS:
			return DBVariables.ORDERS_STATUS;
		case NUMBER:
			return DBVariables.ORDERS_NUMBER;
		case GRADE:
			return DBVariables.ORDERS_GRADE;
		default:
			return null;
		}
	}

}
