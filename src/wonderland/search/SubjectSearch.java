package wonderland.search;

import java.util.List;

import wonderland.general.ConLibrary;
import wonderland.general.core.Subject;

public class SubjectSearch {

	public static Subject getFromName(String name) {
		List<Subject> subjects = ConLibrary.getSubjects();
		for (Subject subject : subjects) {
			if (subject.getName() == name) {
				return subject;
			}
		}
		return null;
	}
	
	public static Subject getFromShortName(String shortName) {
		List<Subject> subjects = ConLibrary.getSubjects();
		for (Subject subject : subjects) {
			if (subject.getShortName() == shortName) {
				return subject;
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
