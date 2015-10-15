package main.java.wonderland.search;

import java.util.List;

import main.java.wonderland.general.ConLibrary;
import main.java.wonderland.general.core.Category;

public class CategorySearch {

	public static Category getFromName(String name) {
		List<Category> categories = ConLibrary.getCategories();
		for (Category category : categories) {
			if (category.getName() == name) {
				return category;
			}
		}
		return null;
	}
	
	public static Category getFromShortName(String shortName) {
		List<Category> categories = ConLibrary.getCategories();
		for (Category category : categories) {
			if (category.getShortName() == shortName) {
				return category;
			}
		}
		return null;
	}
	
	public static boolean containsName(String name) {
		if (getFromName(name) != null) {
			return true;
		}
		return false;
	}
	
	public static boolean containsShortName(String shortName) {
		if (getFromShortName(shortName) != null) {
			return true;
		}
		return false;
	}
	
}
