package main.java.wonderland.database;

import main.java.wonderland.general.core.Book;
import main.java.wonderland.general.core.Category;
import main.java.wonderland.general.core.Grade;
import main.java.wonderland.general.core.OrderEntry;
import main.java.wonderland.general.core.Subject;

public class QueryFactory {

	public static String getStandardSelectQuery(String table, String match, boolean exact, String criteria) {
		String operator = QueryFactory.getOperator(exact);
		return String.format("SELECT * FROM %s.%s WHERE %s %s \"%s\";", DBVariables.DB_KEYSPACE, table, criteria,
				operator, match);
	}

	public static String getBookInsertQuery(Book book) {
		return String.format("INSERT INTO %s.%s (%s,%s,%s,%s) VALUES (\"%s\",\"%s\",\"%s\",\"%s\");",
				DBVariables.DB_KEYSPACE, DBVariables.BOOKS_TABLE, DBVariables.BOOKS_ID, DBVariables.BOOKS_NAME,
				DBVariables.BOOKS_SUBJECT, DBVariables.BOOKS_COVER, book.getID(), book.getName(),
				book.getSubject().getName(), book.getCover());

	}

	public static String getBookSingleUpdateQuery(String ID, String criteria, String value) {
		return String.format("UPDATE %s.%s SET %s = \"%s\" WHERE %s = \"%s\";", DBVariables.DB_KEYSPACE,
				DBVariables.BOOKS_TABLE, criteria, value, DBVariables.BOOKS_ID, ID);

	}

	public static String getBookUpdateQuery(Book book) {
		return String.format("UPDATE %s.%s SET %s = \"%s\", %s = \"%s\", %s = \"%s\", %s = \"%s\" WHERE %s = \"%s\";",
				DBVariables.DB_KEYSPACE, DBVariables.BOOKS_TABLE, DBVariables.BOOKS_ID, book.getID(),
				DBVariables.BOOKS_NAME, book.getName(), DBVariables.BOOKS_SUBJECT, book.getSubject().getName(),
				DBVariables.BOOKS_COVER, book.getCover(), DBVariables.BOOKS_ID, book.getID());

	}

	public static String getBookDeleteQuery(String ID) {
		return String.format("DELETE FROM %s.%s WHERE %s = \"%s\";", DBVariables.DB_KEYSPACE, DBVariables.BOOKS_TABLE,
				DBVariables.BOOKS_ID, ID);

	}

	public static String getSubjectInsertQuery(Subject subject) {
		return String.format("INSERT INTO %s.%s (%s,%s,%s,%s) VALUES (\"%s\",\"%s\",\"%s\",\"%s\");",
				DBVariables.DB_KEYSPACE, DBVariables.SUBJECTS_TABLE, DBVariables.SUBJECTS_NAME,
				DBVariables.SUBJECTS_SHORTNAME, DBVariables.SUBJECTS_CATEGORY, DBVariables.SUBJECTS_COLOR,
				subject.getName(), subject.getShortName(), subject.getCategory().getName(),
				subject.getColor().toString());
	}

	public static String getSubjectSingleUpdateQuery(String name, String criteria, String value) {
		return String.format("UPDATE %s.%s SET %s = \"%s\" WHERE %s = \"%s\";", DBVariables.DB_KEYSPACE,
				DBVariables.SUBJECTS_TABLE, criteria, value, DBVariables.SUBJECTS_NAME, name);
	}

	public static String getSubjectUpdateQuery(Subject subject) {
		return String.format("UPDATE %s.%s SET %s = \"%s\", %s = \"%s\", %s = \"%s\", %s = \"%s\" WHERE %s = \"%s\";",
				DBVariables.DB_KEYSPACE, DBVariables.SUBJECTS_TABLE, DBVariables.SUBJECTS_NAME, subject.getName(),
				DBVariables.SUBJECTS_SHORTNAME, subject.getShortName(), DBVariables.SUBJECTS_CATEGORY,
				subject.getCategory().getName(), DBVariables.SUBJECTS_COLOR, subject.getColor().toString(),
				DBVariables.SUBJECTS_NAME, subject.getName());
	}

	public static String getSubjectDeleteQuery(String name) {
		return String.format("DELETE FROM %s.%s WHERE %s = \"%s\";", DBVariables.DB_KEYSPACE,
				DBVariables.SUBJECTS_TABLE, DBVariables.SUBJECTS_NAME, name);
	}

	public static String getCategoryInsertQuery(Category category) {
		return String.format("INSERT INTO %s.%s (%s,%s,%s) VALUES (\"%s\",\"%s\",\"%s\");", DBVariables.DB_KEYSPACE,
				DBVariables.CATEGORIES_TABLE, DBVariables.CATEGORIES_NAME, DBVariables.CATEGORIES_SHORTNAME,
				DBVariables.CATEGORIES_COLOR, category.getName(), category.getShortName(),
				category.getColor().toString());
	}

	public static String getCategorySingleUpdateQuery(String name, String criteria, String value) {
		return String.format("UPDATE %s.%s SET %s = \"%s\" WHERE %s = \"%s\";", DBVariables.DB_KEYSPACE,
				DBVariables.CATEGORIES_TABLE, criteria, value, DBVariables.CATEGORIES_NAME, name);
	}

	public static String getCategoryUpdateQuery(Category category) {
		return String.format("UPDATE %s.%s SET %s = \"%s\", %s = \"%s\" WHERE %s = \"%s\";", DBVariables.DB_KEYSPACE,
				DBVariables.CATEGORIES_TABLE, DBVariables.CATEGORIES_NAME, category.getName(),
				DBVariables.CATEGORIES_SHORTNAME, category.getShortName(), DBVariables.CATEGORIES_NAME,
				category.getName());
	}

	public static String getCategoryDeleteQuery(String name) {
		return String.format("DELETE FROM %s.%s WHERE %s = \"%s\";", DBVariables.DB_KEYSPACE,
				DBVariables.CATEGORIES_TABLE, DBVariables.CATEGORIES_NAME, name);
	}

	public static String getGradeInsertQuery(Grade grade) {
		return String.format("INSERT INTO %s.%s (%s,%s,%s,%s) VALUES (\"%s\",\"%s\",\"%s\",\"%s\");",
				DBVariables.DB_KEYSPACE, DBVariables.GRADES_TABLE, DBVariables.GRADES_NAME,
				DBVariables.GRADES_SHORTNAME, DBVariables.GRADES_BOOKITEMS, DBVariables.GRADES_BOOKGROUPS,
				grade.getName(), grade.getShortName(), grade.getBooks().getName(),
				Grade.getBookGroupsAsString(grade.getBookGroups()));
	}

	public static String getGradeSingleUpdateQuery(String name, String criteria, String value) {
		return String.format("UPDATE %s.%s SET %s = \"%s\" WHERE %s = \"%s\";", DBVariables.DB_KEYSPACE,
				DBVariables.GRADES_TABLE, criteria, value, DBVariables.GRADES_NAME, name);
	}

	public static String getGradeUpdateQuery(Grade grade) {
		return String.format("UPDATE %s.%s SET %s = \"%s\", %s = \"%s\", %s = \"%s\", %s = \"%s\" WHERE %s = \"%s\";",
				DBVariables.DB_KEYSPACE, DBVariables.GRADES_TABLE, DBVariables.GRADES_NAME, grade.getName(),
				DBVariables.GRADES_SHORTNAME, grade.getShortName(), DBVariables.GRADES_BOOKITEMS,
				grade.getBooks().getName(), DBVariables.GRADES_BOOKGROUPS,
				Grade.getBookGroupsAsString(grade.getBookGroups()), DBVariables.GRADES_NAME, grade.getName());
	}

	public static String getGradeDeleteQuery(String name) {
		return String.format("DELETE FROM %s.%s WHERE %s = \"%s\";", DBVariables.DB_KEYSPACE, DBVariables.GRADES_TABLE,
				DBVariables.GRADES_NAME, name);
	}

	public static String getOrderEntryInsertQuery(OrderEntry order) {
		return String.format(
				"INSERT INTO %s.%s (%s,%s,%s,%s,%s,%s) VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",
				DBVariables.DB_KEYSPACE, DBVariables.ORDERS_TABLE, DBVariables.ORDERS_ID, DBVariables.ORDERS_BOOKS,
				DBVariables.ORDERS_TIMESTAMPS, order.getID(), order.getNumber(), order.getGrade().getName(),
				order.getStatus().toString(), order.getBooksAsJSon(), order.getTimestampsAsJSon());
	}

	public static String getOrderEntrySingleUpdateQuery(String id, String criteria, String value) {
		return String.format("UPDATE %s.%s SET %s = \"%s\" WHERE %s = \"%s\";", DBVariables.DB_KEYSPACE,
				DBVariables.ORDERS_TABLE, criteria, value, DBVariables.ORDERS_ID, id);
	}

	public static String getOrderEntryUpdateQuery(OrderEntry order) {
		return String.format(
				"UPDATE %s.%s SET %s = \"%s\", %s = \"%s\", %s = \"%s\", %s = \"%s\",%s = \"%s\",%s = \"%s\" WHERE %s = \"%s\";",
				DBVariables.DB_KEYSPACE, DBVariables.ORDERS_TABLE, DBVariables.ORDERS_ID, order.getID(),
				DBVariables.ORDERS_NUMBER, order.getNumber(), DBVariables.ORDERS_GRADE, order.getGrade().getName(),
				DBVariables.ORDERS_STATUS, order.getStatus().toString(), DBVariables.ORDERS_BOOKS,
				order.getBooksAsJSon(), DBVariables.ORDERS_TIMESTAMPS, order.getTimestampsAsJSon(),
				DBVariables.ORDERS_ID, order.getID());
	}

	public static String getOrderEntryDeleteQuery(String id) {
		return String.format("DELETE FROM %s.%s WHERE %s = \"%s\";", DBVariables.DB_KEYSPACE, DBVariables.ORDERS_TABLE,
				DBVariables.ORDERS_ID, id);
	}

	private static String getOperator(boolean exact) {
		if (exact)
			return "=";
		else
			return "LIKE";
	}

}
