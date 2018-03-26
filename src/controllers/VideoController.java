package controllers;

import java.util.List;

import dataclasses.Channel;
import dataclasses.Video;
import enums.SortVideoBy;
import exceptions.InvalidDataException;
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
	public List<Video> search(String tags, SortVideoBy sort) throws InvalidDataException {
		return videoServices.search(tags, sort);
	}
	
	//TODO instead of Video -> videoID 
	public void openVideo(Video video) {
		videoServices.openVideo(video);
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
	
}
