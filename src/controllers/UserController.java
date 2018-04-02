package controllers;

import dataclasses.User;
import exceptions.IllegalInputException;
import menus.*;
import services.UserServices;

public class UserController {

	private static UserController userController;

	private UserServices userService;


	private UserController() {
		this.userService = UserServices.getInstance();

	}

	public static UserController getInstance() {
		if (userController == null) {
			userController = new UserController();
		}
		return userController;
	}
	
	//TODO if logged in successful -> HomeMenu, else -> Default Menu
	public Menu login(String username, String password)  {

		try {
			userService.login(username, password);
			return new HomeMenu();
		} catch (IllegalInputException e) {
			System.out.println(e.getMessage()); //TODO This should be handles by the UI
			return new DefaultMenu();
		}
	}
     //String username, String password, String email
	public Menu register(User user) {

		try {
			if(!userService.register(user)){
				throw new IllegalInputException("DATABASE PROBLEM!");
			}
			return new DefaultMenu();
			
		} catch (IllegalInputException e) {
			System.out.println(e.getMessage()); //TODO This should be handles by the UI
			return new DefaultMenu();
		}
			
		
	}

	public Menu logout(String username) {
		userService.logout(username);
		return new DefaultMenu();
	}

	public Menu changePassword(String newPassword) throws IllegalInputException {
		userService.changePassword(newPassword);
		return new SettingsMenu();
	}
	
	public Menu deleteAccount(String password) throws IllegalInputException {
		userService.deleteAccount(password);
		return new DefaultMenu();
	}
	

}
