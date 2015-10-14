package wonderland.general.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wonderland.general.ConLibrary;

public class Grade {
	
	private String name;
	private String shortName;
	private List<Book> books = new ArrayList<>();
	
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
	 * @param books the books assigned to this grade
	 */
	public Grade(String name, String shortName, Book[] books) {
		this.name = name;
		this.shortName = shortName;
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
	
	@Override
	public String toString() {
		return String.format("Grade[name=%s, shortName=%s, books=%s]", name, shortName, books.toString());
	}
}
