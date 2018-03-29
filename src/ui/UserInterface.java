package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import controllers.UserController;
import parsers.CommentParser;
import parsers.UserParser;
import parsers.VideoParser;

abstract class Menu {

	public String presnet() {
		final StringBuilder builder = new StringBuilder();
		builder.append("default");
		final String special = specialPresent();
		builder.append(special);
		builder.append("more defaults");
		return builder.toString();
	}

	protected abstract String specialPresent();

	public abstract Menu process(String input);

	protected Map<String, String> parseToMap(String input) { // Input without
																// command
		return AbstractParser.splitParameters(input);
	}

}

public class UserInterface {
	private Menu menu; // = new DefaultMenu();

	private final Scanner scanner = new Scanner(System.in);

	public void run() {
		try {
			runApplication();
			System.out.println("Good buy");
		} catch (Throwable t) {
			Logger.getGlobal().severe(t.getMessage() + " " + t.getStackTrace());
			System.out.println("Internal server error! Application will terminate. Sorry :(");
		} finally {
			scanner.close();
		}
	}

	public void runApplication() {
		while (menu != null) {
			final String presentation = menu.presnet();
			System.out.println(presentation);

			final String input = scanner.nextLine();
			menu = menu.process(input);
		}
	}
}

class UserMenu extends Menu {

	private UserParser parser = UserParser.getInstance();
	private UserController controller = UserController.getInstance();

	private VideoParser videoParser = VideoParser.getInstance();
	private CommentParser commentParser = CommentParser.getInstance();

	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append("very special user menu");
		return builder.toString();
	}

	@Override
	public Menu process(String input) {
		final String command = input.split(" ")[0];// read first word from
													// input;
		final Map<String, String> argsMap = parseToMap(input); // input minus
																// command
		switch (command) {
		case "method-1":
			// final Video video = videoParser.parse(argsMap);
			// final Comment comment = commnetParser.parse(argsMap);
			// Menu newMenu = controller.writeCommentForVideo(comment, video);
			// return newMenu;
			break;

		default:
			break;
		}

		return null;
	}

}

class AbstractParser {

	public static Map<String, String> splitParameters(final String input) {
		String[] args = input.split(" -");

		Map<String, String> argsMap = new HashMap<>();
		for (int i = 0; i < args.length; i++) {
			final String[] keyValuePair = args[i].split("=");

			if (keyValuePair.length != 2) {
				// explosions
			}

			final String key = keyValuePair[0];
			final String value = keyValuePair[1];

			argsMap.put(key, value);
		}

		return argsMap;
	}

}
