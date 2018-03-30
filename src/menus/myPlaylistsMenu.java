package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.PlaylistController;
import controllers.VideoController;
import exceptions.IllegalInputException;
import parsers.PlaylistParser;

public class myPlaylistsMenu extends Menu {
	private ChannelController channelController = ChannelController.getInstance();
	private VideoController videoController = VideoController.getInstance();

	private PlaylistController playlistController = PlaylistController.getInstance();
	private PlaylistParser playlistParser = PlaylistParser.getInstance();

	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">SortPlaylists -sortplaylistby=NEWEST_OLDEST_or_VIDEOS\n");
		builder.append(">OpenPlaylist -playlistname=playlistname_here\n");
		builder.append(">CreatePlaylist -playlistname=playlistname_here\n");
		builder.append(">RemovePlaylist -playlistname=playlistname_here\n");
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
		case "sortplaylists":
			// final SortPlaylistBy sortBy = SortPlaylistBy.resolve(argsMap);
			// Menu myPlaylistsMenu = PlaylistController.sortPlaylists(sortBy);
			// return myPlaylistsMenu;
			break;
		case "openplaylist":
			// final Playlist playlist = playlistParser.parse(argsMap);
			// Menu playlistMenu = PlaylistController.openPlaylist(playlist.getPlaylistName());
			// return playlistMenu;
			break;
		case "createplaylist":
			// final Playlist playlist = playlistParser.parse(argsMap);
			// Menu myPlaylistsMenu = PlaylistController.createPlaylist(playlist.getPlaylistName());
			// return myPlaylistsMenu;
			break;
		case "removeplaylist":
			// final Playlist playlist = playlistParser.parse(argsMap);
			// Menu myPlaylistsMenu = PlaylistController.removePlaylist(playlist.getPlaylistName());
			// return myPlaylistsMenu;
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
