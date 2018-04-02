package controllers;

import java.util.List;

import dataclasses.Playlist;
import dataclasses.Video;
import enums.SortPlaylistBy;
import enums.SortVideoBy;
import exceptions.IllegalChannelArgumentException;
import menus.Menu;
import menus.MyPlaylistsMenu;
import menus.PlaylistMenu;
import services.PlaylistService;

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

	// sortBy tells the DB by what to sort it with select and order_by
	public List<Playlist> search(String tags, SortPlaylistBy sort) {
		return playlistServices.search(tags, sort);
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu sortPlaylists(SortPlaylistBy sortPlaylistBy) {
		playlistServices.sortPlaylists(sortPlaylistBy);
		return new MyPlaylistsMenu();
	}

	public Menu createPlaylist(String name) {
		playlistServices.createPlaylist(name);
		return new MyPlaylistsMenu();
	}
	
	public Menu removePlaylist(String playlistName) {
		playlistServices.removePlaylist(playlistName);
		return new MyPlaylistsMenu();
	}

	// TODO if(super.getUser() == null) return visitorPlaylistMenu;
	public Menu openPlaylist(String string) {
		playlistServices.openPlaylist(string);
		return new PlaylistMenu();
	}

	// TODO if(super.getUser() == null) return visitorPlaylistMenu;
	public Menu sortVideos(SortVideoBy sortVideoBy) {
		playlistServices.sortVideos(sortVideoBy);
		return new MyPlaylistsMenu();
	}

	public Menu removeVideoFromPlaylist(String title) {
		playlistServices.removeVideoFromPlaylist(title);
		return new PlaylistMenu();
	}
}
