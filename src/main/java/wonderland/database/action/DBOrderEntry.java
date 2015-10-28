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
		if (orders != null && orders.length > 0)
			return orders[0];
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
		if (order != null)
			return order.getStatus();
		return null;
	}

	public static void setOrderStatusByID(String ID, OrderStatus status) {
		OrderEntry order = getOrderEntryByID(ID);
		if (order != null) {
			order.setStatus(status);
			updateOrder(order);
		}
	}

	public static Timestamp getTimestampByID(String ID, OrderStatus status) {
		OrderEntry order = getOrderEntryByID(ID);
		if (order != null)
			return order.getTimestamp(status);
		return null;
	}

	public static void setTimestampByID(String ID, OrderStatus status, Timestamp timestamp) {
		OrderEntry order = getOrderEntryByID(ID);
		if (order != null) {
			order.setTimestamp(status, timestamp);
			updateOrder(order);
		}

	}

	/**
	 * 
	 * Accesses the database and returns an array of books that matches the
	 * given search criteria.
	 * 
	 * @param match the string to search for
	 * @param exact true if the match has to be exact (search string has to
	 *        match to the compared string completely) or false if the match has
	 *        to be like the compared string
	 * @param criteria the criteria to search for
	 * @return an array containing the search results (an empty array if no
	 *         results found) or null if the search criteria is invalid
	 */
	public static OrderEntry[] getOrderEntries(String match, boolean exact, OrderEntryCriteria... criteria) {
		boolean containsAll = false;
		for (OrderEntryCriteria orderEntryCriteria : criteria) {
			if (orderEntryCriteria == OrderEntryCriteria.ALL) {
				containsAll = true;
			}
		}
		if (containsAll) {
			return getOrderEntriesFromCriteria(match, exact, OrderEntryCriteria.values());
		} else {
			return getOrderEntriesFromCriteria(match, exact, criteria);
		}
	}

	@SuppressWarnings("unused")
	private static OrderEntry[] getOrderEntriesSingleCriteria(String match, boolean exact,
			OrderEntryCriteria criteria) {
		String converted = OrderEntryCriteria.convertToDatabase(criteria);
		String query = QueryFactory.getStandardSelectQuery(DBVariables.ORDERS_TABLE, match, exact, converted);
		ResultSet results = DBConnetcor.executeQuery(query);
		return ObjectConverter.convertToOrderEntry(results);
	}

	private static OrderEntry[] getOrderEntriesFromCriteria(String match, boolean exact,
			OrderEntryCriteria[] criteria) {
		List<OrderEntryCriteria> dealtWith = new ArrayList<>();
		List<OrderEntry> orders = new ArrayList<>();
		for (OrderEntryCriteria crit : criteria) {
			if (!dealtWith.contains(crit)) {
				String converted = OrderEntryCriteria.convertToDatabase(crit);
				if (converted != null) {
					String query = QueryFactory.getStandardSelectQuery(DBVariables.ORDERS_TABLE, match, exact,
							converted);
					ResultSet results = DBConnetcor.executeQuery(query);
					List<OrderEntry> search = Arrays.asList(ObjectConverter.convertToOrderEntry(results));
					for (OrderEntry order : search) {
						if (!order.equalsAny(orders.toArray(new OrderEntry[0])))
							orders.add(order);
					}
				}
				dealtWith.add(crit);
			}

		}
		return orders.toArray(new OrderEntry[0]);
	}

}
