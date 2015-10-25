package main.java.wonderland.database.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.wonderland.database.DBConnetcor;
import main.java.wonderland.database.DBVariables;
import main.java.wonderland.database.ObjectConverter;
import main.java.wonderland.database.QueryFactory;
import main.java.wonderland.database.criteria.BookCriteria;
import main.java.wonderland.general.core.Book;

/**
 * Database Access class for book objects.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 24-10-2015 22:12
 *
 */
public class DBBook {

	/**
	 * Accesses the database and returns all existing books.
	 * 
	 * @return all existing books
	 */
	public static Book[] getAll() {
		return getBooks("%", false, BookCriteria.ID);
	}
	
	/**
	 * Accesses the database and returns a book with a given ID if existing.
	 * 
	 * @param ID the ID of the book
	 * @return the Book if existing, otherwise null
	 */
	public static Book getBookByID(String ID) {
		Book[] books = getBooksSingleCriteria(ID, true, BookCriteria.ID);
		if (books != null && books.length > 0)
			return books[0];
		return null;
	}

	/**
	 * Accesses the database and inserts a new book if the ID is still unused.
	 * 
	 * @param book the book
	 */
	public static void insertBook(Book book) {
		DBConnetcor.executeUpdate(QueryFactory.getBookInsertQuery(book));
	}

	/**
	 * Accesses the database and removes a book with a given ID if existing.
	 * 
	 * @param ID the ID of the book
	 */
	public static void removeBookByID(String ID) {
		DBConnetcor.executeUpdate(QueryFactory.getBookDeleteQuery(ID));
	}

	/**
	 * Accesses the database and updates a book if existing. Please keep in mind
	 * that all values of the database entry will be replaced with new values
	 * from the book.
	 * 
	 * @param book the book
	 */
	public static void updateBook(Book book) {
		DBConnetcor.executeUpdate(QueryFactory.getBookUpdateQuery(book));
	}

	/**
	 * 
	 * Accesses the database and returns an array of books that matches the given
	 * search criteria.
	 * 
	 * @param match the string to search for
	 * @param exact true if the match has to be exact (search string has to
	 *        match to the compared string completely) or false if the match has
	 *        to be like the compared string
	 * @param criteria the criteria to search for
	 * @return an array containing the search results (an empty array if no
	 *         results found) or null if the search criteria is invalid
	 */
	public static Book[] getBooks(String match, boolean exact, BookCriteria criteria) {
		if (criteria == BookCriteria.ALL) {
			return getBooksAllCriteria(match, exact);
		} else if (BookCriteria.convertToDatabase(criteria) != null) {
			return getBooksSingleCriteria(match, exact, criteria);
		}
		return null;
	}

	/**
	 * Accesses the database and returns an array of books that matches the given
	 * search criteria.
	 * 
	 * @param match the string to search for
	 * @param exact true if the match has to be exact (search string has to
	 *        match to the compared string completely) or false if the match has
	 *        to be like the compared string
	 * @param criteria the criteria to search for
	 * @return
	 */
	private static Book[] getBooksSingleCriteria(String match, boolean exact, BookCriteria criteria) {
		String converted = BookCriteria.convertToDatabase(criteria);
		String query = QueryFactory.getStandardSelectQuery(DBVariables.BOOKS_TABLE, match, exact, converted);
		ResultSet results = DBConnetcor.executeQuery(query);
		return ObjectConverter.convertToBooks(results);
	}

	/**
	 * Accesses the database and returns an array of books that matches the given
	 * search criteria.
	 * 
	 * @param match the string to search for
	 * @param exact true if the match has to be exact (search string has to
	 *        match to the compared string completely) or false if the match has
	 *        to be like the compared string
	 * @return
	 */
	private static Book[] getBooksAllCriteria(String match, boolean exact) {
		List<Book> books = new ArrayList<>();
		for (BookCriteria crit : BookCriteria.values()) {
			String converted = BookCriteria.convertToDatabase(crit);
			if (converted != null) {
				String query = QueryFactory.getStandardSelectQuery(DBVariables.BOOKS_TABLE, match, exact, converted);
				ResultSet results = DBConnetcor.executeQuery(query);
				List<Book> search = Arrays.asList(ObjectConverter.convertToBooks(results));
				for (Book book : search) {
					if (!book.matchesAny(books.toArray(new Book[0])))
						books.add(book);
				}
			}
		}
		return books.toArray(new Book[0]);
	}
}
