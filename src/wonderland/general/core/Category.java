package wonderland.general.core;

import wonderland.general.ConLibrary;
import wonderland.search.CategorySearch;

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
	 * Adds the book category to the list.
	 */
	public void addToList() {
		if (hasName()) {
			if (CategorySearch.containsName(name)) {
				System.err.println(String.format("Book Category cannot be added to the list: Duplicated Name (%s).", name));
				return;
			}
			if (CategorySearch.containsShortName(shortName)) {
				System.err.println(String.format("Book Category cannot be added to the list: Duplicated ShortName (%s).", shortName));
				return;
			}
			ConLibrary.getCategories().add(this);
		} else {
			System.err.println("Book Category cannot be added to the list: Invalid name.");
		}
	}

	/**
	 * Removes the book category from the list.
	 */
	public void removeFromList() {
		ConLibrary.getCategories().remove(this);
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
	
	@Override
	public String toString() {
		return String.format("Category[name=%s, shortName=%s]", name, shortName);
	}
}
