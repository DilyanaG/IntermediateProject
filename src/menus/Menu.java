package menus;

import java.util.Map;

import exceptions.IllegalInputException;
import parsers.AbstractParser;

public abstract class Menu {
	// protected User user; // The logged in user

	public String presnet() {
		final StringBuilder builder = new StringBuilder();
		builder.append("_____________YOUTUBE_____________\n");
		builder.append(">Search -tags=tags_here -sortsearchby=DATE_VIEWS_or_LIKES\n");
		final String special = specialPresent();
		builder.append(special);
		builder.append(">Exit \n");
		return builder.toString();
	}

	protected abstract String specialPresent();

	public abstract Menu process(String input) throws IllegalInputException;

	// Input without command
	protected Map<String, String> parseToMap(String input) throws IllegalInputException { 
		return AbstractParser.splitParameters(input);
	}

}