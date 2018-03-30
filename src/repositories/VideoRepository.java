package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dataclasses.Channel;
import dataclasses.Comment;
import dataclasses.Playlist;
import dataclasses.User;
import dataclasses.Video;

//data na kacvane , vieew >, likes 

public class VideoRepository {
	private static final String SELECT_ALL_VIDEOS_BY_CHANNEL_ID =
			"SELECT video_id, url, title, date,discription,  views, likes, dislikes,  FROM videos WHERE channel_id = ?";

	private static VideoRepository instance;
	private Connection connection;
	
	private VideoRepository() {
		 connection = DBManager.getInstance().getConnection();

	}

	public static VideoRepository getInstance() {
		if (instance == null) {
			instance = new VideoRepository();
		}
		return instance;
	}
	
	public Set<Video> getVideosForChannel(Channel channel) throws SQLException {
		Set<Video> videos = new HashSet<>();
			PreparedStatement st = connection.prepareStatement(SELECT_ALL_VIDEOS_BY_CHANNEL_ID);
			st.setInt(1, (int) channel.getChannelId());
			ResultSet rezultSet= st.executeQuery();
			// get all channles
			while (rezultSet.next()) {
				Video video = new Video(rezultSet.getInt("video_id"),
						                rezultSet.getString("url"), 
										channel,
										rezultSet.getString("title"), 
										rezultSet.getString("discription"),
										rezultSet.getDate("date"),
										rezultSet.getInt("likes"),
										rezultSet.getInt("dislikes"),
									    rezultSet.getInt("views"));
				// to do 
				video.setComments(CommentDAO.getInstance().getCommentsForVideo(video));
				video.setTags(this.getTagsByVideo(video));
				videos.add(video);
		
			}
			rezultSet.close();
			st.close();
	        // System.out.println("Users loaded successfully");

	        return Collections.unmodifiableSet(videos);
	}

	

	private Set<String> getTagsByVideo(Video video) {
		// TODO Auto-generated method stub
		//TODO
		return Collections.EMPTY_SET;
	}

	public  Set<Video> getAllVideosForPlaylist(Playlist playlist) {
		//write select for this
		// TODO Auto-generated method stub
		return Collections.EMPTY_SET;
	}

}
