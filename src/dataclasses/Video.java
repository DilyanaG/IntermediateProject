package dataclasses;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import exceptions.IllegalChannelArgumentException;
import exceptions.IllegalURLException;
import exceptions.IllegalVideoDescriptionException;
import exceptions.IllegalVideoTitleException;

public class Video {

	private static final String DEFAULT_DESCRIPTION = "";
	private static final int DEFAULT_DISLIKES_TO_VIDEO = 0;
	private static final int DEFAULT_LIKES_TO_VIDEO = 0;
	private static final int DEFAULT_VIEWS_TO_VIDEO = 0;

	private final String url;
	private final Channel channel;
	private String title;
	private String description;
	private LocalDateTime uploadDate;
	private Set<String> tags;
	private List<Comment> comments;
	private int countOfLikes;
	private int countOfDislikes;
	private int views;

	public Video(String url, Channel channel, String title) throws IllegalURLException, IllegalChannelArgumentException, IllegalVideoTitleException {
		if (url != null){
			this.url = url;
		}else{
			throw new IllegalURLException();
		}
		
		if (channel != null){
			this.channel = channel;
		}else{
			throw new IllegalChannelArgumentException();
		}
		
		if (title != null){
			this.title = title;
		}else{
			throw new IllegalVideoTitleException();
		}
		
		this.description = DEFAULT_DESCRIPTION;
		this.uploadDate = LocalDateTime.now();
		this.tags = new TreeSet<String>();
		this.comments = new ArrayList<Comment>();

		this.countOfLikes = DEFAULT_LIKES_TO_VIDEO;
		this.countOfDislikes = DEFAULT_DISLIKES_TO_VIDEO;
		this.views = DEFAULT_VIEWS_TO_VIDEO;
	}

	public Video(String url, Channel channel, String title, String description) throws IllegalURLException, IllegalChannelArgumentException, IllegalVideoTitleException, IllegalVideoDescriptionException {
		this(url, channel, title);

		if (description != null){
			this.description = description;
		}else{
			throw new IllegalVideoDescriptionException();
		}
	}

	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

}
