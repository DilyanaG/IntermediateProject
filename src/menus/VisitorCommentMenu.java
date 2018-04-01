package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.CommentController;
import controllers.VideoController;
import enums.SortSearchBy;
import exceptions.IllegalInputException;
import parsers.GenericParser;

public class VisitorCommentMenu extends Menu{
	private ChannelController channelController = ChannelController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	private CommentController commentController = CommentController.getInstance();
	
	private GenericParser genericParser = GenericParser.getInstance();

	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
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

		
		switch (command) {
		case "search":
			final SortSearchBy sortBy = SortSearchBy.resolve(argsMap);
			final String key = "tags";
			final String tags = genericParser.parseToString(argsMap, key);
			Menu searchMenu = videoController.search(tags, sortBy); 
			return searchMenu;
		case "openauthorschannel":
			Menu visitorChannelMenu = commentController.openAuthorsChannel(commentid);
			return visitorChannelMenu;
		case "homepage":
			Menu defaultMenu = channelController.homepage();
			return defaultMenu;
		case "exit":
			Menu exitMenu = null;
			return exitMenu;
		
		default:
			//TODO throw new IllegalInputException();
			break;
		}

		return null;
	}
}
