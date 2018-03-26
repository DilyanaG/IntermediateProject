package services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataclasses.Channel;
import dataclasses.Video;
import enums.SortVideoBy;
import exceptions.InvalidDataException;

public class VideoServices {
	private static final String URL_PATTERN = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	private static VideoServices videoServices;

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

	public List<Video> search(String tags, SortVideoBy sort) throws InvalidDataException {
		return null;
		// TODO Auto-generated method stub
		
	}	

	public void openVideo(Video video) {
		// TODO Auto-generated method stub
		
	}

	public Channel openAuthorsChannel(Video video) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public void addLikeDislikeToVideo(boolean isLike, Video video) {
		// TODO Auto-generated method stub
		
	}

	public void removeLikeDislikeFromVideo(boolean isLike, Video video) {
		// TODO Auto-generated method stub
		
	}


}
