package wonderland.general.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a group of books for a grade.
 * 
 * @author Lukas Kannenberg
 * @since 15-10-2015
 * @version 15-10-2015 00:52
 *
 */
public class BookGroup {

	private String name;
	private Color color;
	private boolean active;
	private List<BookItem> books = new ArrayList<>();
	
	// Form Settings
	private int minimumSelected = -1;
	
	/**
	 * Main Constructor.
	 * 
	 * @param name the name
	 * @param books the books
	 */
	public BookGroup(String name, BookItem[] books) {
		this.name = name;
		this.color = new Color(255, 255, 255);
		if(books != null) this.books = Arrays.asList(books);
	}
	
	/**
	 * Extended Constructor.
	 * 
	 * @param name the name
	 * @param books the books
	 * @param minimum the minimum
	 * @param maximum the maximum
	 */
	public BookGroup(String name, BookItem[] books, Color color, int minimum) {
		this.name = name;
		this.color = color;
		if(books != null) this.books = Arrays.asList(books);
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
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	public void setBooks(List<BookItem> books) {
		this.books = books;
	}
	
	/**
	 * Validates if a book of contained in this book group.
	 * 
	 * @param book the book
	 * @return true, if the book is contained in the book group
	 */
	public boolean containsBook(Book book) {
		if(books.contains(book)) return true;
		return false;
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
	
	@Override
	public String toString() {
		return String.format("BookGroup[name=%s, color=%s, active=%s, books=%s, minimum=%s]", name, color.toString(), active, books.toString(), minimumSelected);
	}
}
