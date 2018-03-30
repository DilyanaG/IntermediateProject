package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.VideoController;
import exceptions.IllegalInputException;
import parsers.ChannelParser;

public class myChannelsMenu extends Menu {
	private ChannelController channelController = ChannelController.getInstance();
	private VideoController videoController = VideoController.getInstance();

	private ChannelParser channelParser = ChannelParser.getInstance();

	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">OpenChannel -channelname=channelname_here\n");
		builder.append(">UnfollowChannel -channelname=channelname_here\n");
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
		case "openchannel":
			// final String key = "channelname";
			// final String channelName = genericParser.parseToString(argsMap, key);
			// Menu channelMenu = PlaylistController.sortPlaylists(channelName);
			// return channelMenu;
			break;
		case "unfollowchannel":
			// final String key = "channelname";
			// final String channelName = genericParser.parseToString(argsMap, key);
			// Menu myChannelsMenu = PlaylistController.openPlaylist(channelName);
			// return myChannelsMenu;
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
