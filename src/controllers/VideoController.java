package controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dataclasses.Channel;
import dataclasses.Comment;
import dataclasses.Playlist;
import dataclasses.Video;
import enums.SortSearchBy;
import enums.SortVideoBy;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;
import menus.ChannelMenu;
import menus.CommentMenu;
import menus.DefaultMenu;
import menus.HomeMenu;
import menus.Menu;
import menus.MyVideosMenu;
import menus.SearchMenu;
import menus.VideoMenu;
import menus.VisitorChannelMenu;
import menus.VisitorCommentMenu;
import menus.VisitorVideoMenu;
import repositories.VideoDAO;
import menus.Menu;
import menus.SearchMenu;
import services.VideoServices;
import ui.UserInterface;

public class VideoController {

	private static VideoController videoController;
	private final VideoServices videoServices = VideoServices.getInstance();

	private VideoController() {
	}

	public static VideoController getInstance() {
		if (videoController == null) {
			videoController = new VideoController();
		}
		return videoController;
	}

	
	// sortBy tells the DB by what to sort it with select and order_by
	public Menu search(String tags, SortSearchBy sort) throws IllegalInputException, DataBaseException{
			if(sort==null){
				sort=SortSearchBy.DATE;
			}
			List<Video> videos = videoServices.search(tags, sort);
		     //  UserInterface.getInstance().printVideos(videos);
			return new SearchMenu();
	
	  
	}
	
	// TODO if(super.getUser() == null) return visitorChannelMenu;
		public Menu sortVideos(SortVideoBy sortVideoBy) throws IllegalInputException, DataBaseException {
			System.out.println(sortVideoBy);
			List<Video> videos = videoServices.sortChannelVideos(sortVideoBy,
					ChannelController.getInstance().getLoginChannel());
			UserInterface.getInstance().printVideos(videos);
			if(ChannelController.getInstance().isLogin()){
			   return new MyVideosMenu();
			}
			return new VisitorChannelMenu(null);
		}
		
	//TODO if(super.getUser() == null) return visitorVideoMenu;
	public Menu openVideo(String string) throws IllegalInputException, DataBaseException {
		Video video = videoServices.openVideo(string);
		//print video 
		UserInterface.getInstance().showVideo(video);
		if(ChannelController.getInstance().isLogin()){
			return new VideoMenu();
		}
		return new VisitorVideoMenu();
		
	}
	
	public Menu removeVideo(String title) throws IllegalInputException, DataBaseException {
		videoServices.removeVideo(title);
		System.out.println(title+" has been deleted!");
		return new MyVideosMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu openAuthorsChannel() throws IllegalInputException, DataBaseException {
		Channel channel = videoServices.openAuthorsChannel();
		if(ChannelController.getInstance().isLogin()){
			
			if(videoServices.getCurrentVideo().getChannel().getUser().getUserName().equals(
				ChannelController.getInstance().getLoginChannel().getUser().getUserName())){
				return new HomeMenu();
			}
		   return new ChannelMenu(videoServices.getCurrentVideo().getChannel().getUser().getUserName());
		}
		return new VisitorChannelMenu(videoServices.getCurrentVideo().getChannel().getUser().getUserName());
	}

	public Menu addLikeDislikeToVideo(boolean isLike, String title) {
		videoServices.addLikeDislikeToVideo(isLike, title);
		return new VideoMenu();
	}

	public Menu removeLikeDislikeFromVideo(boolean isLike,  String title) {
		videoServices.removeLikeDislikeFromVideo(isLike, title);
		return new VideoMenu();
	}

	public Menu addVideoToPlaylist(String playlistName) throws DataBaseException {
		videoServices.addVideoToPlaylist(playlistName);
		return new VideoMenu();
	}
	
	// TODO if(super.getUser() == null) return visitorCommentMenu;
	public Menu showVideoComments(String title) throws IllegalInputException, DataBaseException {
		List<Comment> comments=  videoServices.showVideoComments(title);
		if(comments==null||comments.isEmpty()){
	            System.out.println("NO COMMENTS!");
	            if(ChannelController.getInstance().isLogin()){
	    	       	return new VideoMenu();
	    		}
	            return new VisitorVideoMenu();
		}
		UserInterface.getInstance().printComments(comments);
		if(ChannelController.getInstance().isLogin()){
	       	return new CommentMenu();
		}
		return new VisitorCommentMenu();
	}

	

//TODO
	public List<Video> getPlaylistVideos(Playlist playlist) 
			throws IllegalInputException, DataBaseException {
		
		return videoServices.getPlaylistVideos(playlist);
	}

	public void deleteVideoFromPlaylist(Playlist currentOppenedPlaylist, String videoTitle) 
			throws DataBaseException {
		videoServices.deleteVideoFromPlaylist(currentOppenedPlaylist,videoTitle);
		
	}

	public List<Video> getVideosToChannel(Channel loginChannel) 
			throws IllegalInputException, DataBaseException {
		List<Video> videos = videoServices.getChannelVideos(loginChannel);
		return videos;
	}

	public void addVideoToChannel(Video video, Channel channel) 
			throws IllegalInputException, DataBaseException {
		this.videoServices.addVideoToChannel(video,channel);
		
	}

	public Video getOpenedVideo() {
		return videoServices.getCurrentVideo();
	}

	

	
}
