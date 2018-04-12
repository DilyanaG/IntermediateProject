package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.PlaylistController;
import controllers.VideoController;
import dataclasses.Playlist;
import enums.SortPlaylistBy;
import enums.SortSearchBy;
import enums.SortVideoBy;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;
import parsers.GenericParser;
import parsers.PlaylistParser;

public class ChannelMenu extends Menu{

	private GenericParser genericParser = GenericParser.getInstance();
	private PlaylistParser playlistParser = PlaylistParser.getInstance();
	
	private ChannelController channelController = ChannelController.getInstance();
	private PlaylistController playlistController = PlaylistController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	

	private String channelName;

	public ChannelMenu(String channelName) {
		this.channelName = channelName;
	}
	
	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">Follow\n");
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
	protected String titlePresent() {
		// TODO Auto-generated method stub
		return super.titlePresent()+"______CHANNEL "+this.channelName+"_______\n";
	}

	@Override
	public Menu process(String input) throws IllegalInputException, DataBaseException {
		final String command = input.split(" ")[0].toLowerCase();// read first word from input
		final String args = input.substring(command.length()); //

		final Map<String, String> argsMap = parseToMap(args);
		final String keyChannelName = "channelname";
		final String channelName = genericParser.parseToString(argsMap, keyChannelName);

		final int channelID = 0; //TODO think of a way to get the channelID
		
		Menu channelMenu = null;
		
		
		switch (command) {
		case "search":
			final SortSearchBy sortSearchBy = SortSearchBy.resolve(argsMap);
			final String keyTag = "tags";
			final String tags = genericParser.parseToString(argsMap, keyTag);
			Menu searchMenu = videoController.search(tags, sortSearchBy); 
			return searchMenu;
		case "follow":
			channelMenu = channelController.followChannel(this.channelName);
			return channelMenu;
		case "showvideos":
			channelMenu = channelController.showVideos();
			return channelMenu;
		case "sortvideos":
			final SortVideoBy sortVideoBy = SortVideoBy.resolve(argsMap);
			channelMenu = videoController.sortVideos(sortVideoBy);
			return channelMenu;
		case "openvideo":
			final String keyTitle = "title";
			final String title = genericParser.parseToString(argsMap, keyTitle);
			Menu videoMenu = videoController.openVideo(title);
			return videoMenu;
		case "showplaylists":
			channelMenu = channelController.showPlaylists();
			return channelMenu;
		case "sortplaylists":
			final SortPlaylistBy sortPlaylistBy = SortPlaylistBy.resolve(argsMap);
			channelMenu = playlistController.sortPlaylists(sortPlaylistBy);
			return channelMenu;
		case "openplaylist":
			final Playlist playlist = playlistParser.parse(argsMap);
			Menu playlistMenu = playlistController.openPlaylist(playlist.getPlaylistName());
			return playlistMenu;
		case "showchannels":
			channelMenu = channelController.showChannels();
			return channelMenu;
		case "openchannel":
			channelMenu = channelController.openChannel(channelName);
			return channelMenu;
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
