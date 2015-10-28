package main.java.wonderland.general.core;

import java.awt.Color;
import java.util.HashMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.wonderland.database.action.DBCategory;
import main.java.wonderland.general.UIDGenerator;

/**
 * Represents the category of a book.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 16:54
 *
 */
public class Category extends Serial {

	// Logger
	private static final Logger log = LogManager.getLogger(Category.class.getName());

	private String name;
	private String shortName;
	private Color color;

	/**
	 * Creates a new category containing a name, a short name and a color.
	 * 
	 * @param name the name
	 * @param shortName the short name
	 * @param color the color
	 */
	public Category(String name, String shortName, Color color) {
		setID(UIDGenerator.genNextID());
		this.name = name;
		this.shortName = shortName;
		this.color = color;
	}

	/**
	 * Creates a new category containing an id, a name, a short name and a
	 * color.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param shortName the short name
	 * @param color the color
	 */
	public Category(String id, String name, String shortName, Color color) {
		setID(id);
		this.name = name;
		this.shortName = shortName;
		this.color = color;
	}

	/**
	 * Creates this category in the database.
	 */
	public void createInDatabase() {
		DBCategory.insertCategory(this);
	}

	/**
	 * Updates the database entry for this category.
	 */
	public void syncWithDatabase() {
		DBCategory.updateCategory(this);
	}

	/**
	 * Removes this category from the database.
	 */
	public void removeFromDatabase() {
		DBCategory.removeCategoryByName(getID());
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
	 * @return true, if the category is valid
	 */
	public boolean isValid() {
		if (hasName() && hasName())
			return true;
		log.log(Level.DEBUG, "Invalid category detected: " + this.toString());
		return false;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Category category = (Category) object;
		if (getID().equals(category.getID()))
			return true;
		return false;
	}
	
	/**
	 * @param objects the objects
	 * @return true, if the category equals at least one other category in the list
	 */
	public boolean equalsAny(Object[] objects) {
		for (Object object : objects) {
			if (this.equals(object))
				return true;
		}
		return false;
	}

	/**
	 * Returns all accessible properties of this category in a HashMap.
	 * 
	 * @return the HashMap containing this categories properties, but never null
	 */
	public HashMap<String, Object> getAsWebElement() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", getName());
		map.put("shortName", getShortName());
		map.put("color", getColor());
		map.put("valid", isValid());
		return map;
	}

	@Override
	public String toString() {
		String color = null;
		if (getColor() != null)
			color = getColor().toString();
		return String.format("Category[id=%s, name=%s, shortName=%s, color=%s]", getID(), name, shortName, color);
	}
}
