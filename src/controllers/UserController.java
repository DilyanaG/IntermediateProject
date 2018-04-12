package controllers;

import dataclasses.User;
import exceptions.DataBaseException;
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

	// TODO if logged in successful -> HomeMenu, else -> Default Menu
	public Menu login(String username, String password) throws IllegalInputException, DataBaseException {
		if (userService.login(username, password)) {
			return new HomeMenu();
		}
		return new DefaultMenu();
	}

	public Menu register(User user) throws IllegalInputException, DataBaseException {
		if (!userService.register(user)) {

			return new DefaultMenu();
		}
		System.out.println("Successful registration!");
		return new HomeMenu();

	}

	public Menu logout(String username) {
		userService.logout(username);
		return new DefaultMenu();
	}

	public Menu changePassword(String newPassword) throws IllegalInputException, DataBaseException {
		userService.changePassword(newPassword);
		return new SettingsMenu();
	}

	public Menu deleteAccount(String password) throws IllegalInputException, DataBaseException {
		userService.deleteAccount(password);
		return new DefaultMenu();
	}

}
