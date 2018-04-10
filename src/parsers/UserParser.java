package parsers;

import java.util.Map;

import dataclasses.User;

public class UserParser {
	private static UserParser userParser;
  
	private UserParser() {
	}

	public static UserParser getInstance() {
		if (userParser == null) {
		  userParser = new UserParser();
		}
		return userParser;
	}

	public User parse(Map<String, String> argsMap) {
		final String userName = argsMap.get("username");
		//System.out.println(userName);
		final String password = argsMap.get("password");
		final String email = argsMap.get("email");
		
		return new User(userName, password, email);
	}
}
