package parsers;

import dataclasses.Channel;
import dataclasses.Comment;
import exceptions.IllegalChannelArgumentException;
import exceptions.IllegalCommentContentException;

public class CommentParser {
private static CommentParser commentParser;
private final ChannelParser channelParser = ChannelParser.getInstance();

	
	private CommentParser(){
	}

	public static CommentParser getInstance() {
		if (commentParser == null) {
			commentParser = new CommentParser();
		}
		return commentParser;
	}
	
	public Comment parse(String channelName, String content){
		Channel channel = channelParser.parse(channelName);
		Comment comment=comment = new Comment(channel, content);
       return comment;
	}
}
