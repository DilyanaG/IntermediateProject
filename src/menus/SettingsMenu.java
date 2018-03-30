package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.VideoController;
import exceptions.IllegalInputException;

public class SettingsMenu extends Menu{
	private ChannelController channelController = ChannelController.getInstance();
	private VideoController videoController = VideoController.getInstance();

	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">ChangePassword -password=new_password_here\n");
		builder.append(">DeleteAccount -password=password_here\n");
		builder.append(">Homepage\n");
		return builder.toString();
	}

	@Override
	public Menu process(String input) throws IllegalInputException {
		final String command = input.split(" ")[0].toLowerCase();// read first
																	// word from
																	// input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args);

		switch (command) {
		case "search":
			// Menu searchMenu = VideoController.search(...);
			// return searchMenu;
			break;
		case "changepassword":
			// final String key = "password";
			// final String newPassword = genericParser.parseToString(argsMap, key);
			// Menu settingsMenu = UserController.changePassword(newPassword);
			// return settingsMenu;
			break;
		case "deleteaccount":
			// final String key = "password";
			// final String password = genericParser.parseToString(argsMap, key);
			// Menu defaultMenu = UserController.deleteAccount(password);
			// return defaultMenu;
			break;
		case "homepage":
			// Menu homeMenu = ChannelController.homepage();
			// return homeMenu;
			break;
		case "exit":
			// Menu exitMenu = null;
			// return exitMenu;
			break;

		default:
			// throw new IllegalInputException();
			break;
		}

		return null;
	}
}
