package main.java.wonderland.webServer.page;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.Map;

import main.java.wonderland.webServer.WebServer;
import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import spark.Filter;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerEngine;

public abstract class Page {

	private final String subPath;
	private final String ftlPath;
	private LoginLevel loginLevel;

	protected Page(String subPath, String ftlFile, LoginLevel loginLevel) {
		this.subPath = subPath;
		this.loginLevel = loginLevel;
		this.ftlPath = ftlFile;
		this.addBeforeFilter(new privateLoginChecker(this));
		this.addGETPOSTRoute(new privateListenerRoute(this));

		put(this.subPath, new privateListenerRoute(this));
	}

	protected abstract void setupPage(User u);

	protected abstract void onPost(String key, String value, User u);

	protected abstract void onPostEnd(Response response, User u);

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
					// TODO ACCESS DENIED Message
					response.redirect("/index");
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
			User u = WebServer.getUser(request.cookies().get("OBOOKUID"));
			if(u == null) u = new User();
			
			u.setCurrentPage(subPath);
			
			if (request.requestMethod() == "GET") {
				page.setupPage(u);
				return new FreeMarkerEngine().render(new ModelAndView(u.getPageConfiguration(), page.ftlPath));
			} else {
				QueryParamsMap map = request.queryMap();
				for (String element : map.toMap().keySet()) {
					if (map.get(element).values().length > 1) {
						for (String str : map.get(element).values()) {
							page.onPost(element, str, u);
						}
					} else {
						page.onPost(element, map.get(element).value(), u);
					}
				}
				page.onPostEnd(response, u);
				page.setupPage(u);
				return new FreeMarkerEngine().render(new ModelAndView(u.getPageConfiguration(), page.ftlPath));
			}
		}
	}
}
