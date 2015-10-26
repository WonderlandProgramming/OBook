package main.java.wonderland.main;

import main.java.wonderland.database.DBConnetcor;
import main.java.wonderland.database.action.DBBook;
import main.java.wonderland.database.criteria.BookCriteria;
import main.java.wonderland.general.core.Book;
import main.java.wonderland.webServer.WebServer;

/**
 * Application Main class.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 15:16
 *
 */
public class Main {

	public static void main(String[] args) {
		
		// Main
		DBConnetcor.defaultConnect();
		Book[] res = DBBook.getBooks("%", false, BookCriteria.ID);
		for (Book book : res) {
			System.out.println(book.toString());
		}
		System.out.println("------------------");
		
		new WebServer();
	}

}
