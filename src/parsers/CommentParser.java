package parsers;

import java.util.Map;

import dataclasses.Comment;

public class CommentParser {
private static CommentParser commentParser;

	
	private CommentParser(){
	}

	public static CommentParser getInstance() {
		if (commentParser == null) {
			commentParser = new CommentParser();
		}
		return commentParser;
	}
	
	public Comment parse(Map<String, String> argsMap){
		final String content = argsMap.get("content");
		
		return new Comment(null, content);
	}
}
