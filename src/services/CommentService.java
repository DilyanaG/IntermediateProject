package services;

import java.util.List;

import dataclasses.Comment;
import dataclasses.Video;

public class CommentService {
	private static CommentService commentService;

	private CommentService() {
	}

	public static CommentService getInstance() {
		if (commentService == null) {
			commentService = new CommentService();
		}
		return commentService;
	}

	public void addComment(Comment comment, Video video) {
		// TODO Auto-generated method stub
		
	}

	public void removeComment(Comment comment, Video video) {
		// TODO Auto-generated method stub
		
	}

	public void addLikeDislikeToComment(boolean isLike, Comment comment) {
		// TODO Auto-generated method stub
		
	}

	public void removeLikeDislikeFromComment(boolean isLike, Comment comment) {
		// TODO Auto-generated method stub
		
	}

	public List<Comment> showVideoComments(Video video) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public void addResponseToComment(Comment response, Comment comment) {
		// TODO Auto-generated method stub
		
	}

	public void removeResponseFromComment(Comment response, Comment comment) {
		// TODO Auto-generated method stub
		
	}
}
