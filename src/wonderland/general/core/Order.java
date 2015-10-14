package wonderland.general.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {

	private String ID;
	private int number = -1;
	private Grade grade;
	private OrderStatus status;
	private List<Book> books = new ArrayList<>();
	
	/**
	 * Main Constructor.
	 * 
	 * @param ID the ID
	 * @param number the number
	 * @param grade the grade
	 * @param books the books
	 */
	public Order(String ID, int number, Grade grade, Book[] books) {
		this.ID = ID;
		this.number = number;
		this.grade = grade;
		if(books != null) this.books = Arrays.asList(books);
	}

	/**
	 * @return the ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param ID the ID to set
	 */
	public void setID(String ID) {
		this.ID = ID;
	}
	
	/**
	 * @return true, if the ID has been set, otherwise false
	 */
	public boolean hasID() {
		if (ID != null && !ID.isEmpty()) return true;
		return false;
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
		if (number != -1) return true;
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
		if (grade != null) return true;
		return false;
	}

	/**
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
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
		if(books != null) this.books = Arrays.asList(books);
	}
}
