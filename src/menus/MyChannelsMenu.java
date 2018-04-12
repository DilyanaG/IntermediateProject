package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.VideoController;
import enums.SortSearchBy;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;
import parsers.GenericParser;

public class MyChannelsMenu extends Menu {
	
	private GenericParser genericParser = GenericParser.getInstance();
	
	private ChannelController channelController = ChannelController.getInstance();
	private VideoController videoController = VideoController.getInstance();

	
	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">OpenChannel -channelname=channelname_here\n");
		builder.append(">UnfollowChannel -channelname=channelname_here\n");
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
		 
		final String key = "channelname";
		final String channelName = genericParser.parseToString(argsMap, key);

		switch (command) {
		case "search":
			final SortSearchBy sortBy = SortSearchBy.resolve(argsMap);
			final String keyTag = "tags";
			final String tags = genericParser.parseToString(argsMap, keyTag);
			Menu searchMenu = videoController.search(tags, sortBy); 
			return searchMenu;
		case "openchannel":
			Menu channelMenu = channelController.openChannel(channelName);
			return channelMenu;
		case "unfollowchannel":
			Menu myChannelsMenu = channelController.unfollowChannel(channelName);
			return myChannelsMenu;
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
