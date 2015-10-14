package wonderland.general.core;

import wonderland.general.ConLibrary;

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
	 */
	public Subject(String name, String shortName, Category category) {
		this.name = name;
		this.shortName = shortName;
		this.category = category;
	}
	
	/**
	 * Adds the book category to the list.
	 */
	public void addToList() {
		if (hasName()) {
			ConLibrary.getSubjects().add(this);
		} else {
			System.err.println("Subject cannot be added to the list: Invalid name.");
		}
	}

	/**
	 * Removes the book category from the list.
	 */
	public void removeFromList() {
		ConLibrary.getSubjects().remove(this);
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
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/**
	 * @return true, if the category has been set, otherwise false
	 */
	public boolean hasCategory() {
		if(category != null) return true;
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
