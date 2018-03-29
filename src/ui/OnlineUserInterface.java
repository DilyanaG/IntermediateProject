package ui;

import dataclasses.Channel;

public class OnlineUserInterface extends UserInterface {

	public static OnlineUserInterface onlineUserInteface;

	private OnlineUserInterface() {

	}

	@Override
	protected void enterCommand(Channel channel) {
		while (true) {
			System.out.println("Enter command:");
			String command = getStringFromKeyboard();
			switch (command) {
			case "addvideo": {
				// this.addVideoMenu(channel);
				return;
			}
			case "settings": {

				return;
			}
			case "logout": {
				return;
			}
			default: {
				if (super.giveCommandsFromChannel(command,channel)) {
					 return;
				}
			}
			}
		}

	}

	@Override
	protected void channelPrintBar() {
		System.out.println("|>AddVideo");
		super.channelPrintBar();
		System.out.println("|>Settings");
		System.out.println("|>Logout");

	}

	@Override
	public void loginMenu() {
		// TODO Auto-generated method stub
		super.loginMenu();
	}

	public static OnlineUserInterface getInstance() {
		if (onlineUserInteface == null) {
			onlineUserInteface = new OnlineUserInterface();
		}
		return onlineUserInteface;
	}
}
