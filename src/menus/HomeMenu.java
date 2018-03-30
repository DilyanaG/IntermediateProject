package menus;

import java.util.Map;

import controllers.ChannelController;
import exceptions.IllegalInputException;

public class HomeMenu extends Menu {

	private ChannelController channelController = ChannelController.getInstance();

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
	public Menu process(String input) throws IllegalInputException {
		final String command = input.split(" ")[0].toLowerCase();// read first word from input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args);

		switch (command) {
		case "search":
			// Menu searchMenu = VideoController.search(...);
			// return searchMenu;
			break;
		case "addvideo":
			// final Video video = VideoParser.parse(argsMap);
			// Menu homeMenu = ChannelController.addVideo(video.getTitle(), video.getURL(), video.getDescription());
			// return homeMenu;
			break;
		case "myvideos":
			// Menu myVideosMenu = ChannelController.myVideos();
			// return myVideosMenu;
			break;
		case "myplaylists":
			// Menu myPlaylistsMenu = ChannelController.myPlaylists();
			// return myPlaylistsMenu;
			break;
		case "mychannels":
			// Menu myChannelsMenu = ChannelController.myChannels();
			// return myChannelsMenu;
			break;
		case "settings":
			// Menu settingsMenu = ChannelController.settings();
			// return settingsMenu;
			break;
		case "logout":
			// Menu defaultMenu = ChannelController.logout();
			// return defaultMenu;
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