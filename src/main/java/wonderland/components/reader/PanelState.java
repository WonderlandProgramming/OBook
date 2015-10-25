package main.java.wonderland.components.reader;

public enum PanelState {
	OPEN, ASSIGNED, IN_PROGESS, PRIORITY, ERROR;

	public static boolean hasOrder(PanelState status) {
		if (status == PanelState.ASSIGNED || status == PanelState.IN_PROGESS || status == PRIORITY)
			return true;
		return false;
	}
}