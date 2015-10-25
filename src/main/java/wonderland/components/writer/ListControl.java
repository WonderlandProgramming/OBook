package main.java.wonderland.components.writer;

import java.util.ArrayList;
import java.util.List;

import main.java.wonderland.general.core.Book;
import main.java.wonderland.general.core.BookGroup;
import main.java.wonderland.general.core.Grade;

/**
 * Controls the given and selected book lists in the writer component.
 * 
 * @author Lukas Kannenberg
 * @since 15-10-2015
 * @version 15-10-2015 22:50
 *
 */
public class ListControl {

	private Grade grade;
	private List<Book> givenBooks = new ArrayList<>();
	private List<Book> selectedBooks = new ArrayList<>();

	/**
	 * Main Constructor.
	 */
	public ListControl(Grade grade) {
		this.grade = grade;
		initialiseLists();
	}

	/**
	 * Initializes the lists.
	 */
	private void initialiseLists() {
		if (grade == null)
			return;
		Book[] books = grade.getBooks().getBooks();
		BookGroup[] bookGroups = grade.getBookGroups();
		givenBooks.clear();
		selectedBooks.clear();
		// Add Preset BookItems
		for (Book book : books) {
			selectedBooks.add(book);
		}
		// Add BookGroups
		for (BookGroup bookGroup : bookGroups) {
			Book[] items = bookGroup.getBooks();
			for (Book bookItem : items) {
				givenBooks.add(bookItem);
			}
		}
	}

	/**
	 * Updates the given lists.
	 */
	public void updateGivenBooks() {
		if (grade == null)
			return;
		Book[] books = grade.getBooks().getBooks();
		BookGroup[] bookGroups = grade.getBookGroups();
		// Remove / Add BookGroup BookItems from Minimum value
		for (BookGroup bookGroup : bookGroups) {
			if (getInSelected(bookGroup) >= bookGroup.getMinimumSelected()) {
				removeInGiven(bookGroup);
			} else {
				addRemainingInGiven(bookGroup);
			}
		}
		// Add Preset BookItems to Given if not in Selected
		for (Book book : books) {
			if (!selectedBooks.contains(book) && !givenBooks.contains(book)) {
				givenBooks.add(book);
			}
		}
	}

	/**
	 * Searches and returns the amount of BookItems from a specific BookGroup
	 * contained in the selectedBook list.
	 * 
	 * @param group the the book group
	 * @return the amount of BookItems from a specific BookGroup contained in
	 *         the selectedBook list
	 */
	private int getInSelected(BookGroup group) {
		int contained = 0;
		for (Book book : selectedBooks) {
			if (group.containsBookItem(book)) {
				contained++;
			}
		}
		return contained;
	}

	/**
	 * Removes all BookItems from a specific BookGroup in the selected
	 * 
	 * @param group
	*/
	private void removeInGiven(BookGroup group) {
		for (Book bookItem : group.getBooks()) {
			if (selectedBooks.contains(bookItem)) {
				selectedBooks.remove(bookItem);
			}
		}
	}

	private void addRemainingInGiven(BookGroup group) {
		for (Book bookItem : group.getBooks()) {
			if (!givenBooks.contains(bookItem) && !selectedBooks.contains(bookItem)) {
				givenBooks.add(bookItem);
			}
		}
	}

	public void switchItem(Book book) {
		if (givenBooks.contains(book)) {
			givenBooks.remove(book);
			selectedBooks.add(book);
		} else if (selectedBooks.contains(book)) {
			selectedBooks.remove(book);
			givenBooks.add(book);
		} else {
			System.err.println("The BookItem to switch is not contained in either list.");
		}
	}

	public void removeItem(Book book) {
		if (givenBooks.contains(book)) {
			givenBooks.remove(book);
		} else if (selectedBooks.contains(book)) {
			selectedBooks.remove(book);
		} else {
			System.err.println("The BookItem to remove is not contained in either list.");
		}
	}
	
	public void updateGrade(Grade grade) {
		setGrade(grade);
		initialiseLists();
	}

	/**
	 * @return the grade
	 */
	public Grade getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	private void setGrade(Grade grade) {
		this.grade = grade;
	}

}