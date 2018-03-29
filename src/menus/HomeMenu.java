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
		final String command = input.split(" ")[0];// read first word from input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args);

		switch (command) {
		case "AddVideo":
			// final Video video = VideoParser.parse(argsMap);
			// Menu homeMenu = ChannelController.addVideo(video.getTitle(), video.getURL(), video.getDescription());
			// return homeMenu;
			break;
		case "MyVideos":
			// Menu myVideos = ChannelController.myVideos();
			// return myVideos;
			break;
		case "MyPlaylists":
			// Menu myPlaylists = ChannelController.myPlaylists();
			// return myPlaylists;
			break;
		case "MyChannels":
			// Menu myChannels = ChannelController.myChannels();
			// return myChannels;
			break;
		case "Settings":
			// Menu settings = ChannelController.settings();
			// return settings;
			break;
		case "Logout":
			// Menu logout = ChannelController.logout();
			// return logout;
			break;
		
		default:
			// throw new IllegalInputException();
			break;
		}

		return null;
	}

}