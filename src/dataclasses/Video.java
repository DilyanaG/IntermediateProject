package dataclasses;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.*;

import exceptions.IllegalURLException;


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

	// TODO validate
	public Video(String url, Channel channel, String title) throws IllegalURLException {
		if(url==null)throw new IllegalURLException();
		this.url =url;
	 	this.channel = channel;
		comments = new ArrayList<Comment>();
		tags = new TreeSet<String>((t1, t2) -> t1.compareTo(t2));
		this.uploadDate = LocalDateTime.now();
		this.countOfLikes = DEFAULT_LIKES_TO_VIDEO;
		this.countOfDislikes = DEFAULT_DISLIKES_TO_VIDEO;
		this.views = DEFAULT_VIEWS_TO_VIDEO;
		this.description=DEFAULT_DESCRIPTION;
	}
	
	public Video(String url, Channel channel, String title, String description) throws IllegalURLException  {
		this(url, channel, title);
		
		this.description = description==null?DEFAULT_DESCRIPTION:description;
	}
	public LocalDateTime getUploadDate() {
		return uploadDate;
	}
 
 


}
