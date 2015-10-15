package main.java.wonderland.util;

import java.util.List;

/**
 * Offers list utility methods.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 20:10
 *
 */
public class ListUtility {

	/**
	 * Validates if a list contains at least one of the given objects.
	 * 
	 * @param list the list
	 * @param objects the objects
	 * @return true, if at least one object is contained in the list, otherwise false
	 */
	public static boolean listContainsOne(List<?> list, Object... objects) {
		for (Object object : objects) {
			if(list.contains(object)) {
				return true;
			}
		}
		return false;
	}
}
