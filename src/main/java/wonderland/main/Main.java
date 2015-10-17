package main.java.wonderland.main;

import java.awt.Color;

import main.java.wonderland.general.core.Book;
import main.java.wonderland.general.core.BookBuilder;
import main.java.wonderland.general.core.BookGroup;
import main.java.wonderland.general.core.BookItem;
import main.java.wonderland.general.core.Category;
import main.java.wonderland.general.core.ColorManager;
import main.java.wonderland.general.core.Grade;
import main.java.wonderland.general.core.Subject;
import main.java.wonderland.search.BookSearch;
import main.java.wonderland.search.SubjectSearch;
import main.java.wonderland.search.criteria.BookCriteria;
import main.java.wonderland.webServer.WebServer;
import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.ConfigPage;
import main.java.wonderland.webServer.page.LoginPage;
import main.java.wonderland.webServer.page.MainPage;
import main.java.wonderland.webServer.page.TestPage;

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
		
		// Creating Categories
		Category c = new Category("Naturwissenschaften", "NW", new Color(16, 78, 89));
		c.addToList();
		Category c1 = new Category("Sprachen", "SP");
		c1.addToList();
		Category c2 = new Category("Religion", "REL", new Color(122, 78, 89));
		c2.addToList();
		Category c3 = new Category("Sonstiges", "SNG");
		c3.addToList();
		
		// Creating Subjects
		Subject s = new Subject("Mathe", "MA", c, null);
		s.addToList();
		Subject s1 = new Subject("Englisch", "EN", c1, null);
		s1.addToList();
		Subject s2 = new Subject("Deutsch", "DE", c1, new Color(10, 10, 10));
		s2.addToList();
		Subject s3 = new Subject("Sport", "SP", c3, new Color(10, 10, 10));
		s3.addToList();
		Subject s4 = new Subject("Kath. Religion", "KREL", c2, new Color(10, 10, 10));
		s4.addToList();
		Subject s5 = new Subject("Evang. Religion", "EREL", c2, new Color(10, 10, 10));
		s5.addToList();
		
		// Creating Books
		Book b = new BookBuilder("001").withName("Mathe 5").withSubject(SubjectSearch.getFromShortName("MA")).built();
		b.addToList();
		Book b1 = new BookBuilder("002").withName("Deutsch 6").withSubject(SubjectSearch.getFromShortName("DE")).built();
		b1.addToList();
		Book b2 = new BookBuilder("003").withName("Religion Katholisch").withSubject(SubjectSearch.getFromShortName("KREL")).built();
		b2.addToList();
		Book b3 = new BookBuilder("004").withName("Sport - Fit bleiben").withSubject(SubjectSearch.getFromShortName("SP")).built();
		b3.addToList();
		Book b4 = new BookBuilder("005").withName("Mathe 10").withSubject(SubjectSearch.getFromShortName("MA")).built();
		b4.addToList();
		
		// Creating Grades / Book Groups
		Grade g = new Grade("Klasse 5", "K5");
		g.addBookItem(new BookItem(b, g, true));
		BookItem bi = new BookItem(b, g, true);
		BookItem bi1 = new BookItem(b, g, true);
		BookItem bi2 = new BookItem(b, g, true);
		BookItem[] bookItems = { bi, bi1, bi2 };
		g.addBookGroup(new BookGroup("Sonstiges", bookItems, new Color(122, 78, 89), 1));
		System.out.println(g.toString());
		for (BookItem bookItem : g.getBookItems()) {
			System.out.println(bookItem.toString());
		}
		
		// Search Engine Test
		BookCriteria[] crit = { BookCriteria.SUBJECT };
		
		Book[] books = BookSearch.getBooks("Mathe", crit);
		for (Book book : books) {
			System.out.println(book.toString());
		}
		
		// ColorManager Test
		System.out.println(ColorManager.getResultingColor(g, bi));
		
		
		WebServer web = new WebServer();
		WebServer.addUser(new User("admin", "admin"), LoginLevel.SuperAdministrator);
		WebServer.addUser(new User("schreiben", "schreiben"), LoginLevel.Schreiben);
		
		web.addPage(new LoginPage());
		web.addPage(new MainPage());
		web.addPage(new ConfigPage());
		web.addPage(new TestPage());
	}

}
