package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.VideoController;
import exceptions.IllegalInputException;
import parsers.VideoParser;

public class SearchMenu extends Menu{

	private VideoParser videoParser = VideoParser.getInstance();
	private VideoController videoController = VideoController.getInstance();
	private ChannelController channelController = ChannelController.getInstance();
	
	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">OpenVideo -title=title_here\n");
		builder.append(">Homepage\n");
		return builder.toString();
	}

	@Override
	public Menu process(String input) throws IllegalInputException {
		final String command = input.split(" ")[0].toLowerCase();// read first word from input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args);

		switch (command) {
		case "search":
			// Menu searchMenu = VideoController.search(argsMap);
			// return searchMenu;
			break;
		case "openvideo":
			// final Video video = VideoParser.parse(argsMap);
			// Menu videoMenu = VideoController.openVideo(video.getTitle());
			// return videoMenu;
			break;
		case "homepage":
			// Menu homeMenu = ChannelController.homeMenu();
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
