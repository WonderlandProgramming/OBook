package main.java.wonderland.general.core;

/**
 * Represents a classic book.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 15:16
 *
 */
public class Book {

	private String ID;
	private String name;
	private Subject subject;
	private String cover;

	/**
	 * Main Constructor.
	 * 
	 * @param ID
	 *            the ID
	 * @param name
	 *            the name
	 * @param subject
	 *            the subject
	 * @param cover
	 *            the cover image
	 * @param grades
	 *            the grades where the book is used
	 */
	public Book(String ID, String name, Subject subject, String cover) {
		this.ID = ID;
		this.name = name;
		this.subject = subject;
		this.cover = cover;
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @return true, if the ID has been set, otherwise false
	 */
	public boolean hasID() {
		if (ID != null && !ID.isEmpty()) return true;
		return false;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return true, if the name has been set, otherwise false
	 */
	public boolean hasName() {
		if (name != null && !name.isEmpty()) return true;
		return false;
	}

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @return true, if the subject has been set, otherwise false
	 */
	public boolean hasSubject() {
		if (subject != null) return true;
		return false;
	}

	/**
	 * @return the cover
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * @return true, if the cover has been set, otherwise false
	 */
	public boolean hasCover() {
		if (cover != null) return true;
		return false;
	}
	
	/**
	 * @return true, if the book is valid
	 */
	public boolean isValid() {
		if(hasID() && hasName()) return true;
		return false;
	}
	
	/**
	 * @param book the book
	 * @return true if the two books match
	 */
	public boolean matches(Book book) {
		if (ID.equals(book.getID())) return true;
		return false;
	}
	
	/**
	 * @param books the books
	 * @return true, if the book matches at least one other book in the list
	 */
	public boolean matchesAny(Book[] books) {
		for (Book book : books) {
			if(this.matches(book)) return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String subject = null;
		if(this.subject != null) {
			subject = this.subject.toString();
		}
		return String.format("Book[ID=%s, name=%s, subject=%s cover=%s]", ID, name, subject, hasCover());
	}

}
