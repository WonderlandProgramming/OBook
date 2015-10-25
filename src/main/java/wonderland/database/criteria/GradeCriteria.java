package main.java.wonderland.database.criteria;

import main.java.wonderland.database.DBVariables;

public enum GradeCriteria {
	NAME, SHORT_NAME, ALL; 
	
	public static String convertToDatabase(GradeCriteria crit) {
		switch (crit) {
		case NAME:
			return DBVariables.GRADES_NAME;
		case SHORT_NAME:
			return DBVariables.GRADES_SHORTNAME;
		default:
			return null;
		}
	}
	
}
