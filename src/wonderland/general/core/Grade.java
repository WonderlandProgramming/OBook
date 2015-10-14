package wonderland.general.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wonderland.general.ConLibrary;

/**
 * Represents a normal grade.
 * 
 * @author Lukas Kannenberg
 * @since 15-10-2015
 * @version 15-10-2015 01:23
 *
 */
public class Grade {
	
	private String name;
	private String shortName;
	private List<Book> books = new ArrayList<>();
	private List<BookGroup> bookGroups = new ArrayList<>();
	
	/**
	 * Constructs a new BookCategory.
	 * 
	 * @param name the name
	 */
	public Grade(String name) {
		this.name = name;
	}
	
	/**
	 * Constructs a new BookCategory.
	 * 
	 * @param name the name
	 * @param shortName the short name
	 */
	public Grade(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}
	
	/**
	 * Constructs a new BookCategory.
	 * 
	 * @param name the name
	 * @param shortName the short name
	 * @param books the books to be added
	 * @param boookGroup the bookGroup to be added
	 */
	public Grade(String name, String shortName, Book[] books, BookGroup[] bookGroups) {
		this.name = name;
		this.shortName = shortName;
		if(books != null) this.books = Arrays.asList(books);
		if(bookGroups != null) this.bookGroups = Arrays.asList(bookGroups);
	}
	
	/**
	 * Adds the grade to the list.
	 */
	public void addToList() {
		if (hasName()) {
			ConLibrary.getGrades().add(this);
		} else {
			System.err.println("Book Category cannot be added to the list: Invalid name.");
		}
	}

	/**
	 * Removes the grade from the list.
	 */
	public void removeFromList() {
		ConLibrary.getGrades().remove(this);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return true, if the name has been set, otherwise false
	 */
	public boolean hasName() {
		if(name != null && !name.isEmpty()) return true;
		return false;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	/**
	 * @return true, if the short name has been set, otherwise false
	 */
	public boolean hasShortName() {
		if(shortName != null && !shortName.isEmpty()) return true;
		return false;
	}
	
	/**
	 * @return the the books
	 */
	public Book[] getBooks() {
		return books.toArray(new Book[0]);
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(Book[] books) {
		if(books != null) this.books = Arrays.asList(books);
	}
	
	/**
	 * @return true, if the list contains any books, otherwise false
	 */
	public boolean hasBooks() {
		if(!books.isEmpty()) return true;
		return false;
	}
	
	/**
	 * Adds a book to the current list.
	 * 
	 * @param book the book
	 */
	public void addBook(Book book) {
		books.add(book);
	}
	
	/**
	 * Adds books to the current list.
	 * 
	 * @param book the book
	 */
	public void addBooks(Book[] books) {
		if(books != null) {
			List<Book> temp = Arrays.asList(books);
			this.books.addAll(temp);
		}	
	}
	
	/**
	 * Removes a book from the list.
	 * 
	 * @param book the book to remove
	 */
	public void removeBook(Book book) {
		if(books.contains(book)) books.remove(book);
	}
	
	/**
	 * @return the the books
	 */
	public BookGroup[] getBookGroups() {
		return bookGroups.toArray(new BookGroup[0]);
	}

	/**
	 * @param books the books to set
	 */
	public void setBookGroups(BookGroup[] bookGroup) {
		if(bookGroups != null) this.bookGroups = Arrays.asList(bookGroup);
	}
	
	/**
	 * @return true, if the list contains any books, otherwise false
	 */
	public boolean hasBookGroups() {
		if(!bookGroups.isEmpty()) return true;
		return false;
	}
	
	/**
	 * Adds a book to the current list.
	 * 
	 * @param book the book
	 */
	public void addBookGroup(BookGroup bookGroup) {
		bookGroups.add(bookGroup);
	}
	
	/**
	 * Adds books to the current list.
	 * 
	 * @param book the book
	 */
	public void addBookGroups(BookGroup[] bookGroups) {
		if(bookGroups != null) {
			List<BookGroup> temp = Arrays.asList(bookGroups);
			this.bookGroups.addAll(temp);
		}	
	}
	
	/**
	 * Removes a book from the list.
	 * 
	 * @param book the book to remove
	 */
	public void removeBookGroup(BookGroup bookGroup) {
		books.remove(bookGroup);
	}
	
	@Override
	public String toString() {
		return String.format("Grade[name=%s, shortName=%s, books=%s, bookGroups=%s]", name, shortName, books.toString(), bookGroups.toString());
	}
}
