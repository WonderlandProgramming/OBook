package main.java.wonderland.webServer.login;

public enum LoginLevel {
	SuperAdministrator(10), Administrator(9), Moderator(8), User(5), Schreiben(2), All(1), NotLoggedIn(0);

	private int level;

	LoginLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

}
