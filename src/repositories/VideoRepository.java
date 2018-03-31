package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import dataclasses.Channel;
import dataclasses.Comment;
import dataclasses.Playlist;
import dataclasses.User;
import dataclasses.Video;
import enums.SortVideoBy;
import exceptions.InvalidDataException;

//data na kacvane , vieew >, likes 

public class VideoRepository {
	private static final String SELECT_ALL_VIDEOS_BY_CHANNEL_ID =
			"SELECT video_id,channel_id, url, title, date,description,  views, likes, dislikes FROM videos WHERE channel_id = ?";
    private static final String SEARCH_VIDEOS_BY_TAGS = 
    	 "SELECT v.video_id,v.channel_id, v.url, v.title, v.date, v.description,  v.views, v.likes, v.dislikes  FROM videos v JOIN videos_has_tags h ON"+
           " (v.video_id = h.video_id) WHERE h.tag_id IN ( SELECT tag_id FROM tags t WHERE content = '?')"+
    			 " ORDER BY ? DESC;";
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
	
	public Set<Video> getVideosForChannel(Channel channel) throws SQLException, InvalidDataException {
		   Set<Video> videos = new HashSet<Video>();
			PreparedStatement st = connection.prepareStatement(SELECT_ALL_VIDEOS_BY_CHANNEL_ID);
			st.setInt(1, (int) channel.getChannelId());
			ResultSet rezultSet= st.executeQuery();
			videos.addAll(createVideosFromRezultSet(rezultSet));
			rezultSet.close();
			st.close();
	        // System.out.println("Users loaded successfully");

	        return Collections.unmodifiableSet(videos);
	}

	private  List<Video> createVideosFromRezultSet( ResultSet rezultSet)
			throws SQLException, InvalidDataException {
		List<Video> videos = new ArrayList<Video>();
		while (rezultSet.next()) {
			Channel channel = ChannelRepository.getInstance().getChannelById(rezultSet.getInt("channel_id"));
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
		//	video.setComments(CommentDAO.getInstance().getCommentsForVideo(video));
		//	video.setTags(this.getTagsByVideo(video));
			videos.add(video);

		}
		
		return videos;
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

	public List<Video> getVideosByTag(String tag, SortVideoBy sort) {
		// TODO Auto-generated method stub
		return null;
	}
   
	//TODO
	public void addVideoToChannel(Video video, Channel Channel){
		
	}
	//maybe this method should be in the playlist, but no problem 
	//because have a third table that keeps it
	public void addVideoToPlaylist(Video video,Playlist playlist){
		
	}
	public void deleteVideo(Video video){
		
	}
	public void deleteVideoFromPlaylist(Video video,Playlist playlist){
		
	}
	//video service convert title and description to set of tags add give here 
	public void setTagsForVideo(Video video, Set<String> tags){
		
	}
}
