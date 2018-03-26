package services;

import java.util.List;

import dataclasses.Playlist;
import dataclasses.Video;
import enums.SortPlaylistBy;
import exceptions.IllegalChannelArgumentException;
import parsers.PlaylistParser;

public class PlaylistService {
	private static PlaylistService playlistServices;

	private PlaylistService() {
	}

	public static PlaylistService getInstance() {
		if (playlistServices == null) {
			playlistServices = new PlaylistService();
		}
		return playlistServices;
	}

	public List<Playlist> search(String tags, SortPlaylistBy sort) {
		// TODO Auto-generated method stub
		return null;
	}

	public void openPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

	public Playlist createPlaylist(String name) throws IllegalChannelArgumentException {
		PlaylistParser newParser = PlaylistParser.getInstance();
		return newParser.parse(name);
	}

	public void removePlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

	public void addVideoToPlaylist(Video video, Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

	public void removeVideoFromPlaylist(Video video, Playlist playlist) {
		// TODO Auto-generated method stub
		
	}
}
