package parsers;

import controllers.UserController;
import dataclasses.User;
import exceptions.*;

public class UserParser {
	private static UserParser userParser;
    private UserController  userController=UserController.getInstance();
	
	private UserParser() {
	}

	public static UserParser getInstance() {
		if (userParser == null) {
			userParser = new UserParser();
		}
		return userParser;
	}

	public void register(String username, String password, String email) 
			throws IllegalNameException, IllegalEmailException, 
						IllegalPasswordException, IllegalUserArgumentException {
		  User user = new User(username, password, email);
		  userController.register(user);
		  }

	public void login(String username, String password) throws InvalidDataException {
		if(username==null||password==null){
			throw new InvalidDataException();
		}
		this.userController.login(username,password);
		
	}
}
