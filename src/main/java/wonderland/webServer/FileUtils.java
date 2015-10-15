package main.java.wonderland.webServer;

import java.io.BufferedReader;
import java.io.FileReader;

class FileUtils {
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
}
