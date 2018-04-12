package services;

import java.sql.SQLException;
import java.util.List;

import controllers.ChannelController;
import controllers.VideoController;
import dataclasses.Channel;
import dataclasses.Comment;
import dataclasses.Video;
import exceptions.DataBaseException;
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

	public void addComment(String commentContent) throws IllegalInputException, DataBaseException {
		Channel channel = ChannelController.getInstance().getLoginChannel();
		Video video = VideoController.getInstance().getOpenedVideo();
		try {
			commentDAO.addNewCommentForVideo(video,channel, commentContent);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage());
		}
		
	}

	public List<Comment> getCommentForVideo(Video video) throws IllegalInputException, DataBaseException {
		 List<Comment> comments=null;
		try {
			comments = commentDAO.getCommentsForVideo(video);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage());
		} 
		return comments;
	}
     public Channel openAuthorsChannel(int commentid) throws DataBaseException, IllegalInputException {
    	 Comment comment;
		try {
			comment = commentDAO.getCommentById(commentid);
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		} 
    	 
		return comment.getChannel();
		
	}



	public void removeComment(int commentid) throws IllegalInputException, DataBaseException {
		Video video = VideoController.getInstance().getOpenedVideo();
		Channel channel= ChannelController.getInstance().getLoginChannel();
		Comment comment;
		try {
			comment = commentDAO.getCommentById(commentid);
		
//		
//		if(video.getChannel().equals(channel)||
//				comment.getChannel().equals(channel)){
			commentDAO.deleteComment(comment);
//		}else{
//			throw new IllegalInputException("You have no right to change this comment !");
//		}
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
		
	}

	public void changeComment(int commentid, String commentContent) throws IllegalInputException, DataBaseException {
		Video video = VideoController.getInstance().getOpenedVideo();
		Channel channel= ChannelController.getInstance().getLoginChannel();
		Comment comment;
		try {
			comment = commentDAO.getCommentById(commentid);
		
		
//		if(video.getChannel().equals(ChannelController.getInstance().getLoginChannel())||
//				comment.getChannel().equals(ChannelController.getInstance().getLoginChannel())){
			commentDAO.updateToCommentContent(comment,commentContent);
//		}else{
//			throw new IllegalInputException("You have no right to change this comment !");
//		}
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
		
	}
	public void addLikeDislikeToComment(boolean isLike, Integer commentid) {
		// TODO Auto-generated method stub
		
	}

	

	public void removeResponseFromComment(int commentid, String title) {
		// TODO Auto-generated method stub
		
	}

	public void addResponseToComment(Integer commentid, String string) {
		// TODO Auto-generated method stub
		
	}

	

	public void removeLikeDislikeFromComment(boolean isLike, Integer commentid) {
		// TODO Auto-generated method stub
		
	}

	public void changeResponseToComment(int commentid, String commentContent) {
		// TODO Auto-generated method stub
		
	}

	
}
