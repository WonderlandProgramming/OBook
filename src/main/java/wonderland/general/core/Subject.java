package main.java.wonderland.general.core;

import java.awt.Color;
import java.util.HashMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.wonderland.general.UIDGenerator;

/**
 * Represents the subject of a book.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 16:54
 *
 */
public class Subject extends Serial {

	// Logger
	private static final Logger log = LogManager.getLogger(Subject.class.getName());

	private String name;
	private String shortName;
	private Category category;
	private Color color;

	/**
	 * Creates a new subject containing a name, a short name, a category and a
	 * color.
	 * 
	 * @param name the name
	 * @param shortName the short name
	 * @param category the category
	 * @param color the color
	 */
	public Subject(String name, String shortName, Category category, Color color) {
		setID(UIDGenerator.genNextID());
		this.name = name;
		this.shortName = shortName;
		this.category = category;
		this.color = color;
	}

	/**
	 * Creates a new subject containing an id, a name, a short name, a category
	 * and a color.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param shortName the short name
	 * @param category the category
	 * @param color the color
	 */
	public Subject(String id, String name, String shortName, Category category, Color color) {
		setID(id);
		this.name = name;
		this.shortName = shortName;
		this.category = category;
		this.color = color;
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
	 * @param shortName the short name to set
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
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return true, if the category has been set, otherwise false
	 */
	public boolean hasCategory() {
		if (category != null)
			return true;
		return false;
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
	 * @return true, if the color has been set
	 */
	public boolean hasColor() {
		return getColor() != null;
	}

	/**
	 * @return true, if the subject is valid
	 */
	public boolean isValid() {
		if (hasID() && hasName())
			return true;
		log.log(Level.DEBUG, "Invalid subject detected: " + this.toString());
		return false;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Subject subject = (Subject) object;
		if (getID().equals(subject.getID()))
			return true;
		return false;
	}
	
	/**
	 * @param objects the objects
	 * @return true, if the subject equals at least one other subject in the list
	 */
	public boolean equalsAny(Object[] objects) {
		for (Object object : objects) {
			if (this.equals(object))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns all accessible properties of this subject in a HashMap.
	 * 
	 * @return the HashMap containing this subjects properties, but never null
	 */
	public HashMap<String, Object> getAsWebElement() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", getName());
		map.put("shortName", getShortName());
		map.put("category", getCategory());
		map.put("color", getColor());
		map.put("valid", isValid());
		return map;
	}

	@Override
	public String toString() {
		String category = null;
		if (this.category != null)
			category = this.category.getName();
		return String.format("Subject[name=%s, shortName=%s, category=%s]", name, shortName, category);
	}

}
