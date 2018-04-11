package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import dataclasses.Channel;
import dataclasses.Playlist;
import enums.SortPlaylistBy;

public class PlaylistDAO {
	//DB
	//selects
	private static final String SELECT_BY_NAME =
			"SELECT playlist_id,name, create_date,last_video_add_date  FROM playlists WHERE name =?;";
	
	private static final String SELECT_ALL_PLAYLIST_BY_CHANNEL_ID =
			"SELECT playlist_id, name, last_video_add_date, create_date  FROM playlists WHERE channel_id = ?;";
	private static final String ALL_PLAYLISTS = 
			"SELECT playlist_id,name, create_date,last_video_add_date  from playlists ;";
	private static final String BY_LAST_ADDED_VIDEO_DATE =
			" SELECT playlist_id,name, create_date,last_video_add_date  FROM playlists WHERE channel_id =?;" +
                             "ORDER BY last_video_add_date DESC;";
	private static final String BY_CREATE_DATE =
			" SELECT playlist_id,name, create_date,last_video_add_date  FROM playlists WHERE channel_id =?;" +
                             "ORDER BY create_date DESC;";
	private static final String BY_NAME =
			" SELECT playlist_id,name, create_date,last_video_add_date  FROM playlists WHERE channel_id =?" +
                             "ORDER BY name ;";
	
  //updates
	private static final String UPDATE_LAST_VIDEO_ADDING_DATE = 
             " UPDATE playlists SET last_video_add_date = now() WHERE playlist_id = ?; ";
	private static final String UPDATE_NAME = 
			"UPDATE playlists SET name = ? WHERE playlist_id = ?; ";
	//insert
	private static final String CREATE_NEW_PLAYLIST = 
			"INSERT INTO playlists (channel_id, name, create_date, last_video_add_date) VALUES (?,?,now(),now());";
	
	//delete
	private static final String DELETE_FROM_PLAYLIST_HAS_VIDEOS_TABLE = 
			"DELETE FROM playlists_has_videos WHERE playlist_id = ?;"; 
	private static final String DELETE_PLAYLIST =
			"DELETE FROM playlists WHERE name = ?;";
	
	
	private static PlaylistDAO instance;
	private Connection connection;
	
	private PlaylistDAO() {
		 connection = DBManager.getInstance().getConnection();

	}

	public static PlaylistDAO getInstance() {
		if (instance == null) {
			instance = new PlaylistDAO();
		}
		return instance;
	}
    
	public void createNewPlaylist(String playlistName,Channel channel) throws SQLException{
		PreparedStatement st = connection.prepareStatement(CREATE_NEW_PLAYLIST);
		st.setInt(1, channel.getChannelId());
		st.setString(2,playlistName);
		st.executeUpdate();
		st.close();
	}
	
	public List<Playlist> getPlaylistForChannel(Channel channel) throws SQLException {
		List<Playlist> playlists =new  ArrayList<>();
		PreparedStatement st = connection.prepareStatement(SELECT_ALL_PLAYLIST_BY_CHANNEL_ID);
		st.setInt(1,channel.getChannelId());
		ResultSet rezultSet= st.executeQuery();
		// get all playlist for channel
		playlists = createPlaylistsFromRezultSet(rezultSet);
		rezultSet.close();
		st.close();

        return Collections.unmodifiableList(playlists);
	}

	private List<Playlist>  createPlaylistsFromRezultSet(ResultSet rezultSet) throws SQLException {
		List<Playlist> playlists = new ArrayList<>();
		while (rezultSet.next()) {
			
			
		Playlist playlist = new Playlist(rezultSet.getInt("playlist_id"), 
				                         rezultSet.getString("name"),
				                         rezultSet.getDate("last_video_add_date"),
				                         rezultSet.getDate("create_date"));
			playlists.add(playlist);
		}
		return playlists;
	}
   
	public void updataLastVideoAddDate(Playlist playlist) throws SQLException{
		PreparedStatement st = connection.prepareStatement(UPDATE_LAST_VIDEO_ADDING_DATE);
		st.setInt(1, playlist.getId());
		st.executeUpdate();
		st.close();
	}

	public void deletePlaylist(String playlist_name) throws SQLException{
	   Playlist playlist =	this.getPlaylistByName(playlist_name);
		this.deletePlaylistFromTable(playlist.getId());
		PreparedStatement st = connection.prepareStatement(DELETE_PLAYLIST);
		st.setString(1, playlist_name);
		st.executeUpdate();
		st.close();
	}

	private void deletePlaylistFromTable(int id) throws SQLException {
		PreparedStatement st = connection.prepareStatement(DELETE_FROM_PLAYLIST_HAS_VIDEOS_TABLE);
		st.setInt(1, id);
		st.executeUpdate();
		st.close();
	}

	public void renamePlaylistName(Playlist playlist,String newName) throws SQLException{
		PreparedStatement st = connection.prepareStatement(UPDATE_NAME);
		st.setString(1, newName);
		st.setInt(2, playlist.getId());
		st.executeUpdate();
		st.close();
	}
	
	//TODO SortPlaylist   ?
	
	public List<Playlist> getSortedPlaylistForChannelBy(Channel channel,SortPlaylistBy sortBy) throws SQLException{
		
		String sort ="";
		switch(sortBy){
		case NEWEST:{
			
		}
		case OLDEST:{
			
		}
		case VIDEOS: {
			
		}
		}
		
        
		List<Playlist> playlists =new  ArrayList<>();
		PreparedStatement st = connection.prepareStatement(sort);
		st.setInt(1, channel.getChannelId());
		ResultSet rezultSet= st.executeQuery();
		// get all playlist for channel
		playlists = createPlaylistsFromRezultSet(rezultSet);
		rezultSet.close();
		st.close();

        return Collections.unmodifiableList(playlists);
		
	}
	
	public List<Playlist>  getAllPlaylists() throws SQLException{
		List<Playlist> playlists =new  ArrayList<>();
		PreparedStatement st = connection.prepareStatement(ALL_PLAYLISTS);
		ResultSet rezultSet= st.executeQuery();
		// get all playlist for channel
		playlists = createPlaylistsFromRezultSet(rezultSet);
		rezultSet.close();
		st.close();

        return Collections.unmodifiableList(playlists);
	}

	public void deleteChannelPlaylists(Channel channel) throws SQLException {
	  
		for(Playlist playlist : this.getPlaylistForChannel(channel)){
			this.deletePlaylist(playlist.getPlaylistName());
		}
		
	}

	public Playlist getPlaylistByName(String playlist_name) throws SQLException {
		PreparedStatement st = connection.prepareStatement(SELECT_BY_NAME);
		st.setString(1,playlist_name);
		ResultSet rezultSet= st.executeQuery();
		rezultSet.next();
			
			
		Playlist playlist = new Playlist(rezultSet.getInt("playlist_id"), 
				                         rezultSet.getString("name"),
				                         rezultSet.getDate("last_video_add_date"),
				                         rezultSet.getDate("create_date"));
		
		return playlist;
	}
	

}
