package main.java.wonderland.webServer.page.pages.orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.wonderland.database.action.DBOrderEntry;
import main.java.wonderland.database.criteria.OrderEntryCriteria;
import main.java.wonderland.general.core.Book;
import main.java.wonderland.general.core.Order;
import main.java.wonderland.general.core.OrderStatus;
import main.java.wonderland.webServer.login.LoginLevel;
import main.java.wonderland.webServer.login.User;
import main.java.wonderland.webServer.page.BasePage;

public class Finish extends BasePage {

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

	@SuppressWarnings("unused")
	@Override
	protected void onPostComplete(User u, Map<String, Object> postObjects) {
		Map<String, Object> map = u.getPageConfiguration();
		

		if (postObjects.get("button_Abbrechen") != null) {
			//Aktuelle order auf null setzten
			map.put("orderID", -1);
		}
		
		if (postObjects.get("button_AuftragFertigstellen") != null) {
			map.put("order", null);
			//Order absenden
			map.put("orderID", -1);
		}

		if (postObjects.get("button_AuftragSuchen") != null) {
			//Auftrag ladaen
			int orderID;
			try {
				orderID = Integer.valueOf((String) postObjects.get("input_nummer"));
			} catch (Exception e) {
				orderID = -1;
				map.put("error", "Keine Zahl eingeben!");
			}
			map.put("orderID", orderID);
			
			Order[] ordersInCheckOut = DBOrderEntry.getOrderEntries(OrderStatus.CHECKOUT.toString(), true, OrderEntryCriteria.STATUS); // = Ordermanager.findLatestByID(orderID);
			Order o = null;
			for (Order order : ordersInCheckOut) {
				if(order.getNumber() == orderID){
					o = order;
					break;
				}
			}
			
			if(o != null){
				HashMap<String, Object> currentOrder = new HashMap<>();
				currentOrder.put("grade", o.getGrade().getName());
				
				List<Object> books = new ArrayList<>();
				for (Book book: o.getBooks()) {
					books.add(book.getAsWebElement());
				}
				HashMap<String, Object> demoBook = new HashMap<>();
				demoBook.put("amount", 10);
				demoBook.put("name", "Mathe 10");
				demoBook.put("subject", "Mathemathik");
				demoBook.put("category", "Naturwissenschaften");
				books.add(demoBook);
				
				currentOrder.put("books", books);
				
				map.put("order", currentOrder);
				map.put("error", null);
			}else{
				map.put("error", "Auftrag nicht gefunden!");
				map.put("orderID", -1);
			}
		}
		
		u.setPageConfiguration(map);
	}

}
