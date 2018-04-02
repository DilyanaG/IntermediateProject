package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.PlaylistController;
import controllers.VideoController;
import dataclasses.Playlist;
import enums.SortPlaylistBy;
import enums.SortSearchBy;
import enums.SortVideoBy;
import exceptions.IllegalInputException;
import parsers.GenericParser;
import parsers.PlaylistParser;

public class VisitorChannelMenu extends Menu{
	private GenericParser genericParser = GenericParser.getInstance();
	private PlaylistParser playlistParser = PlaylistParser.getInstance();
	
	private ChannelController channelController = ChannelController.getInstance();
	private PlaylistController playlistController = PlaylistController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	
	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">ShowVideos\n");
		builder.append(">SortVideos -sortvideoby=NEWEST_OLDEST_or_LIKES\n");
		builder.append(">OpenVideo -title=title_here\n");
		builder.append(">ShowPlaylists\n");
		builder.append(">SortPlaylists -sortplaylistby=NEWEST_OLDEST_or_VIDEOS\n");
		builder.append(">OpenPlaylist -playlistname=playlistname_here\n");
		builder.append(">ShowChannels\n");
		builder.append(">OpenChannel -channelname=channelname_here\n");
		builder.append(">Homepage\n");
		return builder.toString();
	}

	@Override
	public Menu process(String input) throws IllegalInputException {
		final String command = input.split(" ")[0].toLowerCase();// read first word from input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args);
		final String keyChannelName = "channelname";
		final String channelName = genericParser.parseToString(argsMap, keyChannelName);

		final int channelID = 0; //TODO think of a way to get the channelID
		
		Menu visitorChannelMenu = null;
		
		switch (command) {
		case "search":
			final SortSearchBy sortBy = SortSearchBy.resolve(argsMap);
			final String key = "tags";
			final String tags = genericParser.parseToString(argsMap, key);
			Menu searchMenu = videoController.search(tags, sortBy); 
			return searchMenu;
		case "showvideos":
			visitorChannelMenu = channelController.showVideos(channelID);
			return visitorChannelMenu;
		case "sortvideos":
			final SortVideoBy sortVideoBy = SortVideoBy.resolve(argsMap);
			visitorChannelMenu = videoController.sortVideos(sortVideoBy);
			return visitorChannelMenu;
		case "openvideo":
			final String keyTitle = "title";
			final String title = genericParser.parseToString(argsMap, keyTitle);
			Menu VisitorVideoMenu = videoController.openVideo(title);
			return VisitorVideoMenu;
		case "showplaylists":
			visitorChannelMenu = channelController.showPlaylists(channelID);
			return visitorChannelMenu;
		case "sortplaylists":
			final SortPlaylistBy sortPlaylistBy = SortPlaylistBy.resolve(argsMap);
			visitorChannelMenu = playlistController.sortPlaylists(sortPlaylistBy);
			return visitorChannelMenu;
		case "openplaylist":
			final Playlist playlist = playlistParser.parse(argsMap);
			Menu visitorPlaylistMenu = playlistController.openPlaylist(playlist.getPlaylistName());
			return visitorPlaylistMenu;
		case "showchannels":
			visitorChannelMenu = channelController.showChannels(channelID);
			return visitorChannelMenu;
		case "openchannel":
			visitorChannelMenu = channelController.openChannel(channelName);
			return visitorChannelMenu;
		case "homepage":
			Menu defaultMenu = channelController.homepage();
			return defaultMenu;
		case "exit":
			Menu exitMenu = null;
			return exitMenu;
			
		default:
			// throw new IllegalInputException();
			break;
		}

		return null;
	}
}
