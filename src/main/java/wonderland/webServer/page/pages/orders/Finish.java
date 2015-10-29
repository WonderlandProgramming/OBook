package main.java.wonderland.webServer.page.pages.orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.wonderland.database.action.DBOrderEntry;
import main.java.wonderland.database.criteria.OrderEntryCriteria;
import main.java.wonderland.general.core.Book;
import main.java.wonderland.general.core.Order;
import main.java.wonderland.general.core.OrderEntry;
import main.java.wonderland.general.core.OrderStatus;
import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;

public class Finish extends BasePage {

	private static final Logger log = LogManager.getLogger(BasePage.class.getName());
	
	public Finish() {
		super("/orders/finish", "/orders/finish.ftl", LoginLevel.User, "Fertigstellen");
	}

	@Override
	protected void setupSpecialPage(User u) {
		Map<String, Object> map = u.getPageConfiguration();

		if (map.get("orderID") == null) {
			map.put("orderID", -1);
		}

		u.setPageConfiguration(map);
	}

	@Override
	protected void onPostComplete(User u, Map<String, Object> postObjects) {
		Map<String, Object> map = u.getPageConfiguration();

		if (postObjects.get("button_Abbrechen") != null) {
			// Aktuelle order auf null setzten
			map.put("orderUID", null);
			map.put("orderID", -1);
		}

		if (postObjects.get("button_AuftragFertigstellen") != null) {
			int orderUID;
			try {
				orderUID = Integer.parseInt((String) map.get("orderUID"));
			} catch (Exception e) {
				orderUID = -1;
			}

			if (orderUID > 0) {
				log.info(String.format("User %s tries to finish Order with ID %i ", u.getUsername(), orderUID));
				
				OrderEntry o = DBOrderEntry.getOrderEntryByID(String.valueOf(orderUID));
				o.setStatus(OrderStatus.FINISHED);
				DBOrderEntry.updateOrder(o);
				
				map.put("order", null);
				// Order absenden
				map.put("orderID", -1);

				map.put("orderUID", null);
			}
		}

		if (postObjects.get("button_AuftragSuchen") != null) {
			// Auftrag ladaen
			int orderID;
			try {
				orderID = Integer.valueOf((String) postObjects.get("input_nummer"));
			} catch (Exception e) {
				orderID = -1;
				map.put("error", "Keine Zahl eingeben!");
			}
			map.put("orderID", orderID);

			Order[] ordersInCheckOut = DBOrderEntry.getOrderEntries(OrderStatus.CHECKOUT.toString(), true,
					OrderEntryCriteria.STATUS); 
			Order o = null;
			for (Order order : ordersInCheckOut) {
				if (order.getNumber() == orderID) {
					o = order;
					break;
				}
			}

			if (o != null) {
				HashMap<String, Object> currentOrder = new HashMap<>();
				currentOrder.put("grade", o.getGrade().getName());

				List<Object> books = new ArrayList<>();
				for (Book book : o.getBooks()) {
					books.add(book.getAsWebElement());
				}

				currentOrder.put("books", books);

				map.put("orderUID", o.getID());
				map.put("order", currentOrder);
				map.put("error", null);
			} else {
				map.put("error", "Auftrag nicht gefunden!");
				map.put("orderID", -1);
			}
		}

		u.setPageConfiguration(map);
	}

}
