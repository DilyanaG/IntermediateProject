package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.UserController;
import controllers.VideoController;
import enums.SortSearchBy;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;
import parsers.GenericParser;

public class SettingsMenu extends Menu{
	
	private GenericParser genericParser = GenericParser.getInstance();
	
	private UserController userController = UserController.getInstance();
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
	public Menu process(String input) throws IllegalInputException, DataBaseException {
		final String command = input.split(" ")[0].toLowerCase();// read first
																	// word from
																	// input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args);

		final String key = "password";
		final String password = genericParser.parseToString(argsMap, key);

		switch (command) {
		case "search":
			final SortSearchBy sortBy = SortSearchBy.resolve(argsMap);
			final String keyTag = "tags";
			final String tags = genericParser.parseToString(argsMap, keyTag);
			Menu searchMenu = videoController.search(tags, sortBy); 
			return searchMenu;
		case "changepassword":
			Menu settingsMenu = userController.changePassword(password);
			return settingsMenu;
		case "deleteaccount":
			Menu defaultMenu = userController.deleteAccount(password);
			return defaultMenu;
		case "homepage":
			Menu homeMenu = channelController.homepage();
			return homeMenu;
		case "exit":
			Menu exitMenu = null;
			return exitMenu;

		default:
	       throw new IllegalInputException("INVALID INPUT !");
			
		}

	}
}
