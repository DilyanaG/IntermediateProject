package controllers;

import java.util.List;
import java.util.Map;

import dataclasses.Channel;
import dataclasses.Comment;
import dataclasses.Playlist;
import dataclasses.Video;
import enums.SortSearchBy;
import enums.SortVideoBy;
import exceptions.IllegalInputException;
import exceptions.InvalidDataException;
import menus.ChannelMenu;
import menus.CommentMenu;
import menus.DefaultMenu;
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
	public Menu search(String tags, SortSearchBy sort){
		try {
			if(sort==null){
				sort=SortSearchBy.DATE;
			}
			List<Video> videos = videoServices.search(tags, sort);
		} catch (IllegalInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return new SearchMenu();
	
	  //TODO how to print videos to console
			//TODO response: the UI will print them; 
	}
	
	// TODO if(super.getUser() == null) return visitorChannelMenu;
		public Menu sortVideos(SortVideoBy sortVideoBy) {
			videoServices.sortChannelVideos(sortVideoBy);
			return new MyVideosMenu();
		}
		
	//TODO if(super.getUser() == null) return visitorVideoMenu;
	public Menu openVideo(String string) throws IllegalInputException {
		
		
		Video video = videoServices.openVideo(string);
		UserInterface.getInstance().showVideo(video);
		if(ChannelController.getInstance().isLogin()){
			return new VideoMenu();
		}
		return new VisitorVideoMenu();
		
	}
	
	public Menu removeVideo(String title) throws IllegalInputException {
		videoServices.removeVideo(title);
		System.out.println(title+" has been deleted!");
		return new MyVideosMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu openAuthorsChannel() throws IllegalInputException {
		Channel channel = videoServices.openAuthorsChannel();
		if(ChannelController.getInstance().isLogin()){
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

	public Menu addVideoToPlaylist(String title, String playlistName) {
		videoServices.addVideoToPlaylist(title, playlistName);
		return new VideoMenu();
	}
	
	// TODO if(super.getUser() == null) return visitorCommentMenu;
	public Menu showVideoComments(String title) throws IllegalInputException {
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
	public List<Video> getPlaylistVideos(Playlist playlist) throws IllegalInputException {
		
		return videoServices.getPlaylistVideos(playlist);
	}

	public void deleteVideoFromPlaylist(Playlist currentOppenedPlaylist, String videoTitle) {
		videoServices.deleteVideoFromPlaylist(currentOppenedPlaylist,videoTitle);
		
	}

	public List<Video> getVideosToChannel(Channel loginChannel) throws IllegalInputException {
		List<Video> videos = videoServices.getChannelVideos(loginChannel);
		return videos;
	}

	public void addVideoToChannel(Video video, Channel channel) throws IllegalInputException {
		this.videoServices.addVideoToChannel(video,channel);
		
	}

	public Video getOpenedVideo() {
		return videoServices.getCurrentVideo();
	}

	

	
}
