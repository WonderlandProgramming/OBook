package main.java.wonderland.webServer.page.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.wonderland.general.core.Book;
import main.java.wonderland.search.BookSearch;
import main.java.wonderland.search.criteria.BookCriteria;
import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;
import spark.Response;

public class TestPage extends BasePage {

	private String fach;
	private boolean newSearch;
	
	public TestPage() {
		super("/test", "test.ftl", LoginLevel.User, "TestPage");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onPost(String key, String value, User u) {
		if(key.equals("search")){
			newSearch = true;
		}
			
		if (key.equals("fach")) {
			fach = value;
		}

		if (key.equals("removed") && !u.getUsername().equalsIgnoreCase("SHORTTERM USER")) {
			Map<String, Object> map = u.getPageConfiguration();
			List<Object> books = (List<Object>) map.get("books");

			for (Object object : books) {
				if (((HashMap<String, Object>) object).get("id").equals(value)) {
					books.remove(object);
					break;
				}
			}
			if (books.isEmpty())
				books = null;
			map.put("books", books);
			u.setPageConfiguration(map);
			
		}
	}

	@Override
	protected void setupSpecialPage(User u){
		Map<String, Object> map = u.getPageConfiguration();

		List<Object> bookList = new ArrayList<>();

		if (fach != null && newSearch) {
			u.clearSubConfiguration("books");

			BookCriteria[] crit = { BookCriteria.SUBJECT };

			Book[] books = BookSearch.getBooks(fach, crit);
			for (Book book : books) {
				HashMap<String, Object> bookHash = new HashMap<>();
				bookHash.put("name", book.getName());
				bookHash.put("id", book.getID());
				bookHash.put("subject", book.getSubject().getName());
				bookHash.put("category", book.getSubject().getCategory().getName());
				bookList.add(bookHash);
			}
			newSearch = false;
			map.put("books", bookList);

			fach = null;
		}
		u.setPageConfiguration(map);
	}

	@Override
	protected void onPostEnd(Response response, User u) {
		
	}
}
