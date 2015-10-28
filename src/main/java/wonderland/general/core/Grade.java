package main.java.wonderland.general.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.wonderland.database.action.DBGrade;
import main.java.wonderland.general.UIDGenerator;

/**
 * Represents a normal grade.
 * 
 * @author Lukas Kannenberg
 * @since 15-10-2015
 * @version 15-10-2015 01:23
 *
 */
public class Grade extends Serial {

	// Logger
	private static final Logger log = LogManager.getLogger(Grade.class.getName());

	private String name;
	private String shortName;
	private BookGroup books;
	private List<BookGroup> bookGroups = new ArrayList<>();

	/**
	 * Creates a new grade containing a name, a short name, books and book
	 * groups.
	 * 
	 * @param name the name
	 * @param shortName the short name
	 * @param books the books to be added
	 * @param boookGroup the bookGroup to be added
	 */
	public Grade(String name, String shortName, BookGroup books, BookGroup[] bookGroups) {
		setID(UIDGenerator.genNextID());
		this.name = name;
		this.shortName = shortName;
		this.books = books;
		if (bookGroups != null)
			this.bookGroups = Arrays.asList(bookGroups);
	}

	/**
	 * Creates a new grade containing an id, a name, a short name, books and
	 * book groups.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param shortName the short name
	 * @param books the books to be added
	 * @param boookGroup the bookGroup to be added
	 */
	public Grade(String id, String name, String shortName, BookGroup books, BookGroup[] bookGroups) {
		setID(id);
		this.name = name;
		this.shortName = shortName;
		this.books = books;
		if (bookGroups != null)
			this.bookGroups = Arrays.asList(bookGroups);
	}

	/**
	 * Creates this category in the database.
	 */
	public void createInDatabase() {
		if (isValid())
			DBGrade.insertGrade(this);
	}

	/**
	 * Updates the database entry for this category.
	 */
	public void syncWithDatabase() {
		if (isValid())
			DBGrade.updateGrade(this);
	}

	/**
	 * Removes this category from the database.
	 */
	public void removeFromDatabase() {
		if (isValid())
			DBGrade.removeGradeByName(getID());
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
		if (shortName != null && !shortName.isEmpty())
			return true;
		return false;
	}

	/**
	 * @return the the books
	 */
	public BookGroup getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(BookGroup books) {
		this.books = books;
	}

	/**
	 * @return true, if the list contains any books, otherwise false
	 */
	public boolean hasBooks() {
		if (books.getBooks().length > 0)
			return true;
		return false;
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
	 * @return the the books
	 */
	public BookGroup[] getBookGroups() {
		return bookGroups.toArray(new BookGroup[0]);
	}

	/**
	 * @param books the books to set
	 */
	public void setBookGroups(BookGroup[] bookGroup) {
		if (bookGroups != null)
			this.bookGroups = Arrays.asList(bookGroup);
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
		if (bookGroups != null) {
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
		bookGroups.remove(bookGroup);
	}

	/**
	 * @return true, if the list contains any books, otherwise false
	 */
	public boolean hasBookGroups() {
		if (!bookGroups.isEmpty())
			return true;
		return false;
	}

	/**
	 * @return the books as JSon
	 */
	public String getBookGroupsAsJSon() {
		// TODO
		return null;
	}

	/**
	 * Sets the books using a JSon string.
	 * 
	 * @param string the string
	 */
	public void setBookGroupssFromJSon(String string) {
		// TODO
	}

	/**
	 * @return true, if the grade is valid
	 */
	public boolean isValid() {
		if (hasID() && hasName())
			return true;
		log.log(Level.DEBUG, "Invalid grade detected: " + this.toString());
		return false;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Grade grade = (Grade) object;
		if (getID().equals(grade.getID()))
			return true;
		return false;
	}
	
	/**
	 * @param objects the objects
	 * @return true, if the grade equals at least one other grade in the list
	 */
	public boolean equalsAny(Object[] objects) {
		for (Object object : objects) {
			if (this.equals(object))
				return true;
		}
		return false;
	}

	/**
	 * Returns all accessible properties of this grade in a HashMap.
	 * 
	 * @return the HashMap containing this grades properties, but never null
	 */
	public HashMap<String, Object> getAsWebElement() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", getName());
		map.put("shortName", getShortName());
		map.put("books", getBooks());
		map.put("bookGroups", getBookGroups());
		map.put("valid", isValid());
		return map;
	}

	@Override
	public String toString() {
		String books = null;
		int bookGroupsSize = 0;
		if (this.books != null)
			books = this.books.getName();
		if (this.bookGroups != null)
			bookGroupsSize = this.bookGroups.size();
		return String.format("Grade[id=%s, name=%s, shortName=%s, books=%s, bookGroups=%s]", getID(), name, shortName,
				books, bookGroupsSize);
	}

}
