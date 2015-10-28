package main.java.wonderland.main;

import main.java.wonderland.webServer.WebServer;

/**
 * Application Main class.
 * 
 * @author Lukas Kannenberg
 * @since 12-10-2015
 * @version 12-10-2015 15:16
 *
 */
public class Main {

	/*
	 * Ideeen:
	 * Bei der ausgabe der nummer error wenn die nummer noch nicht fertiggestellt ist.
	 * 
	 * Mängel:
	 * Book muss IWebElement implementieren!
	 * 
	 */
	public static void main(String[] args) {
		new WebServer();
	}

}
