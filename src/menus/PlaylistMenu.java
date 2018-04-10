package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.PlaylistController;
import controllers.VideoController;
import enums.SortSearchBy;
import enums.SortVideoBy;
import exceptions.IllegalInputException;
import parsers.GenericParser;

public class PlaylistMenu extends Menu{
	private ChannelController channelController = ChannelController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	
	private PlaylistController playlistController = PlaylistController.getInstance();
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

		final String key = "title";
		final String title = genericParser.parseToString(argsMap, key);

		Menu playlistMenu = null;
		
		switch (command) {
		case "search":
			final SortSearchBy sortSearchBy = SortSearchBy.resolve(argsMap);
			final String keyTag = "tags";
			final String tags = genericParser.parseToString(argsMap, keyTag);
			Menu searchMenu = videoController.search(tags, sortSearchBy); 
			return searchMenu;
		case "sortvideos":
			final SortVideoBy sortVideoBy = SortVideoBy.resolve(argsMap);
			playlistMenu = playlistController.sortVideos(sortVideoBy);
			return playlistMenu;
		case "openvideo":
			Menu videoMenu = videoController.openVideo(title);
			return videoMenu;
		case "removevideo":
			playlistMenu = playlistController.removeVideoFromPlaylist(title);
			return playlistMenu;
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
