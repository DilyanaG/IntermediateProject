package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.PlaylistController;
import controllers.VideoController;
import dataclasses.Playlist;
import enums.SortPlaylistBy;
import enums.SortSearchBy;
import exceptions.IllegalInputException;
import parsers.GenericParser;
import parsers.PlaylistParser;

public class MyPlaylistsMenu extends Menu {

	private GenericParser genericParser = GenericParser.getInstance();
	private PlaylistParser playlistParser = PlaylistParser.getInstance();

	private VideoController videoController = VideoController.getInstance();
	private PlaylistController playlistController = PlaylistController.getInstance();
	private ChannelController channelController = ChannelController.getInstance();

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
		final String command = input.split(" ")[0].toLowerCase();// read first word from input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args);
		
		final Playlist playlist = playlistParser.parse(argsMap);
		
		Menu myPlaylistsMenu = null;

		switch (command) {
		case "search":
			final SortSearchBy sortSearchBy = SortSearchBy.resolve(argsMap);
			final String key = "tags";
			final String tags = genericParser.parseToString(argsMap, key);
			Menu searchMenu = videoController.search(tags, sortSearchBy); 
			return searchMenu;
		case "sortplaylists":
			final SortPlaylistBy sortPlaylistBy = SortPlaylistBy.resolve(argsMap);
			myPlaylistsMenu = playlistController.sortPlaylists(sortPlaylistBy);
			return myPlaylistsMenu;
		case "openplaylist":
			Menu playlistMenu = playlistController.openPlaylist(playlist.getPlaylistName());
			return playlistMenu;
		case "createplaylist":
			myPlaylistsMenu = playlistController.createPlaylist(playlist.getPlaylistName());
			return myPlaylistsMenu;
		case "removeplaylist":
			myPlaylistsMenu = playlistController.removePlaylist(playlist.getPlaylistName());
			return myPlaylistsMenu;
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
