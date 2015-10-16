package main.java.wonderland.webServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class HTMLUtils {
	public static String readHTMLFile(String path) {
		String builder = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String readLine;

			while ((readLine = br.readLine()) != null) {
				builder += readLine;
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder;
	}
	
	public static String generateUserID(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
}
