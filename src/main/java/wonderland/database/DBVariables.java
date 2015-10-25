package main.java.wonderland.database;

public class DBVariables {

	// General
	public static String DB_KEYSPACE = "obook";

	// Books
	public static String BOOKS_TABLE = "books";
	public static String BOOKS_ID = "id";
	public static String BOOKS_NAME = "name";
	public static String BOOKS_SUBJECT = "subject";
	public static String BOOKS_COVER = "cover";

	// Subjects
	public static String SUBJECTS_TABLE = "subjects";
	public static String SUBJECTS_NAME = "name";
	public static String SUBJECTS_SHORTNAME = "shortName";
	public static String SUBJECTS_CATEGORY = "category";
	public static String SUBJECTS_COLOR = "color";

	// Grades
	public static String GRADES_TABLE = "grades";
	public static String GRADES_NAME = "name";
	public static String GRADES_SHORTNAME = "shortName";
	public static String GRADES_BOOKITEMS = "bookItems";
	public static String GRADES_BOOKGROUPS = "bookGroups";

	// Categories
	public static String CATEGORIES_TABLE = "categories";
	public static String CATEGORIES_NAME = "name";
	public static String CATEGORIES_SHORTNAME = "shortName";
	public static String CATEGORIES_COLOR = "color";

	// Order Entries
	public static String ORDERS_TABLE = "orders";
	public static String ORDERS_ID = "id";
	public static String ORDERS_NUMBER = "number";
	public static String ORDERS_GRADE = "grade";
	public static String ORDERS_STATUS = "status";
	public static String ORDERS_BOOKS = "books";
	public static String ORDERS_TIMESTAMPS = "timestamps";
	
	// Book Groups
	public static String BOOKGROUPS_NAME = "name";
	public static String BOOKGROUPS_COLOR = "color";
	public static String BOOKGROUPS_BOOKS = "books";
	public static String BOOKGROUPS_MINSELEC = "minSelec";

}
