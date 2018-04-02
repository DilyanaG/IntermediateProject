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
import exceptions.IllegalInputException;
import exceptions.InvalidDataException;

//data na kacvane , vieew >, likes 

public class VideoRepository {
	
	//selects
	private static final String SELECT_ALL_VIDEOS_BY_CHANNEL_ID =
			"SELECT video_id,channel_id, url, title, date,description,  views, likes, dislikes FROM videos WHERE channel_id = ?";
    private static final String SEARCH_VIDEOS_BY_TAGS = 
    	 "SELECT v.video_id,v.channel_id, v.url, v.title, v.date, v.description,  v.views, v.likes, v.dislikes"+
                "FROM videos v JOIN videos_has_tags h ON"+
                       " (v.video_id = h.video_id) WHERE h.tag_id IN ( SELECT tag_id FROM tags t WHERE content = '?')"+
    			              " ORDER BY ? DESC;";
   
    private static final String SELECT_ALL_VIDEOS_BY_PLAYLIST_ID =
			"SELECT  v.video_id,v.channel_id, v.url, v.title, v.date,v.description, v.views, v.likes, v.dislikes FROM videos v"+
                 "JOIN  playlists_has_videos p ON(v.video_id = p.video_id)  WHERE p.playlist_id = ?;";
	
    private static final String ADD_VIDEO_TO_CHANNEL = 
    		" INSERT INTO videos (channel_id, url, title, date, description ,likes, dislikes , views) VALUES (?,'?','?',now(),'?',?,?,?);";
	
    private static final String GET_TAG_ID = 
    		"SELECT tag_id FROM tags WHERE tags.content = '?';";
	
   private static final String GET_TAG_COUNT = 
			"SELECT COUNT (tags.tag_id) FROM tags WHERE tags.content = '?';";
	
	
    //INSERTS
	private static final String INSERT_TAG = 
			"INSERT INTO tags (content) VALUES ('?')";
    
	private static final String WRITE_IN_VIDEOS_HAS_TAGS = 
			"INSERT INTO videos_has_tags (video_id,tag_id) VALUES (?,?)";
	
	private static final String INSERT_VIDEO_IN_PLAYLIST =
			"INSERT INTO playlists_has_videos (video_id,playlist_id) VALUES (?,?)";
    //UPDATES
    
    //DELETE
    
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
	
	public Set<Video> getVideosForChannelBy(Channel channel) throws SQLException, IllegalInputException {
		   Set<Video> videos = new HashSet<Video>();
			PreparedStatement st = connection.prepareStatement(SELECT_ALL_VIDEOS_BY_CHANNEL_ID);
			st.setInt(1, channel.getChannelId());
			ResultSet rezultSet= st.executeQuery();
			videos.addAll(createVideosFromRezultSet(rezultSet));
			rezultSet.close();
			st.close();
	        // System.out.println("Users loaded successfully");

	        return Collections.unmodifiableSet(videos);
	}

	private  List<Video> createVideosFromRezultSet( ResultSet rezultSet)
			throws SQLException,IllegalInputException {
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



	public  List<Video> getAllVideosForPlaylist(Playlist playlist) throws SQLException, IllegalInputException {
		  List<Video> videos =new ArrayList<Video>();
			PreparedStatement st = connection.prepareStatement(SELECT_ALL_VIDEOS_BY_CHANNEL_ID);
			st.setInt(1, playlist.getId());
			ResultSet rezultSet= st.executeQuery();
			videos.addAll(createVideosFromRezultSet(rezultSet));
			rezultSet.close();
			st.close();
			return videos;
	}

	public List<Video> getVideosByTag(String tag, SortVideoBy sort) {
		// TODO Auto-generated method stub
		return null;
	}
   
	//TODO

	public void addVideoToChannel(Video video, Channel channel) throws SQLException{
		PreparedStatement st = connection.prepareStatement(ADD_VIDEO_TO_CHANNEL);
		st.setInt(1 ,channel.getChannelId() );
		st.setString(2,video.getUrl());
		st.setString(3,video.getTitle());
		st.setString(4,video.getDescription());
		st.setInt(5, 0);
		st.setInt(6,0);
		st.setInt(7,0);
		st.executeUpdate();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		int video_id = res.getInt(1);
		this.writeInVideosHasTagsTable(video.getTitle()+" "+video.getDescription(),video_id);
		
		res.close();
		st.close();
	}
	private void writeInVideosHasTagsTable(String videoTags, int video_id) throws SQLException {
		String[] tags = videoTags.split("\\s+");
		for(String tag: tags){
		   this.insertVideoTag(tag ,video_id);
		}
		
		
	}

	private void insertVideoTag(String tag, int video_id) throws SQLException {
		 int countOfTag =this.checkForTag(tag);
		 int tagId = 0;
		if(tagId==0){
			tagId=this.insertTagAndGetId(tag);
		}else{
			tagId=this.getTagId(tag);
		}
		PreparedStatement st = connection.prepareStatement(WRITE_IN_VIDEOS_HAS_TAGS);
		st.setInt(1,video_id);
		st.setInt(2,tagId);
		st.executeUpdate();
		
	}

	private int getTagId(String tag) throws SQLException {
		PreparedStatement st = connection.prepareStatement(GET_TAG_ID);
		st.setString(1,tag);
	    ResultSet res = st.executeQuery();
		res.next();
		int tag_id = res.getInt(1);
		res.close();
		st.close();
		return tag_id;
	}

	private int insertTagAndGetId(String tag) throws SQLException {
		PreparedStatement st = connection.prepareStatement(INSERT_TAG);
		st.setString(1,tag);
	   st.executeUpdate();
	   ResultSet res = st.getGeneratedKeys();
		res.next();
		int tag_id = res.getInt(1);
		res.close();
		st.close();
		return tag_id;
	}

	private int checkForTag(String tag) throws SQLException {
		PreparedStatement st = connection.prepareStatement(GET_TAG_COUNT);
		st.setString(1, tag);
		ResultSet rezultSet= st.executeQuery();
		
		int  count = rezultSet.getInt(1);
		rezultSet.close();
		st.close();
		return count;
	}

	//maybe this method should be in the playlist, but no problem 
	//because have a third table that keeps it
	
	public void addVideoToPlaylist(Video video,Playlist playlist) throws SQLException{
		PreparedStatement st = connection.prepareStatement(INSERT_VIDEO_IN_PLAYLIST);
		st.setInt(1,video.getVideoId());
		st.setInt(2,playlist.getId());
	    st.executeUpdate();
	}
	//TODO
	public void deleteVideo(Video video){
		
	}
	//TODO
	public void deleteVideoFromPlaylist(String videoTitle,Playlist playlist){
		
	}
	//video service convert title and description to set of tags add give here 
	public void setTagsForVideo(Video video, Set<String> tags){
		
	}

	public void deleteChannelVideos(Channel channel) {
		// TODO Auto-generated method stub
		
	}

	public Video getVideoById(int video_id) {
		
		return null;
	}
}

 