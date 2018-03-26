package parsers;

import dataclasses.Channel;
import dataclasses.Comment;

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
		Comment comment = new Comment(channel, content);
		return comment;
	}
}
