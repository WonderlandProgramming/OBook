package main.java.wonderland.components.writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import main.java.wonderland.general.core.Book;
import main.java.wonderland.general.core.BookGroup;
import main.java.wonderland.general.core.Grade;

/**
 * Serves a a control unit for two lists containing the suggested and currently
 * selected books.
 * 
 * @author Lukas Kannenberg
 * @since 15-10-2015
 * @version 15-10-2015 22:50
 *
 */
public class ListControl {
	
	private Grade grade;
	private int defaultSize;
	private List<Book> suggestedBooks = new ArrayList<>();
	private List<Book> selectedBooks = new ArrayList<>();

	/**
	 * Constructs a new ListControl. The Grade is used to calculate suggested
	 * books. After construction the lists will be initialized based on the
	 * grades standard.
	 * 
	 * @param grade the grade
	 */
	public ListControl(Grade grade, int defaultSize) {
		this.grade = grade;
		this.defaultSize = defaultSize;
		initialiseLists();
	}

	/**
	 * Removes a book from the suggested book list and adds it to the selected
	 * book list. The suggestions from the suggested book list will be updated
	 * afterwards.
	 * 
	 * @param book the book
	 */
	public void switchItem(Book book) {
		if (suggestedBooks.contains(book)) {
			suggestedBooks.remove(book);
			selectedBooks.add(book);
		} else if (selectedBooks.contains(book)) {
			selectedBooks.remove(book);
			suggestedBooks.add(book);
		} else {
			System.err.println("The BookItem to switch is not contained in either list.");
		}
		updateSuggestedBooks();
	}

	/**
	 * Initializes the lists.
	 */
	private void initialiseLists() {
		if (grade == null)
			return;
		if (grade.hasBooks()) {
			Book[] books = grade.getBooks().getBooks();
			selectedBooks.clear();
			selectedBooks.addAll(Arrays.asList(books));
		}
		if (grade.hasBookGroups()) {
			BookGroup[] bookGroups = grade.getBookGroups();
			suggestedBooks.clear();
			for (BookGroup bookGroup : bookGroups) {
				suggestedBooks.addAll(Arrays.asList(bookGroup.getBooks()));
			}
		}
		addFromStatistics(defaultSize - selectedBooks.size());
	}

	/**
	 * Updates the suggested books.
	 */
	private void updateSuggestedBooks() {
		if (grade == null)
			return;
		if (grade.hasBooks()) {
			Book[] books = grade.getBooks().getBooks();
			for (Book book : books) {
				if (!selectedBooks.contains(book) && !suggestedBooks.contains(book)) {
					suggestedBooks.add(book);
				}
			}
		}
		if (grade.hasBookGroups()) {
			BookGroup[] bookGroups = grade.getBookGroups();
			for (BookGroup bookGroup : bookGroups) {
				if (getBookAmountSelected(bookGroup) >= bookGroup.getMinimumSelected()) {
					removeInSuggested(bookGroup);
				} else {
					addRemainingInSuggested(bookGroup);
				}
			}
		}
		addFromStatistics(defaultSize - selectedBooks.size());
	}
	
	private void addFromStatistics(int amount) {
		int[] array = { 1000*60*5, 1000*60*10, 1000*60*30 };
		HashMap<Book, Integer> amounts = new HashMap<>();
		for (Book book : grade.getBooks().getBooks()) {
			amounts.put(book, 0);
			for (int i = 0; i < array.length; i++) {
				int result = StatisticalAnalyt.getAmountPastTime(grade, book, array[i]);
				amounts.put(book, amounts.get(book) + result);
			}
		}
		//TODO
	}

	/**
	 * Searches and returns the amount of BookItems from a specific BookGroup
	 * contained in the selectedBook list.
	 * 
	 * @param group the the book group
	 * @return the amount of BookItems from a specific BookGroup contained in
	 *         the selectedBook list
	 */
	private int getBookAmountSelected(BookGroup group) {
		int contained = 0;
		for (Book book : selectedBooks) {
			if (group.containsBook(book.getID())) {
				contained++;
			}
		}
		return contained;
	}

	/**
	 * Removes all books of a book group in the suggested book list.
	 * 
	 * @param group the book group
	 */
	private void removeInSuggested(BookGroup group) {
		for (Book bookItem : group.getBooks()) {
			if (suggestedBooks.contains(bookItem)) {
				suggestedBooks.remove(bookItem);
			}
		}
	}

	/**
	 * Adds each book of a book group to the selected book list if it is not
	 * contained in the suggested book list nor in the contained book list.
	 * 
	 * @param group
	 */
	private void addRemainingInSuggested(BookGroup group) {
		for (Book bookItem : group.getBooks()) {
			if (!suggestedBooks.contains(bookItem)) {
				suggestedBooks.add(bookItem);
			}
		}
	}

	/**
	 * @return the grade
	 */
	public Grade getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
		initialiseLists();
	}

	/**
	 * @param book the book to add
	 */
	public void addInSelected(Book book) {
		selectedBooks.add(book);
		updateSuggestedBooks();
	}

	/**
	 * @param book the book
	 */
	public void removeInSelected(Book book) {
		selectedBooks.remove(book);
		updateSuggestedBooks();
	}

	/**
	 * @return the suggested books
	 */
	public Book[] getSuggestedBooks() {
		return suggestedBooks.toArray(new Book[0]);
	}

	/**
	 * @return the suggested books
	 */
	public Book[] getSelectedBooks() {
		return selectedBooks.toArray(new Book[0]);
	}

}