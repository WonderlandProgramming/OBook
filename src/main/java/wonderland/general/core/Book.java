package main.java.wonderland.general.core;

import java.util.HashMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.wonderland.database.action.DBBook;
import main.java.wonderland.general.UIDGenerator;

/**
 * Represents a classic book, which has to be constructed with a name. For
 * additional properties, a book may also have a subject and a cover path
 * representing the location of the image used for this book.
 * 
 * @author Lukas Kannenberg, Lukas Peer
 * @since 12-10-2015
 * @version 27-10-2015 23:50
 * 
 * @see {@link UIDGenerator} {@link BookBuilder}
 *
 */
public class Book extends Serial{

	// Logger
	private static final Logger log = LogManager.getLogger(Book.class.getName());

	private String name;
	private Subject subject;
	private String coverPath;

	/**
	 * Creates a new book containing a name, a subject and a cover path.
	 * 
	 * @param name the name
	 * @param subject the subject
	 * @param coverPath the path of the cover
	 */
	public Book(String name, Subject subject, String coverPath) {
		setID(UIDGenerator.genNextID());
		this.name = name;
		this.subject = subject;
		this.coverPath = coverPath;
	}
	
	/**
	 * Creates a new book containing an id, a name, a subject and a cover path.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param subject the subject
	 * @param coverPath the path of the cover
	 */
	public Book(String id, String name, Subject subject, String coverPath) {
		setID(id);
		this.name = name;
		this.subject = subject;
		this.coverPath = coverPath;
	}

	/**
	 * Creates this book in the database.
	 */
	public void createInDatabase() {
		DBBook.insertBook(this);
	}

	/**
	 * Updates the database entry for this book.
	 */
	public void syncWithDatabase() {
		DBBook.updateBook(this);
	}

	/**
	 * Removes this book from the database.
	 */
	public void removeFromDatabase() {
		DBBook.removeBookByID(getID());
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
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * @return true, if the subject has been set, otherwise false
	 */
	public boolean hasSubject() {
		if (subject != null)
			return true;
		return false;
	}

	/**
	 * @return the cover
	 */
	public String getCoverPath() {
		return coverPath;
	}

	/**
	 * @param coverPath the coverPath to set
	 */
	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	/**
	 * @return true, if the cover has been set, otherwise false
	 */
	public boolean hasCoverPath() {
		if (coverPath != null)
			return true;
		return false;
	}

	/**
	 * @return true, if the book is valid
	 */
	public boolean isValid() {
		if (hasID() && hasName())
			return true;
		log.log(Level.DEBUG, "Invalid book detected: " + this.toString());
		return false;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Book book = (Book) object;
		if (getID().equals(book.getID()))
			return true;
		return false;
	}

	/**
	 * @param books the books
	 * @return true, if the book equals at least one other book in the list
	 */
	public boolean equalsAny(Object[] objects) {
		for (Object object : objects) {
			if (this.equals(object))
				return true;
		}
		return false;
	}

	/**
	 * Returns all accessible properties of this book in a HashMap.
	 * 
	 * @return the HashMap containing this books properties, but never null
	 */
	public HashMap<String, Object> getAsWebElement() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", getID());
		map.put("name", getName());
		map.put("subject", getSubject());
		map.put("coverPath", getCoverPath());
		map.put("valid", isValid());
		return map;
	}

	@Override
	public String toString() {
		String subject = null;
		if (this.subject != null)
			subject = this.subject.getName();
		return String.format("Book[ID=%s, name=%s, subject=%s coverPath=%s]", getID(), name, subject, hasCoverPath());
	}

}
