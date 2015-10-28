package main.java.wonderland.database.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.wonderland.database.DBConnetcor;
import main.java.wonderland.database.DBVariables;
import main.java.wonderland.database.ObjectConverter;
import main.java.wonderland.database.QueryFactory;
import main.java.wonderland.database.criteria.CategoryCriteria;
import main.java.wonderland.general.core.Category;

/**
 * Reads data from local disk.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 15:16
 *
 */
public class DBCategory {
	
	/**
	 * Accesses the database and returns all existing categories.
	 * 
	 * @return all existing categories
	 */
	public static Category[] getAll() {
		return getCategories("%", false, CategoryCriteria.NAME);
	}

	public static Category getCategoryByName(String name) {
		Category[] categories = getCategoriesSingleCriteria(name, true, CategoryCriteria.NAME);
		if (categories != null && categories.length > 0)
			return categories[0];
		return null;
	}

	public static void insertCategory(Category category) {
		DBConnetcor.executeUpdate(QueryFactory.getCategoryInsertQuery(category));
	}

	public static void removeCategoryByName(String name) {
		DBConnetcor.executeUpdate(QueryFactory.getCategoryDeleteQuery(name));
	}

	public static void updateCategory(Category category) {
		DBConnetcor.executeUpdate(QueryFactory.getCategoryUpdateQuery(category));
	}

	/**
	 * Fetches all subjects from the database that match the given criteria.
	 * 
	 * @param match the match criteria
	 * @return an array containing the results
	 */
	public static Category[] getCategories(String match, boolean exact, CategoryCriteria criteria) {
		if (criteria == CategoryCriteria.ALL) {
			return getCategoriesAllCriteria(match, exact);
		} else if (CategoryCriteria.convertToDatabase(criteria) != null) {
			return getCategoriesSingleCriteria(match, exact, criteria);
		}
		return null;
	}

	private static Category[] getCategoriesSingleCriteria(String match, boolean exact, CategoryCriteria criteria) {
		String converted = CategoryCriteria.convertToDatabase(criteria);
		String query = QueryFactory.getStandardSelectQuery(DBVariables.CATEGORIES_TABLE, match, exact, converted);
		ResultSet results = DBConnetcor.executeQuery(query);
		return ObjectConverter.convertToCategories(results);
	}

	private static Category[] getCategoriesAllCriteria(String match, boolean exact) {
		List<Category> categories = new ArrayList<>();
		for (CategoryCriteria crit : CategoryCriteria.values()) {
			String converted = CategoryCriteria.convertToDatabase(crit);
			if (converted != null) {
				String query = QueryFactory.getStandardSelectQuery(DBVariables.CATEGORIES_TABLE, match, exact,
						converted);
				ResultSet results = DBConnetcor.executeQuery(query);
				List<Category> search = Arrays.asList(ObjectConverter.convertToCategories(results));
				for (Category category : search) {
					if (!category.equalsAny(categories.toArray(new Category[0])))
						categories.add(category);
				}
			}
		}
		return categories.toArray(new Category[0]);
	}

}
