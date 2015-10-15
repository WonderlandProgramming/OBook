package main.java.wonderland.general.core;

import java.awt.Image;

import main.java.wonderland.general.ConLibrary;

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
	private Image cover;

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
	public Book(String ID, String name, Subject subject, Image cover) {
		this.ID = ID;
		this.name = name;
		this.subject = subject;
		this.cover = cover;
	}

	/**
	 * Adds the Book to the list.
	 */
	public void addToList() {
		if (hasID()) {
			ConLibrary.getBooks().add(this);
		} else {
			System.err.println("Book cannot be added to the list: Invalid ID.");
		}
	}

	/**
	 * Removes the book from the list.
	 */
	public void removeFromList() {
		ConLibrary.getBooks().remove(this);
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
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
	public Image getCover() {
		return cover;
	}

	/**
	 * @param cover
	 *            the cover to set
	 */
	public void setCover(Image cover) {
		this.cover = cover;
	}

	/**
	 * @return true, if the cover has been set, otherwise false
	 */
	public boolean hasCover() {
		if (cover != null) return true;
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
