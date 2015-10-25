package main.java.wonderland.general.core;

import java.awt.Color;

/**
 * Represents the category of a book.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 16:54
 *
 */
public class Category {
	
	private String name;
	private String shortName;
	private Color color;
	
	/**
	 * Constructs a new BookCategory.
	 * 
	 * @param name the name
	 */
	public Category(String name) {
		this.name = name;
	}
	
	/**
	 * Constructs a new BookCategory.
	 * 
	 * @param name the name
	 * @param shortName the short name
	 */
	public Category(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}
	
	/**
	 * Constructs a new BookCategory.
	 * 
	 * @param name the name
	 * @param shortName the short name
	 * @param color the color
	 */
	public Category(String name, String shortName, Color color) {
		this.name = name;
		this.shortName = shortName;
		this.color = color;
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
		if(name != null && !name.isEmpty()) return true;
		return false;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	
	/**
	 * @return true, if the short name has been set, otherwise false
	 */
	public boolean hasShortName() {
		if(shortName != null && !shortName.isEmpty()) return true;
		return false;
	}
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * @return true, if the color has been set
	 */
	public boolean hasColor() {
		return getColor() != null;
	}
	
	public boolean matches(Category category) {
		if (name.equals(category.getName())) return true;
		return false;
	}
	
	public boolean matchesAny(Category[] categories) {
		for (Category category : categories) {
			if(this.matches(category)) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Category[name=%s, shortName=%s]", name, shortName);
	}
}
