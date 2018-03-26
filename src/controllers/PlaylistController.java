package controllers;

import java.util.List;

import dataclasses.Playlist;
import dataclasses.Video;
import enums.SortPlaylistBy;
import exceptions.IllegalChannelArgumentException;
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

	//TODO check if those were the needed sortings
	public List<Playlist> search(String tags, SortPlaylistBy sort) {
		return playlistServices.search(tags, sort);
	}

	public void openPlaylist(Playlist playlist) {
		playlistServices.openPlaylist(playlist);
	}
	
	public Playlist createPlaylist(String name) throws IllegalChannelArgumentException {
		return playlistServices.createPlaylist(name);
	}

	public void removePlaylist(Playlist playlist) {
		playlistServices.removePlaylist(playlist);
	}
	
	public void addVideoToPlaylist(Video video, Playlist playlist) {
		playlistServices.addVideoToPlaylist(video, playlist);
	}

	public void removeVideoFromPlaylist(Video video, Playlist playlist) {
		playlistServices.removeVideoFromPlaylist(video, playlist);
	}
}
