package parsers;

import controllers.UserController;
import dataclasses.User;
import exceptions.*;

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

	public User  register(String username, String password, String email)
			throws IllegalNameException, IllegalEmailException, 
				IllegalPasswordException, IllegalUserArgumentException {
		  User user = new User(username, password, email);
		  return user;
	}
	
   public boolean loginCheck(String username, String password) throws InvalidDataException {
		if(username==null||password==null){
			return  false;
		}
		return true;
	}
}
