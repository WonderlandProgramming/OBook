package main.java.wonderland.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.wonderland.general.core.Book;
import main.java.wonderland.general.core.Category;
import main.java.wonderland.general.core.Grade;
import main.java.wonderland.general.core.OrderEntry;
import main.java.wonderland.general.core.Subject;

public class ObjectConverter {

	/**
	 * Converts given result set into an array of book objects.
	 * 
	 * @param results the result set
	 * @return an array of books
	 */
	public static Book[] convertToBooks(ResultSet results) {
		List<Book> books = new ArrayList<>();
		if (results == null) return new Book[0];
		try {
			while (results.next()) {
				String ID = results.getString(DBVariables.BOOKS_ID);
				String name = results.getString(DBVariables.BOOKS_NAME);
				String subject = results.getString(DBVariables.BOOKS_SUBJECT);
				String cover = results.getString(DBVariables.BOOKS_COVER);
				Book book = new Book(ID, name, new Subject(subject), cover);
				if (book.isValid())
					books.add(book);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return books.toArray(new Book[0]);
	}

	public static Subject[] convertToSubjects(ResultSet results) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Grade[] convertToGrades(ResultSet results) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Category[] convertToCategories(ResultSet results) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static OrderEntry[] convertToOrderEntry(ResultSet results) {
		// TODO
		return null;
	}
}
