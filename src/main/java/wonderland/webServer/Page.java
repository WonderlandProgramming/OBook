package main.java.wonderland.webServer;


import static spark.Spark.post;

import spark.Request;
import spark.Response;
import spark.Route;

public class Page {
	public Page(String subPath){
		post("", new Route() {
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
