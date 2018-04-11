package menus;

import java.util.Map;

import controllers.ChannelController;
import controllers.VideoController;
import enums.SortSearchBy;
import exceptions.IllegalInputException;
import parsers.GenericParser;

public class VisitorVideoMenu extends Menu{
	private ChannelController channelController = ChannelController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	
	private GenericParser genericParser = GenericParser.getInstance();
 
	@Override
	protected String specialPresent() {
		final StringBuilder builder = new StringBuilder();
	
		builder.append(">OpenAuthorsChannel\n");
		builder.append(">ShowComments\n");
		builder.append(">Homepage\n");
		return builder.toString();
	}

	@Override
	public Menu process(String input) throws IllegalInputException {
		final String command = input.split(" ")[0].toLowerCase();// read first word from input
		 final String args = input.substring(command.length()); // remove command

		 final Map<String, String> argsMap = parseToMap(args);
		  final String title = null; //TODO think of a way to get the title

		switch (command) {
		case "search":
			final SortSearchBy sortBy = SortSearchBy.resolve(argsMap);
			final String keyTag = "tags";
			final String tags = genericParser.parseToString(argsMap, keyTag);
			Menu searchMenu = videoController.search(tags, sortBy); 
			return searchMenu;
		case "openauthorschannel":
			Menu visitorChannelMenu = videoController.openAuthorsChannel();
			return visitorChannelMenu;
		case "showcomments":
			Menu visitorCommentMenu = videoController.showVideoComments(title);
			return visitorCommentMenu;
		case "homepage":
			Menu defaultMenu = channelController.homepage();
			return defaultMenu;
		case "exit":
			Menu exitMenu = null;
			return exitMenu;
		
		default:
	         throw new IllegalInputException("INVALID INPUT !");
			
		}

	}
}
