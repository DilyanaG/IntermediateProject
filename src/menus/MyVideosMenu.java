package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.VideoController;
import enums.SortSearchBy;
import enums.SortVideoBy;
import exceptions.IllegalInputException;
import parsers.GenericParser;

public class MyVideosMenu extends Menu{
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
		
		final String keyTitle = "title";
		final String title = genericParser.parseToString(argsMap, keyTitle);
		Menu myVideosMenu = null;

		switch (command) {
		case "search":
			final SortSearchBy sortSearchBy = SortSearchBy.resolve(argsMap);
			final String keyTag = "tags";
			final String tags = genericParser.parseToString(argsMap, keyTag);
			Menu searchMenu = videoController.search(tags, sortSearchBy); 
			return searchMenu;
		case "sortvideos":
			final SortVideoBy sortVideoBy = SortVideoBy.resolve(argsMap);
			myVideosMenu = videoController.sortVideos(sortVideoBy);
			return myVideosMenu;
		case "openvideo":
			Menu videoMenu = videoController.openVideo(title);
			return videoMenu;
		case "removevideo":
			myVideosMenu = videoController.removeVideo(title);
			return myVideosMenu;
		case "homepage":
			Menu homeMenu = channelController.homepage();
			return homeMenu;
		case "exit":
			Menu exitMenu = null;
			return exitMenu;
		
		default:
			//TODO throw new IllegalInputException();
			break;
		}

		return null;
	}
}
