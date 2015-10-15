package wonderland.general.core;

import java.awt.Color;

/**
 * Represents a book item that is to be displayed on the graphical interface.
 * 
 * @author Lukas Kannenberg
 * @since 15-10-2015
 * @version 15-10-2015 19:07
 *
 */
public class BookItem {

	private Book book;
	private Grade grade;
	
	/**
	 * Constructs a new Book Item.
	 * 
	 * @param book the book
	 * @param preset if the book is preset
	 */
	public BookItem(Book book, Grade grade, boolean preset) {
		this.book = book;
		this.grade = grade;
	}
	
	/**
	 * @return the color for this item
	 */
	public Color getItemColor() {
		return ColorManager.getResultingColor(grade, this);
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}
	
	/**
	 * @return true, if the book has been set
	 */
	public boolean hasBook() {
		return getBook() != null;
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
	 * @return true, if the grade has been set
	 */
	public boolean hasGrade() {
		return getGrade() != null;
	}
	
	@Override
	public String toString() {
		return String.format("BookItem[book=%s, grade=%s]", book.toString(), grade.toString());
	}
}
