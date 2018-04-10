package services;

import java.sql.SQLException;
import java.util.List;

import controllers.ChannelController;
import controllers.VideoController;
import dataclasses.Channel;
import dataclasses.Comment;
import dataclasses.Video;
import exceptions.IllegalInputException;
import repositories.CommentDAO;

public class CommentService {
	private static CommentService commentService;
    
	
	private CommentDAO commentDAO = CommentDAO.getInstance();
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

	public void addComment(String commentContent) throws IllegalInputException {
		Channel channel = ChannelController.getInstance().getLoginChannel();
		Video video = VideoController.getInstance().getOpenedVideo();
		try {
			commentDAO.addNewCommentForVideo(video,channel, commentContent);
		} catch (SQLException e) {
			throw new IllegalInputException(e.getMessage()+"DATABASE ERROR!");
		}
		
	}

	public void removeComment(int commentid, String title) {
		// TODO Auto-generated method stub
		
	}

	public void addLikeDislikeToComment(boolean isLike, Integer commentid) {
		// TODO Auto-generated method stub
		
	}

	public void openAuthorsChannel(int commentid) {
		// TODO Auto-generated method stub
		
	}

	public void removeResponseFromComment(int commentid, String title) {
		// TODO Auto-generated method stub
		
	}

	public void addResponseToComment(Integer commentid, String string) {
		// TODO Auto-generated method stub
		
	}

	public void changeComment(int commentid, String commentContent) {
		// TODO Auto-generated method stub
		
	}

	public void removeLikeDislikeFromComment(boolean isLike, Integer commentid) {
		// TODO Auto-generated method stub
		
	}

	public void changeResponseToComment(int commentid, String commentContent) {
		// TODO Auto-generated method stub
		
	}

	public List<Comment> getCommentForVideo(Video video) throws IllegalInputException {
		 List<Comment> comments=null;
		try {
			comments = commentDAO.getCommentsForVideo(video);
		} catch (SQLException e) {
			throw new IllegalInputException("DATABASE ERROR!\n"+e.getMessage());
		} 
		return comments;
	}
}
