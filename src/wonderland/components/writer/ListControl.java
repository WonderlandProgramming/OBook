package wonderland.components.writer;

import java.util.ArrayList;
import java.util.List;

import wonderland.general.core.BookGroup;
import wonderland.general.core.BookItem;
import wonderland.general.core.Grade;

public class ListControl {

	private Grade grade;
	private List<BookItem> givenBooks = new ArrayList<>();
	private List<BookItem> selectedBooks = new ArrayList<>();

	/**
	 * Main Constructor.
	 */
	public ListControl(Grade grade) {
		this.grade = grade;
	}

	/**
	 * First initialization of the lists.
	 */
	public void initialiseLists() {
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
	 * 
	 * @param grade
	 */
	public void updateGivenBooks() {
		if (grade == null)
			return;
		BookItem[] books = grade.getBookItems();
		BookGroup[] bookGroups = grade.getBookGroups();
		// Remove / Add BookGroup BookItems from Minimum value
		for (BookGroup bookGroup : bookGroups) {
			if(getInSelected(bookGroup) >= bookGroup.getMinimumSelected()) {
				removeInSelected(bookGroup);
			} else {
				addRemainingInSelected(bookGroup);
			}
		}
		// Add Preset BookItems to Given if not in Selected
		for (BookItem bookItem : books) {
			if(!selectedBooks.contains(bookItem) && !givenBooks.contains(bookItem)) {
				givenBooks.add(bookItem);
			}
		}
	}

	private int getInSelected(BookGroup group) {
		int contained = 0;
		for (BookItem bookItem : selectedBooks) {
			if (group.containsBookItem(bookItem)) {
				contained++;
			}
		}
		return contained;
	}
	
	private void removeInSelected(BookGroup group) {
		for (BookItem bookItem : group.getBookItems()) {
			if(selectedBooks.contains(bookItem)) {
				selectedBooks.remove(bookItem);
			}
		}
	}
	
	private void addRemainingInSelected(BookGroup group) {
		for (BookItem bookItem : group.getBookItems()) {
			if(!givenBooks.contains(bookItem) && !selectedBooks.contains(bookItem)) {
				givenBooks.add(bookItem);
			}
		}
	}
	
	public void switchItem(BookItem item) {
		if(givenBooks.contains(item)) {
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
		if(givenBooks.contains(item)) {
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
