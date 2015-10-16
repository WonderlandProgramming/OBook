package main.java.wonderland.webServer;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Map;

import main.java.wonderland.webServer.login.LoginLevel;
import spark.Filter;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class Page {

	protected final String subPath;
	protected final String htmlPath;
	private LoginLevel loginLevel;

	protected Page(String subPath, String htmlFile, LoginLevel loginLevel) {
		this.subPath = "/" + subPath;
		this.loginLevel = loginLevel;
		this.htmlPath = htmlFile;
		this.addBeforeFilter(new privateLoginChecker(this));
		this.addGETPOSTRoute(new privateListenerRoute(this));
	}

	protected abstract void onPost(String key, String value);

	protected abstract void onPostEnd(Response response);

	protected void addBeforeFilter(Filter filter) {
		before(subPath, filter);
	}

	protected void addGETPOSTRoute(Route route) {
		get(subPath, route);
		post(subPath, route);
	}

	class privateLoginChecker implements Filter {
		private Page page;

		public privateLoginChecker(Page page) {
			this.page = page;
		}

		@Override
		public void handle(Request request, Response response) throws Exception {
			Map<String, String> currentUser = request.cookies();
			String UIDinCookies = currentUser.get("OBOOKUID");

			// if current page needs you to be logged in
			if (page.loginLevel.getLevel() > LoginLevel.NotLoggedIn.getLevel()) {
				// if not logged in
				if (UIDinCookies == null || UIDinCookies.isEmpty() || WebServer.getUser(UIDinCookies) == null) {
					response.redirect("/login");
					return;
				}
				// No rights to acces with the current userrights
				else if (WebServer.getUser(UIDinCookies).getLoginLevel().getLevel() < page.loginLevel.getLevel()) {
					//TODO ACCESS DENIED Message
					response.redirect("/");
					return;
				} else {
					return;
				}
			}
		}
	}

	class privateListenerRoute implements Route {
		private Page page;

		public privateListenerRoute(Page page) {
			this.page = page;
		}

		@Override
		public Object handle(Request request, Response response) throws Exception {
			if (request.requestMethod() == "GET") {
				return HTMLUtils.readHTMLFile(htmlPath);
			} else {

				QueryParamsMap map = request.queryMap();
				for (String element : map.toMap().keySet()) {
					if (map.get(element).values().length > 1) {
						for (String str : map.get(element).values()) {
							page.onPost(element, str);
						}
					} else {
						page.onPost(element, map.get(element).value());
					}
				}
				page.onPostEnd(response);
				return HTMLUtils.readHTMLFile(htmlPath);
			}
		}
	}
}
