package wonderland.search;

import java.util.List;

import wonderland.general.ConLibrary;
import wonderland.general.core.Grade;

public class GradeSearch {

	public static Grade getFromName(String name) {
		List<Grade> grades = ConLibrary.getGrades();
		for (Grade grade : grades) {
			if (grade.getName() == name) {
				return grade;
			}
		}
		return null;
	}
	
	public static Grade getFromShortName(String shortName) {
		List<Grade> grades = ConLibrary.getGrades();
		for (Grade grade : grades) {
			if (grade.getShortName() == shortName) {
				return grade;
			}
		}
		return null;
	}
	
	public static boolean containsName(String name) {
		if(getFromName(name) != null) {
			return true;
		}
		return false;
	}
	
	public static boolean containsShortName(String shortName) {
		if(getFromShortName(shortName) != null) {
			return true;
		}
		return false;
	}
}
