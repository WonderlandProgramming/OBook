package wonderland.main;

import wonderland.components.reader.ObjectQueue;
import wonderland.general.core.Book;
import wonderland.general.core.BookBuilder;
import wonderland.general.core.Category;
import wonderland.general.core.Subject;
import wonderland.search.BookSearch;
import wonderland.search.SubjectSearch;
import wonderland.search.criteria.BookCriteria;

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
		
		Category c = new Category("Naturwissenschaften", "NW");
		c.addToList();
		Category c1 = new Category("Sprachen", "SP");
		c1.addToList();
		Category c2 = new Category("Religion", "REL");
		c2.addToList();
		Category c3 = new Category("Sonstiges", "SNG");
		c3.addToList();
		
		Subject s = new Subject("Mathe", "MA", c);
		s.addToList();
		Subject s1 = new Subject("Englisch", "EN", c1);
		s1.addToList();
		Subject s2 = new Subject("Deutsch", "DE", c1);
		s2.addToList();
		Subject s3 = new Subject("Sport", "SP", c3);
		s3.addToList();
		Subject s4 = new Subject("Kath. Religion", "KREL", c2);
		s4.addToList();
		Subject s5 = new Subject("Evang. Religion", "EREL", c2);
		s5.addToList();
		
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
		
		BookCriteria[] crit = { BookCriteria.SUBJECT };
		
		Book[] books = BookSearch.getBooks("MA", crit);
		for (Book book : books) {
			System.out.println(book.toString());
		}
		
		new ObjectQueue(null, 6, 1000);
		
	}

}
