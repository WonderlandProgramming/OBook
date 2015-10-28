package main.java.wonderland.main;

public class Settings {

	// Database
	public static String[] DATABASE_CONNECT = { "jdbc:mysql://localhost", "root", "" };
	
	// Reader Settings
	public static int QUEUE_UPDATE_TIME = 500;
	
	public static boolean DURATION_ENABLED = true;
	public static int NEEDED_PRIORITY_DURATION = 1000 * 60 * 3;
	public static int NEEDED_INACTIVE_DURATION = 1000 * 60 * 5;
	
	public static boolean COUNT_ENABLED = true;
	public static int NEEDED_COUNT = 6;
}
