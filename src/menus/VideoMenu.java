package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.CommentController;
import controllers.VideoController;
import dataclasses.Comment;
import dataclasses.Playlist;
import enums.SortSearchBy;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;
import parsers.CommentParser;
import parsers.GenericParser;
import parsers.PlaylistParser;

public class VideoMenu extends Menu{
	private ChannelController channelController = ChannelController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	
	private PlaylistParser playlistParser = PlaylistParser.getInstance();
	private GenericParser genericParser = GenericParser.getInstance();

	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
		builder.append(">LikeDislikeVideo -like=true_or_false\n"); // more than one like per person?
		builder.append(">RemoveLikeDislike -like=true_or_false\n"); // remove like you did not give?
		builder.append(">AddToPlaylist -playlistname=playlistname_here\n");
		builder.append(">OpenAuthorsChannel\n");
		builder.append(">AddComment -content=comment_content_here\n");
	    builder.append(">ShowComments\n");
		builder.append(">Homepage\n");
		return builder.toString();
	}

	@Override
	public Menu process(String input) throws IllegalInputException, DataBaseException {
		final String command = input.split(" ")[0].toLowerCase();// read first word from input
		final String args = input.substring(command.length()); // remove command

		final Map<String, String> argsMap = parseToMap(args);

		final String key = "like";
		final Boolean isLike = genericParser.parseToBoolean(argsMap, key);
		final String title = null; //TODO think of how to get the title
		
		Menu videoMenu = null;

		switch (command) {
		case "search":
			final SortSearchBy sortBy = SortSearchBy.resolve(argsMap);
			final String keyTag = "tags";
			final String tags = genericParser.parseToString(argsMap, keyTag);
			Menu searchMenu = videoController.search(tags, sortBy); 
			return searchMenu;
		case "likedislikevideo":
			videoMenu = videoController.addLikeDislikeToVideo(isLike, title);
			return videoMenu;
		case "removelikedislike":
			videoMenu = videoController.removeLikeDislikeFromVideo(isLike, title);
			return videoMenu;
		case "openauthorschannel":
			Menu channelMenu = videoController.openAuthorsChannel();
			return channelMenu;
		case "addtoplaylist":
			final Playlist playlist = playlistParser.parse(argsMap);
			videoMenu = videoController.addVideoToPlaylist(playlist.getPlaylistName());
			return videoMenu;
		case "addcomment":
			final Comment comment = CommentParser.getInstance().parse(argsMap);
			Menu commentMenu = CommentController.getInstance().addComment(comment.getContent());
			return commentMenu;
		case "showcomments":
			Menu commentsMenu = videoController.showVideoComments(title);
			return commentsMenu;
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
