package main.java.wonderland.main;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.wonderland.components.reader.ReaderController;
import main.java.wonderland.components.writer.WriterController;
import main.java.wonderland.database.DBConnetcor;
import main.java.wonderland.general.core.Book;
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

	private static final Logger log = LogManager.getLogger(Main.class.getName());
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		log.log(Level.INFO, "Starting OBook Services.");
		// Main
		log.log(Level.INFO, "Starting Connection to OBook Database.");
		DBConnetcor.defaultConnect();
		ReaderController rc = new ReaderController();
		WriterController wc = new WriterController();
		
		Book b = new Book("001", "", null, null);
		b.isValid();
		
		rc.addPanelGroup("001", 1);

		new WebServer();
	}

}
