package wonderland.general.core;

import java.awt.Image;

/**
 * The BookBuilder builds books with specific properties.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 15:16
 *
 */
public class BookBuilder {

	private String ID;
	private String name;
	private Subject subject;
	private Image cover;

	/**
	 * Constructs a new BookBuilder instance.
	 * 
	 * @param ID
	 *            the ID of the book
	 */
	public BookBuilder(String ID) {
		this.ID = ID;
	}

	/**
	 * Adds a name to the current instance.
	 * 
	 * @param name
	 *            the name
	 * @return the current instance
	 */
	public BookBuilder withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Adds a subject to the current instance.
	 * 
	 * @param subject
	 *            the subject
	 * @return the current instance
	 */
	public BookBuilder withSubject(Subject subject) {
		this.subject = subject;
		return this;
	}

	/**
	 * Adds a cover to the current instance.
	 * 
	 * @param cover
	 *            the cover
	 * @return the current instance
	 */
	public BookBuilder withCover(Image cover) {
		this.cover = cover;
		return this;
	}

	/**
	 * Builds the current instance.
	 * 
	 * @return a book representing the instance
	 */
	public Book built() {
		return new Book(ID, name, subject, cover);
	}
}
