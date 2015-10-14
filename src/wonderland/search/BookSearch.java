package wonderland.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wonderland.general.ConLibrary;
import wonderland.general.core.Book;
import wonderland.general.core.Category;
import wonderland.general.core.Subject;
import wonderland.search.criteria.BookCriteria;
import wonderland.util.ListUtility;

public class BookSearch {
	
	/**
	 * Searches for a books using specified criteria.
	 * 
	 * @param name the name
	 * @return the book if a match was found, otherwise false
	 */
	public static Book[] getBooks(String text, BookCriteria... criteria) {
		List<BookCriteria> search = Arrays.asList(criteria);
		List<Book> results = new ArrayList<>();
		for (Book book : ConLibrary.getBooks()) {
			if (ListUtility.listContainsOne(search, BookCriteria.ID, BookCriteria.ALL)) {
				if(book.getID().contains(text) && !results.contains(book)) results.add(book);
			}
			if (ListUtility.listContainsOne(search, BookCriteria.NAME, BookCriteria.ALL)) {
				if(searchName(text, book) && !results.contains(book)) results.add(book);
			}
			if (ListUtility.listContainsOne(search, BookCriteria.CATEGORY, BookCriteria.ALL)) {
				if(searchCategory(text, book) && !results.contains(book)) results.add(book);
			}
			if (ListUtility.listContainsOne(search, BookCriteria.SUBJECT, BookCriteria.ALL)) {
				if(searchSubject(text, book) && !results.contains(book)) results.add(book);
			}
		}
		return results.toArray(new Book[0]);
	}
	
	/**
	 * Searches if the book name matches a specified search string.
	 * 
	 * @param text the search text
	 * @param book the book
	 * @return true, if the book name matches the requirements, otherwise false
	 */
	private static boolean searchName(String text, Book book) {
		String name = book.getName();
		if(name != null && name.contains(text)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Searches if the book category matches a specified search string.
	 * 
	 * @param text the search text
	 * @param book the book
	 * @return true, if the book category matches the requirements, otherwise false
	 */
	private static boolean searchCategory(String text, Book book) {
		Category category = book.getSubject().getCategory();
		if(category != null && category.getName().contains(text)) {
			return true;
		} else if(category.getShortName().contains(text)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Searches if the book subject matches a specified search string.
	 * 
	 * @param text the search text
	 * @param book the book
	 * @return true, if the book subject matches the requirements, otherwise false
	 */
	private static boolean searchSubject(String text, Book book) {
		Subject subject = book.getSubject();
		if(subject != null && subject.getName().contains(text)) {
			return true;
		} else if(subject.getShortName().contains(text)) {
			return true;
		}
		return false;
	}
}
