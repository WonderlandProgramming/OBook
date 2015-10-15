package main.java.wonderland.general;

import java.util.ArrayList;
import java.util.List;

import main.java.wonderland.general.core.Book;
import main.java.wonderland.general.core.Category;
import main.java.wonderland.general.core.Grade;
import main.java.wonderland.general.core.Subject;

/**
 * Holds the main content of the application.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 20:02
 *
 */
public class ConLibrary {

	private static List<Book> books = new ArrayList<>();
	private static List<Category> categories = new ArrayList<>();
	private static List<Grade> grades = new ArrayList<>();
	private static List<Subject> subjects = new ArrayList<>();
	
	/**
	 * @return the books
	 */
	public static List<Book> getBooks() {
		return books;
	}
	
	/**
	 * @return the categories
	 */
	public static List<Category> getCategories() {
		return categories;
	}
	
	/**
	 * @return the grades
	 */
	public static List<Grade> getGrades() {
		return grades;
	}
	
	/**
	 * @return the subjects
	 */
	public static List<Subject> getSubjects() {
		return subjects;
	}
	
}
