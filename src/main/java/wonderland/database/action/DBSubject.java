package main.java.wonderland.database.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.wonderland.database.DBConnetcor;
import main.java.wonderland.database.DBVariables;
import main.java.wonderland.database.ObjectConverter;
import main.java.wonderland.database.QueryFactory;
import main.java.wonderland.database.criteria.SubjectCriteria;
import main.java.wonderland.general.core.Subject;

/**
 * Reads data from local disk.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 15:16
 *
 */
public class DBSubject {

	/**
	 * Accesses the database and returns all existing subjects.
	 * 
	 * @return all existing subjects
	 */
	public static Subject[] getAll() {
		return getSubjects("%", false, SubjectCriteria.NAME);
	}
	
	public static Subject getSubjectByName(String name) {
		Subject[] subjects = getSubjectsSingleCriteria(name, true, SubjectCriteria.NAME);
		if (subjects != null && subjects.length > 0)
			return subjects[0];
		return null;
	}

	public static void insertSubject(Subject subject) {
		DBConnetcor.executeUpdate(QueryFactory.getSubjectInsertQuery(subject));
	}

	public static void removeSubjectByName(String name) {
		DBConnetcor.executeUpdate(QueryFactory.getSubjectDeleteQuery(name));
	}

	public static void updateSubject(Subject subject) {
		DBConnetcor.executeUpdate(QueryFactory.getSubjectUpdateQuery(subject));
	}

	/**
	 * Fetches all subjects from the database that match the given criteria.
	 * 
	 * @param match the match criteria
	 * @return an array containing the results
	 */
	public static Subject[] getSubjects(String match, boolean exact, SubjectCriteria criteria) {
		if (criteria == SubjectCriteria.ALL) {
			return getSubjectsAllCriteria(match, exact);
		} else if (SubjectCriteria.convertToDatabase(criteria) != null) {
			return getSubjectsSingleCriteria(match, exact, criteria);
		}
		return null;
	}

	private static Subject[] getSubjectsSingleCriteria(String match, boolean exact, SubjectCriteria criteria) {
		String converted = SubjectCriteria.convertToDatabase(criteria);
		String query = QueryFactory.getStandardSelectQuery(DBVariables.SUBJECTS_TABLE, match, exact, converted);
		ResultSet results = DBConnetcor.executeQuery(query);
		return ObjectConverter.convertToSubjects(results);
	}

	private static Subject[] getSubjectsAllCriteria(String match, boolean exact) {
		List<Subject> subjects = new ArrayList<>();
		for (SubjectCriteria crit : SubjectCriteria.values()) {
			String converted = SubjectCriteria.convertToDatabase(crit);
			if (converted != null) {
				String query = QueryFactory.getStandardSelectQuery(DBVariables.SUBJECTS_TABLE, match, exact, converted);
				ResultSet results = DBConnetcor.executeQuery(query);
				List<Subject> search = Arrays.asList(ObjectConverter.convertToSubjects(results));
				for (Subject subject : search) {
					if (!subject.matchesAny(subjects.toArray(new Subject[0])))
						subjects.add(subject);
				}
			}
		}
		return subjects.toArray(new Subject[0]);
	}

}
