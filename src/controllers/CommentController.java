package controllers;

import java.util.List;

import dataclasses.Comment;
import dataclasses.Video;
import exceptions.IllegalInputException;
import menus.ChannelMenu;
import menus.CommentMenu;
import menus.Menu;
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

	public Menu addComment(String commentContent) throws IllegalInputException    {
		commentService.addComment(commentContent);
		return new CommentMenu();
	}

	public Menu removeComment(int commentid, String title) {
		commentService.removeComment(commentid, title);
		return new CommentMenu();
	}
	
	public Menu changeComment(int commentid, String commentContent) {
		commentService.changeComment(commentid, commentContent);
		return new CommentMenu();
	}

	public Menu addLikeDislikeToComment(boolean isLike, Integer commentid) {
		commentService.addLikeDislikeToComment(isLike, commentid);
		return new CommentMenu();
	}

	public Menu removeLikeDislikeFromComment(boolean isLike, Integer commentid) {
		commentService.removeLikeDislikeFromComment(isLike, commentid);
		return new CommentMenu();
	}

	public Menu addResponseToComment(Integer commentid, String string) {
		commentService.addResponseToComment(commentid, string);
		return new CommentMenu();	
	}

	public Menu removeResponseFromComment(int commentid, String title) {
		commentService.removeResponseFromComment(commentid, title);
		return new CommentMenu();
	}
	
	public Menu changeResponseToComment(int commentid, String commentContent) {
		commentService.changeResponseToComment(commentid, commentContent);
		return new CommentMenu();
	}
	
	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu openAuthorsChannel(int commentid) {
		commentService.openAuthorsChannel(commentid);
		return new ChannelMenu(null);
	}

	public List<Comment> getCommentsForVideo(Video video) throws IllegalInputException {
		List<Comment> comments = commentService.getCommentForVideo(video);
		return comments;
	}
}
