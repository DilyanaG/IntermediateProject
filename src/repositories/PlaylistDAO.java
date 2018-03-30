package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import dataclasses.Channel;
import dataclasses.Playlist;
import dataclasses.Video;

public class PlaylistDAO {
	private static final String SELECT_ALL_PLAYLIST_BY_CHANNEL_ID =
			"SELECT playlist_id, name, last_video_add_date, create_date  FROM playlists WHERE channel_id = ?";
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

	public Set<Playlist> getPlaylistForChannel(Channel channel) throws SQLException {
		Set<Playlist> playlists = new HashSet<>();
		PreparedStatement st = connection.prepareStatement(SELECT_ALL_PLAYLIST_BY_CHANNEL_ID);
		//st.setInt(1,channel.getChannelId());
		
		
		ResultSet rezultSet= st.executeQuery();
		// get all playlist for channel
		while (rezultSet.next()) {
			
			
		Playlist playlist = new Playlist(rezultSet.getInt("playlist_id"), 
				                         rezultSet.getString("name"),
				                         rezultSet.getDate("last_video_add_date"),
				                         rezultSet.getDate("create_date"));
		    playlist.setVideos(VideoRepository.getInstance().getAllVideosForPlaylist(playlist));
			playlists.add(playlist);
	
		}
		rezultSet.close();
		st.close();
        // System.out.println("Users loaded successfully");

        return Collections.unmodifiableSet(playlists);
	}


}
