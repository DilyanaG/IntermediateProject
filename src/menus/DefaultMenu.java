package menus;

import java.util.Map;

import controllers.UserController;
import controllers.VideoController;
import dataclasses.User;
import enums.SortSearchBy;
import exceptions.IllegalInputException;
import parsers.GenericParser;
import parsers.UserParser;

public class DefaultMenu extends Menu {

	private UserParser userParser = UserParser.getInstance();
	private GenericParser genericParser = GenericParser.getInstance();
	
	private UserController userController = UserController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	
	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">Login -username=username_here -password=password_here\n");
		builder.append(">Register -username=username_here -password=password_here -email=email_here\n");
		return builder.toString();
	}

	@Override
	public Menu process(String input) throws IllegalInputException {
		final String command = input.split(" ")[0].toLowerCase(); // read first word from input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args); // make pairs of parameter and value
		
		final User user = userParser.parse(argsMap);
		Menu userMenu = null;

		switch (command) {
		case "search":
			final SortSearchBy sortBy = SortSearchBy.resolve(argsMap);
			final String key = "tags";
			final String tags = genericParser.parseToString(argsMap, key);
			Menu searchMenu = videoController.search(tags, sortBy); 
			return searchMenu;
		case "login":
			userMenu = userController.login(user.getUserName(), user.getPassword());
			return userMenu;
		case "register":
			userMenu = userController.register(user.getUserName(), user.getPassword(), user.getEmail());
			return userMenu;
		case "exit":
			Menu exitMenu = null;
		    return exitMenu;

		default:
			// this new IllegalInputException();
			break;
		}

		return null;
	}

}
