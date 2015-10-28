package main.java.wonderland.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.wonderland.main.Settings;

public class DBConnetcor {
	
	private static Logger log = LogManager.getLogger(DBConnetcor.class.getName());
	
	private static Connection connection;

	public static void connect(String url, String user, String password) {
		try {
			connection = DriverManager.getConnection(url, user , password);
			System.out.println("Connected to Database.");
		} catch (Exception e) {
			log.log(Level.ERROR, "Connection failed: " + e.getMessage());
		}
	}
	
	public static void defaultConnect() {
		connect(Settings.DATABASE_CONNECT[0], Settings.DATABASE_CONNECT[1], Settings.DATABASE_CONNECT[2]);
	}
	
	public static void executeUpdate(String query) {
		if(connection == null) return;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			log.log(Level.ERROR, " Database Update failed: " + e.getMessage());
		}
	}
	
	public static ResultSet executeQuery(String query) {
		if(connection == null) return null;
		Statement statement = null;
		ResultSet results = null;
		try {
			statement = connection.createStatement();
			String queryString = query;
			results = statement.executeQuery(queryString);
		} catch (SQLException e) {
			log.log(Level.ERROR, "Database Query failed: " + e.getMessage());
		}
		return results;
	}
	
}
