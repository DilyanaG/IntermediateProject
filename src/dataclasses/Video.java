package dataclasses;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Video {
	private static final int DEFAULT_DISLIKES_TO_VIDEO = 0;
	private static final int DEFAULT_LIKES_TO_VIDEO = 0;
	private static final int DEFAULT_VIEWS_TO_VIDEO = 0;

	
	private final String url;
	private final Channel channel;
	private String title;
	private String description;
	private LocalDateTime date;
	private Set<String> tags;
	private List<Comment> comments;
	private int countOfLikes;
	private int countOfDislikes;
	private int views;

	// TODO validate
	public Video(String url, Channel channel, String title) {
		this.url = url;
		this.channel = channel;
		this.setTitle(title);
		comments = new ArrayList<Comment>();
		tags = new TreeSet<String>((t1, t2) -> t1.compareTo(t2));
		this.date = LocalDateTime.now();
		this.countOfLikes = DEFAULT_LIKES_TO_VIDEO;
		this.countOfDislikes = DEFAULT_DISLIKES_TO_VIDEO;
		this.views = DEFAULT_VIEWS_TO_VIDEO;

	}
	
	public Video(String url, Channel channel, String title, String description) {
		this(url, channel, title);
		
		this.description = (description == null) ? "" : description;
	}

	public void addDiscription(String description) {
		this.description = (description == null) ? "" : description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = (title == null) ? "" : title;
	}

}
