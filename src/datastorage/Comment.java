package datastorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.IllegalChannelArgumentException;
import exceptions.IllegalCommentContentException;

public class Comment {
	private static final int DEFAULT_DISLIKES_TO_COMMENTS = 0;
	private static final int DEFAULT_LIKES_TO_COMMENTS = 0;
	
	private final Channel channel;
	private Date publicationDate;
	private long likes;
	private long dislikes;
	private List<Comment> responses;
	private String content;
	
	public Comment(Channel channel, String content) throws IllegalChannelArgumentException, IllegalCommentContentException{
		if(channel != null){
			this.channel = channel;
		}else{
			throw new IllegalChannelArgumentException();
		}
		
		if(content != null && content.trim().length() > 0){
			this.content = content;
		}else{
			throw new IllegalCommentContentException();
		}
		
		this.publicationDate = new Date();
		this.likes = DEFAULT_LIKES_TO_COMMENTS;
		this.dislikes = DEFAULT_DISLIKES_TO_COMMENTS;
		
		this.responses = new ArrayList<Comment>();
	}
}
