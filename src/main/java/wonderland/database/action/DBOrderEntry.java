package main.java.wonderland.database.action;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.wonderland.database.DBConnetcor;
import main.java.wonderland.database.DBVariables;
import main.java.wonderland.database.ObjectConverter;
import main.java.wonderland.database.QueryFactory;
import main.java.wonderland.database.criteria.OrderEntryCriteria;
import main.java.wonderland.general.core.OrderEntry;
import main.java.wonderland.general.core.OrderStatus;

public class DBOrderEntry {

	/**
	 * Accesses the database and returns all existing order entries.
	 * 
	 * @return all existing order entries
	 */
	public static OrderEntry[] getAll() {
		return getOrderEntries("%", false, OrderEntryCriteria.ID);
	}
	
	public static OrderEntry getOrderEntryByID(String ID) {
		OrderEntry[] orders = getOrderEntries(ID, true, OrderEntryCriteria.ID);
		if(orders != null && orders.length > 0) return orders[0];
		return null;
	}

	public static void insertOrderEntry(OrderEntry order) {
		DBConnetcor.executeUpdate(QueryFactory.getOrderEntryInsertQuery(order));
	}

	public static void removeOrderEntryByID(String ID) {
		DBConnetcor.executeUpdate(QueryFactory.getOrderEntryDeleteQuery(ID));
	}

	public static void updateOrder(OrderEntry order) {
		DBConnetcor.executeUpdate(QueryFactory.getOrderEntryUpdateQuery(order));
	}

	public static OrderStatus getOrderStatusByID(String ID) {
		OrderEntry order = getOrderEntryByID(ID);
		if(order != null) return order.getStatus();
		return null;
	}

	public static void setOrderStatusByID(String ID, OrderStatus status) {
		OrderEntry order = getOrderEntryByID(ID);
		if(order != null) {
			order.setStatus(status);
			updateOrder(order);
		}
	}

	public static Timestamp getTimestampByID(String ID, OrderStatus status) {
		OrderEntry order = getOrderEntryByID(ID);
		if(order != null) return order.getTimestamp(status);
		return null;
	}

	public static void setTimestampByID(String ID, OrderStatus status, Timestamp timestamp) {
		OrderEntry order = getOrderEntryByID(ID);
		if(order != null) {
			order.setTimestamp(status, timestamp);
			updateOrder(order);
		}
		
	}

	public static OrderEntry[] getOrderEntries(String match, boolean exact, OrderEntryCriteria criteria) {
		if (criteria == OrderEntryCriteria.ALL) {
			return getOrderEntriesAllCriteria(match, exact, criteria);
		} else if (OrderEntryCriteria.convertToDatabase(criteria) != null) {
			return getOrderEntriesSingleCriteria(match, exact, criteria);
		}
		return null;
	}

	private static OrderEntry[] getOrderEntriesSingleCriteria(String match, boolean exact, OrderEntryCriteria criteria) {
		String converted = OrderEntryCriteria.convertToDatabase(criteria);
		String query = QueryFactory.getStandardSelectQuery(DBVariables.ORDERS_TABLE, match, exact, converted);
		ResultSet results = DBConnetcor.executeQuery(query);
		return ObjectConverter.convertToOrderEntry(results);
	}

	private static OrderEntry[] getOrderEntriesAllCriteria(String match, boolean exact, OrderEntryCriteria criteria) {
		List<OrderEntry> orders = new ArrayList<>();
		for (OrderEntryCriteria crit : OrderEntryCriteria.values()) {
			String converted = OrderEntryCriteria.convertToDatabase(crit);
			if (converted != null) {
				String query = QueryFactory.getStandardSelectQuery(DBVariables.ORDERS_TABLE, match, exact, converted);
				ResultSet results = DBConnetcor.executeQuery(query);
				List<OrderEntry> search = Arrays.asList(ObjectConverter.convertToOrderEntry(results));
				for (OrderEntry order : search) {
					if (!order.matchesAny(orders.toArray(new OrderEntry[0])))
						orders.add(order);
				}
			}
		}
		return orders.toArray(new OrderEntry[0]);
	}

}
