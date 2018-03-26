package controllers;

import java.util.List;

import dataclasses.Comment;
import dataclasses.Video;
import services.CommentService;

public class CommentController {
	private static CommentController commentController;
	private final CommentService commentService = CommentService.getInstance();

	private CommentController() {
	}

	public static CommentController getInstance() {
		if (commentController == null) {
			commentController = new CommentController();
		}
		return commentController;
	}

	public void addComment(Comment comment, Video video) {
		commentService.addComment(comment, video);
	}

	public void removeComment(Comment comment, Video video) {
		commentService.removeComment(comment, video);
	}

	public void addLikeDislikeToComment(boolean isLike, Comment comment) {
		commentService.addLikeDislikeToComment(isLike, comment);
	}

	public void removeLikeDislikeFromComment(boolean isLike, Comment comment) {
		commentService.removeLikeDislikeFromComment(isLike, comment);
	}

	public List<Comment> showVideoComments(Video video) {
		return commentService.showVideoComments(video);
	}

	public void addResponseToComment(Comment response, Comment comment) {
		commentService.addResponseToComment(response, comment);
	}

	public void removeResponseFromComment(Comment response, Comment comment) {
		commentService.removeResponseFromComment(response, comment);
	}
}
