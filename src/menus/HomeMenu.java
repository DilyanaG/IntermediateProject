package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.UserController;
import controllers.VideoController;
import dataclasses.Video;
import enums.SortSearchBy;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;
import parsers.GenericParser;
import parsers.VideoParser;

public class HomeMenu extends Menu {

	private GenericParser genericParser = GenericParser.getInstance();
	private VideoParser videoParser = VideoParser.getInstance();
	
	private UserController userController = UserController.getInstance();
	private ChannelController channelController = ChannelController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	
	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">AddVideo -title=title_here -url=url_here -description=description_here\n");
		builder.append(">MyVideos \n");
		builder.append(">MyPlaylists \n");
		builder.append(">MyChannels \n");
		builder.append(">Settings \n");
		builder.append(">Logout \n");
	
		return builder.toString();
	}

	@Override
	public Menu process(String input) throws IllegalInputException, DataBaseException {
		final String command = input.split(" ")[0].toLowerCase();// read first word from input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args);
		
		final String username = null; //TODO where to get the current user`s username?
		
		switch (command) {
		case "search":
			final SortSearchBy sortBy = SortSearchBy.resolve(argsMap);
			final String key = "tags";
			final String tags = genericParser.parseToString(argsMap, key);
			Menu searchMenu = videoController.search(tags, sortBy); 
			return searchMenu;
		case "addvideo":
			final Video video = videoParser.parse(argsMap);
			Menu homeMenu = channelController.addVideo(video);
			return homeMenu;
		case "myvideos":
			Menu myVideosMenu = channelController.myVideos();
			return myVideosMenu;
		case "myplaylists":
			Menu myPlaylistsMenu = channelController.myPlaylists();
			return myPlaylistsMenu;
		case "mychannels":
			Menu myChannelsMenu = channelController.myChannels();
			return myChannelsMenu;
		case "settings":
			Menu settingsMenu = channelController.settings();
			return settingsMenu;
		case "logout":
			Menu defaultMenu = userController.logout(username);
			return defaultMenu;
		case "exit":
			Menu exitMenu = null;
			return exitMenu;
		
		default:
	      throw new IllegalInputException("INVALID INPUT !");
			
		}

	}

}