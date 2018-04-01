package controllers;

import java.util.List;
import java.util.Map;

import dataclasses.Channel;
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
import services.VideoServices;

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
			videoServices.search(tags, sort);
			return new SearchMenu();
	}
	
	// TODO if(super.getUser() == null) return visitorChannelMenu;
		public Menu sortVideos(SortVideoBy sortVideoBy) {
			videoServices.sortVideos(sortVideoBy);
			return new MyVideosMenu();
		}
		
	//TODO if(super.getUser() == null) return visitorVideoMenu;
	public Menu openVideo(String string) {
		videoServices.openVideo(string);
		return new VideoMenu();
	}
	
	public Menu removeVideo(String title) {
		videoServices.removeVideo(title);
		return new MyVideosMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu openAuthorsChannel(String title) {
		videoServices.openAuthorsChannel(title);
		return new ChannelMenu();
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
	public Menu showVideoComments(String title) {
		videoServices.showVideoComments(title);
		return new CommentMenu();
	}

	
	///////////////////////
	
	// Why do we need this?
	public Map<Integer, Video> giveVideosToChannel(Channel channel) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
