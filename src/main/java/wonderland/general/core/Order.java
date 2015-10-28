package main.java.wonderland.general.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.wonderland.general.UIDGenerator;

public class Order extends Serial {

	// Logger
	private static final Logger log = LogManager.getLogger(Order.class.getName());

	private int number = -1;
	private Grade grade;
	private String description;
	private List<Book> books = new ArrayList<>();

	/**
	 * Creates a new order containing a number, a grade, a description and
	 * books.
	 * 
	 * @param number the number
	 * @param grade the grade
	 * @param description the description
	 * @param books the books
	 */
	public Order(int number, Grade grade, String description, Book[] books) {
		setID(UIDGenerator.genNextID());
		this.number = number;
		this.grade = grade;
		this.description = description;
		if (books != null)
			this.books = Arrays.asList(books);
	}

	/**
	 * Creates a new order containing an id, a number, a grade, a description
	 * and books.
	 * 
	 * @param id the id
	 * @param number the number
	 * @param grade the grade
	 * @param description the description
	 * @param books the books
	 */
	public Order(String id, int number, Grade grade, String description, Book[] books) {
		setID(id);
		this.number = number;
		this.grade = grade;
		this.description = description;
		if (books != null)
			this.books = Arrays.asList(books);
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return true, if the number has been set, otherwise false
	 */
	public boolean hasNumber() {
		if (number != -1)
			return true;
		return false;
	}

	/**
	 * @return the grade
	 */
	public Grade getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	/**
	 * @return true, if the grade has been set, otherwise false
	 */
	public boolean hasGrade() {
		if (grade != null)
			return true;
		return false;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return true, if the order contains at least one book
	 */
	public boolean hasDescription() {
		if (description != null && !description.isEmpty())
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
	public void setBooks(Book[] books) {
		if (books != null)
			this.books = Arrays.asList(books);
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
	 * @param books the books
	 */
	public void addBooks(Book[] books) {
		if (books != null) {
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
		books.remove(book);
	}

	/**
	 * @return true, if the order contains at least one book
	 */
	public boolean hasBooks() {
		return getBooks().length != 0;
	}

	/**
	 * @return the books as JSon
	 */
	public String getBooksAsJSon() {
		// TODO
		return null;
	}

	/**
	 * Sets the books using a JSon string.
	 * 
	 * @param string the string
	 */
	public void setBooksFromJSon(String string) {
		// TODO
	}

	/**
	 * @return true, if the order is valid
	 */
	public boolean isValid() {
		if (hasID() && hasNumber() && hasBooks())
			return true;
		log.log(Level.DEBUG, "Invalid order detected: " + this.toString());
		return false;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Order order = (Order) object;
		if (getID().equals(order.getID()))
			return true;
		return false;
	}
	
	/**
	 * @param objects the objects
	 * @return true, if the order equals at least one other order in the list
	 */
	public boolean equalsAny(Object[] objects) {
		for (Object object : objects) {
			if (this.equals(object))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String grade = null;
		int booksSize = 0;
		if(this.grade != null) grade = this.grade.getName();
		if(this.books != null) booksSize = this.books.size();
		return String.format("Order[id=%s, number=%s, grade=%s, description=%s, books=%s]", getID(), number,
				grade, description, booksSize);
	}

}
