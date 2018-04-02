package services;

import java.sql.SQLException;
import java.util.List;

import controllers.VideoController;
import dataclasses.Channel;
import dataclasses.Playlist;
import dataclasses.Video;
import enums.SortPlaylistBy;
import enums.SortVideoBy;
import exceptions.IllegalChannelArgumentException;
import exceptions.IllegalInputException;
import parsers.PlaylistParser;
import repositories.PlaylistDAO;

public class PlaylistService {
	private static PlaylistService playlistServices;
	
	private PlaylistDAO playlistDAO;
    private Playlist currentOppenedPlaylist;
	
	private PlaylistService() {
		playlistDAO = PlaylistDAO.getInstance();
	}

	public static PlaylistService getInstance() {
		if (playlistServices == null) {
			playlistServices = new PlaylistService();
		}
		return playlistServices;
	}

	
	public void createPlaylist(String name) throws IllegalInputException {
		if(name==null){
			throw new  IllegalInputException("INVALID NAME FOR PLAYLIST");
		}
		Channel channel = ChannelService.getInstance().giveLoginChannel();
		try {
			playlistDAO.createNewPlaylist(name, channel);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new IllegalInputException("DATABASE ERROR");
		}
		
	}
	
	public void removePlaylist(String playlist) throws IllegalInputException {
		try {
			this.playlistDAO.deletePlaylist(playlist);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new IllegalInputException("DATABASE ERROR");
		}
		
		
	}
	public List<Playlist> sortPlaylists(SortPlaylistBy sortPlaylistBy) throws IllegalInputException {
		Channel channel = ChannelService.getInstance().giveLoginChannel();
		List<Playlist> channelPlaylists;
		try {
			channelPlaylists = this.playlistDAO.getSortedPlaylistForChannelBy(channel,sortPlaylistBy);
		} catch (SQLException e) {
			throw new IllegalInputException("DATABASE ERROR!");
		}
		return channelPlaylists;
	}
	
	public List<Video> openPlaylist(String playlist_name) throws IllegalInputException {
		List<Video> playlistVideos  =null;
		try {
			Playlist playlist = playlistDAO.getPlaylistByName(playlist_name);
		     playlistVideos = VideoController.getInstance().getPlaylistVideos(playlist);
		   this.currentOppenedPlaylist=playlist;
		} catch (SQLException e) {
	
			throw new IllegalInputException("DATABASE ERROR!");
		}
		return playlistVideos;
		
	}
	
	public List<Playlist> search(String tags, SortPlaylistBy sort) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public void addVideoToPlaylist(Video video, Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

	public void removeVideoFromPlaylist(String videoTitle) {
		VideoController.getInstance().deleteVideoFromPlaylist(currentOppenedPlaylist,videoTitle);
	}
  //TODO
	public void sortVideos(SortVideoBy sortVideoBy) {
		// TODO Auto-generated method stub
		
	}


}
