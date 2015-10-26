package main.java.wonderland.webServer.page.pages.orders;

import java.util.Map;

import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;

public class Create extends BasePage {

	public Create() {
		super("/orders/create", "/orders/create.ftl", LoginLevel.User, "Erstellen");
	}

	@Override
	protected void setupSpecialPage(User u) {
		Map<String, Object> map = u.getPageConfiguration();

		// setting the current page for the first init
		int currentID = map.get("currentID") == null ? 1 : (int) map.get("currentID");

		map.put("currentID", currentID);
		u.setPageConfiguration(map);
	}

	protected void onPostComplete(User u, Map<String, Object> postObjects) {
		Map<String, Object> map = u.getPageConfiguration();

		int currentID = map.get("currentID") == null ? 1 : (int) map.get("currentID");

		// check ob weiter oder zurück
		if (postObjects.get("weiter") != null) {
			if(currentID < 3){
				currentID++;
			}
		}
		if (postObjects.get("zurueck") != null) {
			if(currentID <= 3){
				currentID--;
				if(currentID == 1){
					postObjects.clear();
				}
			}
		}
		if (postObjects.get("fertigstellen") != null) {
			if(currentID == 3){
				currentID = 1;
			}
		}
		
		
		switch (currentID) {
		case 1:
			// Erstelllen aus webcode oder neu erstellen
			if (postObjects.get("button_neuErstellen") != null) {
				if (!((String) postObjects.get("input_nummer")).isEmpty()) {
					//Check if all values are correct
					boolean vorauswahl = postObjects.get("radio_vorauswahl").equals("mitVorauswahlt"); 
					String klasse = (String)postObjects.get("select_Class");
					String nummer = (String)postObjects.get("input_nummer");
					String zusatzinformationen = (String)postObjects.get("textarea_zusatz");
					
					System.out.println(vorauswahl + ":" + klasse + ":" + nummer + ":" + zusatzinformationen);
					
					currentID++;
				}

			} else if (postObjects.get("button_webCode") != null) {
				if (!((String) postObjects.get("input_webCode")).isEmpty()) {
					//Check if all values are correct
					String webCode = (String) postObjects.get("input_webCode");
					
					System.out.println(webCode);
					
					currentID++;
				}
			}

			break;
		case 2:
			// Return page 2

			break;
		case 3:
			// Return page 3

			break;
		}

		// Setting all values that are always there
		map.put("currentID", currentID);

		u.setPageConfiguration(map);
		System.out.println();
	}
}
