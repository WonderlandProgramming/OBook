package main.java.wonderland.database.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.wonderland.database.DBConnetcor;
import main.java.wonderland.database.DBVariables;
import main.java.wonderland.database.ObjectConverter;
import main.java.wonderland.database.QueryFactory;
import main.java.wonderland.database.criteria.GradeCriteria;
import main.java.wonderland.general.core.Grade;

/**
 * Database Access class for grade objects.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 25-10-2015 00:05
 *
 */
public class DBGrade {

	/**
	 * Accesses the database and returns all existing grades.
	 * 
	 * @return all existing grades
	 */
	public static Grade[] getAll() {
		return getGrades("%", false, GradeCriteria.NAME);
	}
	
	/**
	 * Accesses the database and returns a grade with a given name if existing.
	 * 
	 * @param name the name of the book
	 * @return the grade if existing, otherwise null
	 */
	public static Grade getGradeByName(String name) {
		Grade[] grades = getGradesSingleCriteria(name, true, GradeCriteria.NAME);
		if (grades != null && grades.length > 0)
			return grades[0];
		return null;
	}

	/**
	 * Accesses the database and inserts a new grade if the name is still
	 * unused.
	 * 
	 * @param grade the grade
	 */
	public static void insertGrade(Grade grade) {
		DBConnetcor.executeUpdate(QueryFactory.getGradeInsertQuery(grade));
	}

	/**
	 * Accesses the database and removes a grade with a given name if existing.
	 * 
	 * @param name the name of the grade
	 */
	public static void removeGradeByName(String name) {
		DBConnetcor.executeUpdate(QueryFactory.getGradeDeleteQuery(name));
	}

	/**
	 * Accesses the database and updates a grade if existing. Please keep in
	 * mind that all values of the database entry will be replaced with new
	 * values from the grade.
	 * 
	 * @param grade the grade
	 */
	public static void updateGrade(Grade grade) {
		DBConnetcor.executeUpdate(QueryFactory.getGradeUpdateQuery(grade));
	}

	/**
	 * Accesses the database and returns an array of grades that matches the given
	 * search criteria.
	 * 
	 * @param match the string to search for
	 * @param exact true if the match has to be exact (search string has to
	 *        match to the compared string completely) or false if the match has
	 *        to be like the compared string
	 * @param criteria the criteria to search for
	 * @return an array containing the search results (an empty array if no
	 *         results found) or null if the search criteria is invalid
	 */
	public static Grade[] getGrades(String match, boolean exact, GradeCriteria criteria) {
		if (criteria == GradeCriteria.ALL) {
			return getGradesAllCriteria(match, exact);
		} else if (GradeCriteria.convertToDatabase(criteria) != null) {
			return getGradesSingleCriteria(match, exact, criteria);
		}
		return null;
	}

	/**
	 * Accesses the database and returns an array of grades that matches the given
	 * search criteria.
	 * 
	 * @param match the string to search for
	 * @param exact true if the match has to be exact (search string has to
	 *        match to the compared string completely) or false if the match has
	 *        to be like the compared string
	 * @param criteria the criteria to search for
	 * @return
	 */
	private static Grade[] getGradesSingleCriteria(String match, boolean exact, GradeCriteria criteria) {
		String converted = GradeCriteria.convertToDatabase(criteria);
		String query = QueryFactory.getStandardSelectQuery(DBVariables.GRADES_TABLE, match, exact, converted);
		ResultSet results = DBConnetcor.executeQuery(query);
		return ObjectConverter.convertToGrades(results);
	}

	/**
	 * Accesses the database and returns an array of grades that matches the given
	 * search criteria.
	 * 
	 * @param match the string to search for
	 * @param exact true if the match has to be exact (search string has to
	 *        match to the compared string completely) or false if the match has
	 *        to be like the compared string
	 * @return
	 */
	private static Grade[] getGradesAllCriteria(String match, boolean exact) {
		List<Grade> grades = new ArrayList<>();
		for (GradeCriteria crit : GradeCriteria.values()) {
			String converted = GradeCriteria.convertToDatabase(crit);
			if (converted != null) {
				String query = QueryFactory.getStandardSelectQuery(DBVariables.GRADES_TABLE, match, exact, converted);
				ResultSet results = DBConnetcor.executeQuery(query);
				List<Grade> search = Arrays.asList(ObjectConverter.convertToGrades(results));
				for (Grade grade : search) {
					if (!grade.equalsAny(grades.toArray(new Grade[0])))
						grades.add(grade);
				}
			}
		}
		return grades.toArray(new Grade[0]);
	}

}
