package wonderland.storing;

import wonderland.general.core.Book;
import wonderland.general.core.Category;
import wonderland.general.core.Grade;
import wonderland.general.core.Subject;

/**
 * Reads data from local disk.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 15:16
 *
 */
public class ContentReader {

	/**
	 * Main Constructor.
	 * 
	 * @param path the path
	 */
	public ContentReader() {
		
	}
	
	public void loadContent() {
		//TODO
		addAllBooks(getAllBooksFromFile());
		addAllCategories(getAllCategoriesFromFile());
		addAllSubjects(getAllSubjectsFromFile());
		addAllGrades(getAllGradesFromFile());
		
	}
	
	/**
	 * Fetches all Books from file.
	 * 
	 * @return
	 */
	private Book[] getAllBooksFromFile() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Fetches all Categories from file.
	 * 
	 * @return
	 */
	private Category[] getAllCategoriesFromFile() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Fetches all Subjects from file.
	 * 
	 * @return
	 */
	private Subject[] getAllSubjectsFromFile() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Fetches all Grades form file.
	 * 
	 * @return
	 */
	private Grade[] getAllGradesFromFile() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Adds target books to the list.
	 * 
	 * @param books
	 */
	private void addAllBooks(Book[] books) {
		for (Book book : books) {
			book.addToList();
		}
	}
	
	/**
	 * Adds target categories to the list.
	 * 
	 * @param books
	 */
	private void addAllCategories(Category[] categories) {
		for (Category category : categories) {
			category.addToList();
		}
	}
	
	/**
	 * Adds target subjects to the list.
	 * 
	 * @param books
	 */
	private void addAllSubjects(Subject[] subjects) {
		for (Subject subject : subjects) {
			subject.addToList();
		}
	}
	
	/**
	 * Adds target grade to the list.
	 * 
	 * @param grades
	 */
	private void addAllGrades(Grade[] grades) {
		for (Grade grade : grades) {
			grade.addToList();
		}
	}
}
