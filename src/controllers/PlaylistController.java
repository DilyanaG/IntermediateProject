package controllers;

import java.util.List;

import dataclasses.Channel;
import dataclasses.Playlist;
import dataclasses.Video;
import enums.SortPlaylistBy;
import enums.SortVideoBy;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;
import menus.Menu;
import menus.MyPlaylistsMenu;
import menus.PlaylistMenu;
import menus.VisitorChannelMenu;
import services.PlaylistService;
import ui.UserInterface;

public class PlaylistController {
	private static PlaylistController playlistController;
	private final PlaylistService playlistServices = PlaylistService.getInstance();

	private PlaylistController() {
	}

	public static PlaylistController getInstance() {
		if (playlistController == null) {
			playlistController = new PlaylistController();
		}
		return playlistController;
	}

	
	public Menu createPlaylist(String name) throws IllegalInputException, DataBaseException  {
		playlistServices.createPlaylist(name);
		System.out.println("Playlist added!");
		return new MyPlaylistsMenu();
	}
	
	public Menu removePlaylist(String playlistName) throws IllegalInputException, DataBaseException {
		playlistServices.removePlaylist(playlistName);
		return new MyPlaylistsMenu();
	}
	
	// TODO if(super.getUser() == null) return visitorChannelMenu;
		public Menu sortPlaylists(SortPlaylistBy sortPlaylistBy) throws IllegalInputException, DataBaseException {
			List<Playlist> channelPlaylists = playlistServices.sortPlaylists(sortPlaylistBy);
			UserInterface.getInstance().printPlaylist(channelPlaylists);
			if(ChannelController.getInstance().isLogin())
					return new MyPlaylistsMenu();
			return new VisitorChannelMenu(null);
		}
	
		

	// TODO if(super.getUser() == null) return visitorPlaylistMenu;
	public Menu openPlaylist(String playlist_name) throws IllegalInputException, DataBaseException {
		List<Video> playlistVideos = playlistServices.openPlaylist(playlist_name);
		UserInterface.getInstance().printVideos(playlistVideos);
		return new PlaylistMenu();
	}

		
		//TODO
	// sortBy tells the DB by what to sort it with select and order_by
	public List<Playlist> search(String tags, SortPlaylistBy sort) {
		return playlistServices.search(tags, sort);
	}

    // TODO if(super.getUser() == null) return visitorPlaylistMenu;
	public Menu sortVideos(SortVideoBy sortVideoBy) {
		playlistServices.sortVideos(sortVideoBy);
		return new MyPlaylistsMenu();
	}

	public Menu removeVideoFromPlaylist(String title) throws DataBaseException {
		playlistServices.removeVideoFromPlaylist(title);
		return new PlaylistMenu();
	}

	public List<Playlist> getPlaylistForChannel(Channel channel) {
		
		return null;
	}

	public Playlist getPlaylistByName(String playlistName) {
		Playlist playlist = PlaylistService.getInstance().getPlaylist(playlistName);
		return playlist;
	}
}
