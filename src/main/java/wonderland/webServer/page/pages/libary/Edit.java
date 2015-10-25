package main.java.wonderland.webServer.page.pages.libary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.wonderland.general.ConLibrary;
import main.java.wonderland.general.core.Book;
import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;
import spark.Response;

public class Edit extends BasePage {

	public Edit() {
		super("/libary/edit", "/libary/edit.ftl", LoginLevel.Moderator, "Bearbeiten");
	}

	@Override
	protected void setupSpecialPage(User u) {
		Map<String, Object> map = u.getPageConfiguration();
		List<Object> bookList = new ArrayList<>();
		
		u.clearSubConfiguration("books");

		List<Book> books = ConLibrary.getBooks();
		for (Book book : books) {
			HashMap<String, Object> bookHash = new HashMap<>();
			bookHash.put("name", book.getName());
			bookHash.put("id", book.getID());
			bookHash.put("subject", book.getSubject().getName());
			bookHash.put("category", book.getSubject().getCategory().getName());
			bookList.add(bookHash);
		}
		map.put("books", bookList);
		u.setPageConfiguration(map);
	}

	@Override
	protected void onPost(String key, String value, User u) {

	}

	@Override
	protected void onPostEnd(Response response, User u) {

	}

}
