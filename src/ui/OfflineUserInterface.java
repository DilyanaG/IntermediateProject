package ui;

public class OfflineUserInterface extends UserInterface {

	private static OfflineUserInterface offlineUserInterface;

	private OfflineUserInterface() {
	}

	public static OfflineUserInterface getInstance() {
		if (offlineUserInterface == null) {
             offlineUserInterface = new OfflineUserInterface();
		}
		return offlineUserInterface;
	}

}
