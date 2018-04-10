package services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controllers.CommentController;
import dataclasses.Channel;
import dataclasses.Comment;
import dataclasses.Playlist;
import dataclasses.Video;
import enums.SortSearchBy;
import enums.SortVideoBy;
import exceptions.IllegalInputException;
import exceptions.InvalidDataException;
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

	public List<Video> search(String tag, SortSearchBy sort) throws IllegalInputException {
		List<Video> videos=null;
		try {
			videos = videoDao.getVideosByTag(tag,sort);
		
		UserInterface.getInstance().printVideos(videos);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalInputException("DATABASE PROBLEM!");
		}
		return videos;
		
		
	}	
	public List<Video> getPlaylistVideos(Playlist playlist) throws IllegalInputException {
		List<Video> playlistVideos =null;
		try {
			playlistVideos = videoDao.getAllVideosForPlaylist(playlist);
		} catch (SQLException e) {
			throw new IllegalInputException("DATABASE ERROR!");
		} 
		return playlistVideos;
	}

	public void deleteVideoFromPlaylist(Playlist playlist, String videoTitle) {
		 videoDao.deleteVideoFromPlaylist(videoTitle, playlist);
		
	}

	public Video openVideo(String videoname) throws IllegalInputException {
		try {
			this.currentVideo = videoDao.getVideoByTitle(videoname);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalInputException(e.getMessage()+"\n DATABASE ERROR!");
		}
		return currentVideo;
		}
	
	public List<Video> getChannelVideos(Channel channel) throws IllegalInputException{
		List<Video> videos = null;
		try {
			videos =videoDao.getVideosForChannelBy(channel);
		} catch (SQLException  e) {
			System.out.println(e.getMessage()+"\n DATABASE ERROR");
			e.printStackTrace();
		}
		return videos;		
	}
 public Video getCurrentVideo() {
	return currentVideo;
}
	public Channel openAuthorsChannel(Video video) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public void addLikeDislikeToVideo(boolean isLike, Video video) {
		// TODO Auto-generated method stub
		
	}

	public void removeLikeDislikeFromVideo(boolean isLike, String title) {
		// TODO Auto-generated method stub
		
	}

	public void removeVideo(String title) throws IllegalInputException {
		try {
			videoDao.deleteVideo(title);
		} catch (SQLException e) {
			throw new IllegalInputException("DATABASE ERROR!"+e.getMessage());
		} 
		
	}
//TODO
	

	public List<Comment> showVideoComments(String title) throws IllegalInputException {
		
		List<Comment> comments = CommentController.getInstance().getCommentsForVideo(currentVideo);
		return comments;
	}

	public void addVideoToPlaylist(String title, String playlistName) {
		// TODO Auto-generated method stub
		
	}

	public Channel openAuthorsChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addLikeDislikeToVideo(boolean isLike, String title) {
		// TODO Auto-generated method stub
		
	}

	public void sortChannelVideos(SortVideoBy sortVideoBy) {
		// TODO Auto-generated method stub
		
	}

	public void addVideoToChannel(Video video, Channel channel) throws IllegalInputException {
		if(video.getTitle()==null||
				video.getUrl()==null)
			throw new IllegalInputException("INVALID DATA FOR NEW VIDEO");
		try {
			videoDao.addVideoToChannel(video, channel);
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new IllegalInputException(e.getMessage()+"DATABASE ERROR!");
		}
	}


}
