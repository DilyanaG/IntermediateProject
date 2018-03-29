package menus;

import java.util.Map;

import controllers.UserController;
import controllers.VideoController;
import exceptions.IllegalInputException;
import parsers.UserParser;

public class DefaultMenu extends Menu {

	private UserParser parser = UserParser.getInstance();
	private UserController controller = UserController.getInstance();
	private VideoController videoParser = VideoController.getInstance();
	
	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">Login -username=username_here -password=password_here\n");
		builder.append(">Register -username=username_here -password=password_here -email=email_here\n");
		return builder.toString();
	}

	@Override
	public Menu process(String input) throws IllegalInputException {
		final String command = input.split(" ")[0];// read first word from input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args);

		switch (command) {
		case "Search":
			// Menu SearchMenu = VideoController.search(...);
			// return SearchMenu;
			break;
		case "Login":
			// final User user = UserParser.parse(argsMap);
			// Menu userMenu = UserController.login(user.getUsername(), user.getPassword());
			// return userMenu;
			break;
		case "Register":
			// final User user = UserParser.parse(argsMap);
			// Menu userMenu = UserController.register(user.getUsername(), user.getPassword(), user.getEmail());
			// return userMenu;
			break;
		case "Exit":
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
