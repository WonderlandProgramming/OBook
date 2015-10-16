package main.java.wonderland.components.writer;

import java.util.ArrayList;
import java.util.List;

import main.java.wonderland.general.core.BookGroup;
import main.java.wonderland.general.core.BookItem;
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
	private List<BookItem> givenBooks = new ArrayList<>();
	private List<BookItem> selectedBooks = new ArrayList<>();

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
		BookItem[] books = grade.getBookItems();
		BookGroup[] bookGroups = grade.getBookGroups();
		givenBooks.clear();
		selectedBooks.clear();
		// Add Preset BookItems
		for (BookItem bookItem : books) {
			selectedBooks.add(bookItem);
		}
		// Add BookGroups
		for (BookGroup bookGroup : bookGroups) {
			BookItem[] items = bookGroup.getBookItems();
			for (BookItem bookItem : items) {
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
		BookItem[] books = grade.getBookItems();
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
		for (BookItem bookItem : books) {
			if (!selectedBooks.contains(bookItem) && !givenBooks.contains(bookItem)) {
				givenBooks.add(bookItem);
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
		for (BookItem bookItem : selectedBooks) {
			if (group.containsBookItem(bookItem)) {
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
		for (BookItem bookItem : group.getBookItems()) {
			if (selectedBooks.contains(bookItem)) {
				selectedBooks.remove(bookItem);
			}
		}
	}

	private void addRemainingInGiven(BookGroup group) {
		for (BookItem bookItem : group.getBookItems()) {
			if (!givenBooks.contains(bookItem) && !selectedBooks.contains(bookItem)) {
				givenBooks.add(bookItem);
			}
		}
	}

	public void switchItem(BookItem item) {
		if (givenBooks.contains(item)) {
			givenBooks.remove(item);
			selectedBooks.add(item);
		} else if (selectedBooks.contains(item)) {
			selectedBooks.remove(item);
			givenBooks.add(item);
		} else {
			System.err.println("The BookItem to switch is not contained in either list.");
		}
	}

	public void removeItem(BookItem item) {
		if (givenBooks.contains(item)) {
			givenBooks.remove(item);
		} else if (selectedBooks.contains(item)) {
			selectedBooks.remove(item);
		} else {
			System.err.println("The BookItem to remove is not contained in either list.");
		}
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
	public void setGrade(Grade grade) {
		this.grade = grade;
	}

}