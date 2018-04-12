package services;

import java.sql.SQLException;
import java.util.List;

import controllers.VideoController;
import dataclasses.Channel;
import dataclasses.Playlist;
import dataclasses.Video;
import enums.SortPlaylistBy;
import enums.SortVideoBy;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;

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

	
	public void createPlaylist(String name) throws IllegalInputException, DataBaseException {
		if(name==null){
			throw new  IllegalInputException("INVALID NAME FOR PLAYLIST");
		}
		Channel channel = ChannelService.getInstance().getLoginChannel();
		try {
			playlistDAO.createNewPlaylist(name, channel);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage());
		}
		
	}
	
	public void removePlaylist(String playlist) throws IllegalInputException, DataBaseException {
		try {
			this.playlistDAO.deletePlaylist(playlist);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage());
		}
		
		
	}
	public List<Playlist> sortPlaylists(SortPlaylistBy sortPlaylistBy) throws IllegalInputException, DataBaseException {
		Channel channel = ChannelService.getInstance().getLoginChannel();
		List<Playlist> channelPlaylists;
		try {
			channelPlaylists = this.playlistDAO.getSortedPlaylistForChannelBy(channel,sortPlaylistBy);
			return channelPlaylists;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage());
		}
		
	}
	
	public List<Video> openPlaylist(String playlist_name) throws IllegalInputException, DataBaseException {
		List<Video> playlistVideos  =null;
		try {
			Playlist playlist = playlistDAO.getPlaylistByName(playlist_name);
		     playlistVideos = VideoController.getInstance().getPlaylistVideos(playlist);
		   this.currentOppenedPlaylist=playlist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage());
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

	public void removeVideoFromPlaylist(String videoTitle) throws DataBaseException {
		VideoController.getInstance().deleteVideoFromPlaylist(currentOppenedPlaylist,videoTitle);
	}
  //TODO
	public void sortVideos(SortVideoBy sortVideoBy) {
		// TODO Auto-generated method stub
		
	}

	public Playlist getPlaylist(String playlistName) {
		try {
			return PlaylistDAO.getInstance().getPlaylistByName(playlistName);
		} catch (SQLException e) {
			
		}
		return currentOppenedPlaylist;
	}


}
