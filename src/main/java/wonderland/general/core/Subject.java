package main.java.wonderland.general.core;

import java.awt.Color;

/**
 * Represents the subject of a book.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 16:54
 *
 */
public class Subject {
	
	private String name;
	private String shortName;
	private Category category;
	private Color color;
	
	/**
	 * Constructs a new BookCategory.
	 * 
	 * @param name the name
	 */
	public Subject(String name) {
		this.name = name;
	}
	
	/**
	 * Constructs a new BookCategory.
	 * 
	 * @param name the name
	 * @param shortName the short name
	 */
	public Subject(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}
	
	/**
	 * Constructs a new BookCategory.
	 * 
	 * @param name the name
	 * @param shortName the short name
	 * @param category the category
	 * @param color the color
	 */
	public Subject(String name, String shortName, Category category, Color color) {
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
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	
	/**
	 * @return true, if the category has been set, otherwise false
	 */
	public boolean hasCategory() {
		if(category != null) return true;
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
	
	/**
	 * @return true, if the subject is valid
	 */
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean matches(Subject subject) {
		if (name.equals(subject.getName())) return true;
		return false;
	}
	
	public boolean matchesAny(Subject[] subjects) {
		for (Subject subject : subjects) {
			if(this.matches(subject)) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String category = null;
		if(this.category != null) {
			category = this.category.toString();
		}
		return String.format("Subject[name=%s, shortName=%s, category=%s]", name, shortName, category);
	}

}
