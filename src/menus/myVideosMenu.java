package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.VideoController;
import exceptions.IllegalInputException;
import parsers.GenericParser;

public class myVideosMenu extends Menu{
	private ChannelController channelController = ChannelController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	
	private GenericParser genericParser = GenericParser.getInstance();

	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">SortVideos -sortvideoby=NEWEST_OLDEST_or_LIKES\n");
		builder.append(">OpenVideo -title=title_here\n");
		builder.append(">RemoveVideo -title=title_here \n");
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
			// Menu searchMenu = VideoController.search(...);
			// return searchMenu;
			break;
		case "sortvideos":
			// final SortVideoBy sortBy = SortVideoBy.resolve(argsMap);
			// Menu myVideosMenu = VideoController.sortVideos(sortBy);
			// return myVideosMenu;
			break;
		case "openvideo":
			// final String key = "title";
			// final String title = genericParser.parseToString(argsMap, key);
			// Menu videoMenu = VideoController.openVideo(title);
			// return videoMenu;
			break;
		case "removevideo":
			// final String key = "title";
			// final String title = genericParser.parseToString(argsMap, key);
			// Menu myVideosMenu = VideoController.removeVideo(title);
			// return myVideosMenu;
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
