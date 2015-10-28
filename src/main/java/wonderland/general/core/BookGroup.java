package main.java.wonderland.general.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.wonderland.general.UIDGenerator;

/**
 * Represents a group of books for a grade.
 * 
 * @author Lukas Kannenberg
 * @since 15-10-2015
 * @version 15-10-2015 00:52
 *
 */
public class BookGroup extends Serial {

	// Logger
	private static final Logger log = LogManager.getLogger(BookGroup.class.getName());

	private String name;
	private List<Book> books = new ArrayList<>();
	private Color color;

	// Form Settings
	private int minimumSelected = -1;

	/**
	 * Main Constructor.
	 * 
	 * @param name the name
	 * @param books the books
	 * @param color the color
	 * @param minimum the minimum
	 */
	public BookGroup(String name, Book[] books, Color color, int minimum) {
		setID(UIDGenerator.genNextID());
		this.name = name;
		this.color = color;
		if (books != null)
			this.books = Arrays.asList(books);
		this.minimumSelected = minimum;
	}

	/**
	 * Main Constructor.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param books the books
	 * @param color the color
	 * @param minimum the minimum
	 */
	public BookGroup(String id, String name, Book[] books, Color color, int minimum) {
		setID(id);
		this.name = name;
		this.color = color;
		if (books != null)
			this.books = Arrays.asList(books);
		this.minimumSelected = minimum;
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
		if (name != null && !name.isEmpty())
			return true;
		return false;
	}

	/**
	 * @return the books
	 */
	public Book[] getBooks() {
		return books.toArray(new Book[0]);
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	/**
	 * @param book the book to add
	 */
	public void addBook(Book book) {
		this.books.add(book);
	}

	/**
	 * Adds books to the current list.
	 * 
	 * @param books the books
	 */
	public void addBooks(Book[] books) {
		if (books != null) {
			List<Book> temp = Arrays.asList(books);
			this.books.addAll(temp);
		}
	}

	/**
	 * @param ID the book ID of the book to remove
	 */
	public void removeBook(String ID) {
		for (Book book : books) {
			if (book.getID() == ID)
				books.remove(book);
		}
	}

	/**
	 * Validates if a book of contained in this book group.
	 * 
	 * @param book the book
	 * @return true, if the book is contained in the book group
	 */
	public boolean containsBook(String id) {
		for (Book book : books) {
			if (book.getID() == id)
				return true;
		}
		return false;
	}

	/**
	 * @return true if the list contains at least one item
	 */
	public boolean hasBooks() {
		return books != null && books.size() > 0;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return true, if a color has been set
	 */
	public boolean hasColor() {
		return getColor() != null;
	}

	/**
	 * @return the minimumSelected
	 */
	public int getMinimumSelected() {
		return minimumSelected;
	}

	/**
	 * @param minimumSelected the minimumSelected to set
	 */
	public void setMinimumSelected(int minimumSelected) {
		this.minimumSelected = minimumSelected;
	}

	/**
	 * @return true, if a minimum has been set
	 */
	public boolean hasMinimumSelected() {
		return getMinimumSelected() != -1;
	}

	/**
	 * @return true, if the book group is valid
	 */
	public boolean isValid() {
		if (hasID() && hasName())
			return true;
		log.log(Level.DEBUG, "Invalid book group detected: " + this.toString());
		return false;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		BookGroup bookGroup = (BookGroup) object;
		if (getID().equals(bookGroup.getID()))
			return true;
		return false;
	}
	
	/**
	 * @param objects the objects
	 * @return true, if the book group equals at least one other book group in the list
	 */
	public boolean equalsAny(Object[] objects) {
		for (Object object : objects) {
			if (this.equals(object))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns all accessible properties of this book group in a HashMap.
	 * 
	 * @return the HashMap containing this book groups properties, but never null
	 */
	public HashMap<String, Object> getAsWebElement() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", getName());
		map.put("books", getBooks());
		map.put("color", getColor());
		map.put("minimum", getMinimumSelected());
		map.put("valid", isValid());
		return map;
	}

	@Override
	public String toString() {
		String color = null;
		int booksSize = 0;
		if (this.color != null)
			color = this.color.toString();
		if (this.books != null)
			booksSize = this.books.size();
		return String.format("BookGroup[id=%s, name=%s, color=%s, books=%s, minimum=%s]", getID(), name, color,
				booksSize, minimumSelected);
	}

}
