package controllers;

import java.util.List;

import dataclasses.Channel;
import dataclasses.Comment;
import dataclasses.Video;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;
import menus.ChannelMenu;
import menus.CommentMenu;
import menus.HomeMenu;
import menus.Menu;
import menus.VisitorChannelMenu;
import services.CommentService;
import services.VideoServices;

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

	public Menu addComment(String commentContent) throws IllegalInputException, DataBaseException    {
		commentService.addComment(commentContent);
		System.out.println("COMMENT ADDED!");
		return new CommentMenu();
	}

	public Menu removeComment(int commentid) throws IllegalInputException, DataBaseException {
		
		commentService.removeComment(commentid);
		System.out.println("Comment Deleted!");
		return new CommentMenu();
	}
	
	public Menu changeComment(int commentid, String commentContent) throws IllegalInputException, DataBaseException {
		//System.out.println(commentid+"ccS");
		commentService.changeComment(commentid, commentContent);
		System.out.println("Comment content changed!");
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
	public Menu openAuthorsChannel(int commentid) throws IllegalInputException, DataBaseException {
		Channel channel = commentService.openAuthorsChannel(commentid);
		if(ChannelController.getInstance().isLogin()){
			
			if(VideoServices.getInstance().getCurrentVideo().getChannel().getUser().getUserName().equals(
					channel.getUser().getUserName())){
				return new HomeMenu();
			}
		   return new ChannelMenu(channel.getUser().getUserName());
		}
		return new VisitorChannelMenu(channel.getUser().getUserName());
	}
	

	public List<Comment> getCommentsForVideo(Video video) throws IllegalInputException, DataBaseException {
		List<Comment> comments = commentService.getCommentForVideo(video);
		return comments;
	}
}
