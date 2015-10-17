package main.java.wonderland.webServer.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.wonderland.general.core.Book;
import main.java.wonderland.search.BookSearch;
import main.java.wonderland.search.criteria.BookCriteria;
import main.java.wonderland.webServer.login.LoginLevel;
import spark.Response;

public class TestPage extends Page {

	private String fach;

	public TestPage() {
		super("/test", "test.ftl", LoginLevel.NotLoggedIn);
	}

	@Override
	protected void onPost(String key, String value) {
		if (key.equals("fach"))
			fach = value;
	}

	@Override
	protected void onPostEnd(Response response) {

	}

	@Override
	protected void setupPage(Map<String, Object> map) {

		map.put("user", "testUser1");
		map.put("title", "TestPage");

		HashMap<String, String> product = new HashMap<>();
		product.put("url", "TestURL");
		product.put("name", "TestName");
		map.put("latestProduct", product);

		List<Object> bookList = new ArrayList<>();

		if (fach != null) {
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
			fach = null;
		}

		map.put("books", bookList);

	}

}
