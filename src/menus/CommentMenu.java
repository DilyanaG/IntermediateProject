package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.CommentController;
import controllers.VideoController;
import dataclasses.Comment;
import enums.SortSearchBy;
import exceptions.IllegalInputException;
import parsers.CommentParser;
import parsers.GenericParser;

public class CommentMenu extends Menu {
	private ChannelController channelController = ChannelController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	private CommentController commentController = CommentController.getInstance();
	
	private GenericParser genericParser = GenericParser.getInstance();
	private CommentParser commentParser = CommentParser.getInstance();

	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">AddComment -content=comment_content_here\n");
		builder.append(">RemoveComment -commentid=comment_id_here\n"); // remove comment you did not give? a better input
		builder.append(">ChangeComment -commentid=comment_id_here -content=new_comment_content_here\n"); // change comment you did not give? a better input
		builder.append(">AddResponseToComment -content=comment_content_here\n");
		builder.append(">RemoveResponseFromComment -commentid=comment_id_here\n"); 
		builder.append(">ChangeResponseToComment -commentid=comment_id_here -content=new_comment_content_here\n"); 
		builder.append(">LikeDislikeComment -like=true_or_false\n");
		builder.append(">RemoveLikeDislike -like=true_or_false\n");
		builder.append(">OpenAuthorsChannel -commentid=comment_id_here\n");
		builder.append(">Homepage\n");
		return builder.toString();
	}

	@Override
	public Menu process(String input) throws IllegalInputException {
		final String command = input.split(" ")[0].toLowerCase();// read first word from input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args);

		final String keyCommentID = "commentid";
		final Integer commentid = genericParser.parseToInteger(argsMap, keyCommentID);

		final String keyLike = "like";
		final Boolean isLike = genericParser.parseToBoolean(argsMap, keyLike);
		
		final Comment comment = commentParser.parse(argsMap);
		final String title = null; // how to keep which video we are commenting?

		Menu commentMenu = null;
		
		switch (command) {
		case "search":
			final SortSearchBy sortBy = SortSearchBy.resolve(argsMap);
			final String keyTag = "tags";
			final String tags = genericParser.parseToString(argsMap, keyTag);
			Menu searchMenu = videoController.search(tags, sortBy); 
			return searchMenu;
		case "addcomment":
			commentMenu = commentController.addComment(comment.getContent());
			return commentMenu;
		case "removecomment":
			commentMenu = commentController.removeComment(commentid, title);
			return commentMenu;
		case "changecomment":
			commentMenu = commentController.changeComment(comment.getCommentId(), comment.getContent());
			return commentMenu;
		case "addresponsetocomment":
			commentMenu = commentController.addResponseToComment(commentid, comment.getContent());
			return commentMenu;
		case "removeresponsefromcomment":
			commentMenu = commentController.removeResponseFromComment(commentid, title);
			return commentMenu;
		case "changeresponsetocomment":
			commentMenu = commentController.changeResponseToComment(comment.getCommentId(), comment.getContent());
			return commentMenu;
		case "likedislikecomment":
			commentMenu = commentController.addLikeDislikeToComment(isLike, commentid);
			return commentMenu;
		case "removelikedislike":
			commentMenu = commentController.removeLikeDislikeFromComment(isLike, commentid);
			return commentMenu;
		case "openauthorschannel":
			Menu channelMenu = commentController.openAuthorsChannel(commentid);
			return channelMenu;
		case "homepage":
			Menu homeMenu = channelController.homepage();
			return homeMenu;
		case "exit":
			Menu exitMenu = null;
			return exitMenu;
		
		default:
			throw new IllegalInputException("INVALID INPUT !");
			
		}

	}
}
