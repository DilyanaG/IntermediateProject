package controllers;

import java.util.List;
import java.util.Map;

import dataclasses.Channel;
import dataclasses.Video;
import enums.SortVideoBy;
import exceptions.InvalidDataException;
import menus.Menu;
import menus.SearchMenu;
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
     
      
	//TODO sortBy as a parameter and make the DB sort it with select and orders
	public Menu search(String tags, SortVideoBy sort) {
	  try {
		List<Video>	videos = videoServices.search(tags, sort);
	} catch (InvalidDataException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  //TO DO 
	 // how to print videos to console
		return null;
	}
	
	//TODO instead of Video -> videoID 
	public void openVideo(Video video) {
		videoServices.openVideo(video);
	}
	
	public void removeVideo(Video video) {
		videoServices.removeVideo(video);
	}

	public Channel openAuthorsChannel(Video video) {
		return videoServices.openAuthorsChannel(video);
	}

	public void addLikeDislikeToVideo(boolean isLike, Video video) {
		videoServices.addLikeDislikeToVideo(isLike, video);
	}

	public void removeLikeDislikeFromVideo(boolean isLike,  Video video) {
		videoServices.removeLikeDislikeFromVideo(isLike, video);
	}

	public Map<Integer, Video> giveVideosToChannel(Channel channel) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
