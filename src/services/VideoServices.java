package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import controllers.CommentController;
import controllers.PlaylistController;
import dataclasses.Channel;
import dataclasses.Comment;
import dataclasses.Playlist;
import dataclasses.Video;
import enums.SortSearchBy;
import enums.SortVideoBy;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;
import repositories.VideoDAO;
import ui.UserInterface;

public class VideoServices {
	
	private static final String URL_PATTERN = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	private static VideoServices videoServices;
	
	VideoDAO videoDao = VideoDAO.getInstance();
    private Video currentVideo = null;
	private VideoServices() {
	}

	public static VideoServices getInstance() {
		if (videoServices == null) {
			videoServices = new VideoServices();
		}
		return videoServices;
	}

	private boolean checkForUrl(String url) {
		if (url != null) {
			try {
				Pattern patt = Pattern.compile(URL_PATTERN);
				Matcher matcher = patt.matcher(url);
				return matcher.matches();
			} catch (RuntimeException e) {
				return false;
			}

		}
		return false;
	}

	public void addVideoToChannel(Video video, Channel channel) throws IllegalInputException, DataBaseException {
		if(video.getTitle()==null||
				video.getUrl()==null)
			throw new IllegalInputException("INVALID DATA FOR NEW VIDEO");
		try {
			videoDao.addVideoToChannel(video, channel);
		} catch (SQLException e) {
			
			throw new DataBaseException(e.getMessage());
		}
	}
	
    public List<Video> search(String tag, SortSearchBy sort) throws IllegalInputException, DataBaseException {
		List<Video> videos=null;
		try {
			videos = videoDao.getVideosByTag(tag,sort);
		
		UserInterface.getInstance().printVideos(videos);
		} catch (SQLException e) {
			
			throw new DataBaseException(e.getMessage());
		}
		return videos;
		
		
	}	
	
	public List<Video> getPlaylistVideos(Playlist playlist) throws IllegalInputException, DataBaseException {
		List<Video> playlistVideos =null;
		try {
			playlistVideos = videoDao.getAllVideosForPlaylist(playlist);
		} catch (SQLException e) {
			throw new  DataBaseException(e.getMessage());
		} 
		return playlistVideos;
	}

	public void deleteVideoFromPlaylist(Playlist playlist, String videoTitle) throws DataBaseException {
		 try {
			videoDao.deleteVideoFromPlaylist(videoTitle, playlist);
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
		 
		
	}

	public Video openVideo(String videoname) throws IllegalInputException, DataBaseException {
		try {
			this.currentVideo = videoDao.getVideoByTitle(videoname);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new  DataBaseException(e.getMessage());
		}
		return currentVideo;
		}
	
	public List<Video> getChannelVideos(Channel channel) throws IllegalInputException, DataBaseException{
		List<Video> videos = null;
		try {
			videos =videoDao.getVideosForChannelBy(channel);
		} catch (SQLException  e) {
			throw new  DataBaseException(e.getMessage());
			
		}
		return videos;		
	}
 
	public Video getCurrentVideo() {
	return currentVideo;
}
	
	public void removeVideo(String title) throws IllegalInputException, DataBaseException {
		try {
			videoDao.deleteVideo(title);
		} catch (SQLException e) {
			throw new  DataBaseException(e.getMessage());
		} 
		
	}

	public List<Comment> showVideoComments(String title) throws IllegalInputException, DataBaseException {
		
		List<Comment> comments = CommentController.getInstance().getCommentsForVideo(currentVideo);
		return comments;
	}

	public void addLikeDislikeToVideo(boolean isLike, Video video) {
		// TODO Auto-generated method stub
		
	}

	public void removeLikeDislikeFromVideo(boolean isLike, String title) {
		// TODO Auto-generated method stub
		
	}

     public void addVideoToPlaylist(String playlistName) throws DataBaseException {
		Video video = currentVideo;
		Playlist playlist= PlaylistController.getInstance().getPlaylistByName(playlistName);
		try {
			videoDao.addVideoToPlaylist(video, playlist);
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		} 
		
		
	}

	public Channel openAuthorsChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addLikeDislikeToVideo(boolean isLike, String title) {
		// TODO Auto-generated method stub
		
	}

	public List<Video> sortChannelVideos(SortVideoBy sortVideoBy,Channel channel) 
			          throws DataBaseException, IllegalInputException {
		List<Video> videos = new ArrayList<>();
		try {
			videos = videoDao.getSortedVideosForChannel(sortVideoBy,
					channel);
		} catch (SQLException e) {
			throw new  DataBaseException(e.getMessage());
		}
		return videos;
	}




}
