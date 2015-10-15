package main.java.wonderland.webServer;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	protected final String redirectAfterPost;
	private static LoginLevel loginLevel;
	private static List<String> watchElements = new ArrayList<>();

	protected Page(String subPath, String htmlFile, LoginLevel loginLevel) {
		this(subPath, htmlFile, loginLevel, null);
	}

	protected Page(String subPath, String htmlFile, LoginLevel loginLevel, String redirectAfterPost) {
		this.subPath = "/" + subPath;
		Page.loginLevel = loginLevel;
		this.htmlPath = htmlFile;
		this.redirectAfterPost = redirectAfterPost;

		
		this.addBeforeFilter(new privateLoginChecker());
		this.addGETPOSTRoute(new privateListenerRoute());
	}

	protected abstract void onPost(Map<String, List<String>> watchElements);

	protected void addWatchElement(String name) {
		watchElements.add(name);
	}
	
	protected void addBeforeFilter(Filter filter){
		before(subPath, filter);
	}

	protected void addGETPOSTRoute(Route route) {
		get(subPath, route);
		post(subPath, route);
	}

	class privateLoginChecker implements Filter {
		@Override
		public void handle(Request request, Response response) throws Exception {
			Map<String, String> currentUser = request.cookies();
			String UIDinCookies = currentUser.get("OBOOKUID");
			
			if(Page.loginLevel.getLevel() > LoginLevel.All.getLevel()){
				if (UIDinCookies == null)
					response.redirect("/login");
				if (WebServer.getUser(UIDinCookies) == null
						|| WebServer.getUser(UIDinCookies).getLoginLevel().getLevel() >= Page.loginLevel.getLevel()) {
					response.redirect("/", 403);
				}
			}

			
		}
	}

	class privateListenerRoute implements Route {
		@Override
		public Object handle(Request request, Response response) throws Exception {
			if (request.requestMethod() == "GET") {
				return HTMLUtils.readHTMLFile(htmlPath);
			} else {
				Map<String, List<String>> watchElements = new HashMap<>();

				QueryParamsMap map = request.queryMap();
				for (String element : map.toMap().keySet()) {
					if (map.get(element).values().length > 1) {
						for (String str : map.get(element).values()) {
							updateIndex(element, str, watchElements);
						}
					} else {
						updateIndex(element, map.get(element).value(), watchElements);
					}
				}
				
				if(redirectAfterPost != null){
					response.redirect(redirectAfterPost);
					return null;
				}
			}
			//TODO change the values after the plot was send
			return HTMLUtils.readHTMLFile(htmlPath);
		}
	}

	private static void updateIndex(String key, String value, Map<String, List<String>> list) {
		if (watchElements.contains(key)) {
			if (!list.containsKey(key))
				list.put(key, new ArrayList<String>());
			list.get(key).add(value);
		}
	}
}
