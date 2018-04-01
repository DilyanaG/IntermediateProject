package controllers;

import java.sql.SQLException;

import dataclasses.User;
<<<<<<< HEAD
import exceptions.IllegalInputException;
import menus.DefaultMenu;
import menus.HomeMenu;
import menus.Menu;
import menus.SettingsMenu;
=======
import exceptions.IllegalEmailException;
import exceptions.IllegalInputException;
import exceptions.IllegalNameException;
import exceptions.IllegalPasswordException;
import exceptions.IllegalUserArgumentException;
import exceptions.InvalidDataException;
import exceptions.UserNotFoundException;
>>>>>>> 51c885e98ce8098db7b4c43aa448418131f80173
import parsers.UserParser;
import services.UserServices;

public class UserController {

	private static UserController userController;

	private UserServices userService;
	private UserParser userParser;

	private ChannelController channelController;

	private UserController() {

	}

	public static UserController getInstance() {
		if (userController == null) {
			userController = new UserController();
		}
		return userController;
	}
	
	//TODO if logged in successful -> HomeMenu, else -> Default Menu
	public Menu login(String username, String password) {
		addFields(); //TODO what is this?
		try {
			userService.login(username, password);
			//TODO channelController.sa; - what is this?
			return new HomeMenu();
		} catch (IllegalInputException e) {
			System.out.println(e.getMessage()); //TODO This should be handles by the UI
			return new DefaultMenu();
		}
	}

	public Menu register(String username, String password, String email) {
		addFields(); //TODO what is this?
		try {
			userService.register(username, password, email);
		} catch (IllegalInputException e) {
			System.out.println(e.getMessage()); //TODO This should be handles by the UI
		}finally{
			return new DefaultMenu();
		}
	}

	public Menu logout(String username) {
		userService.logout(username);
		return new DefaultMenu();
	}

	public Menu changePassword(String newPassword) {
		userService.changePassword(newPassword);
		return new SettingsMenu();
	}
	
	public Menu deleteAccount(String password) {
		userService.deleteAccount(password);
		return new DefaultMenu();
	}
	
	
	/////////////////
	
	//TODO use this method because throw stackoverflow error
	private void addFields() {
		this.userService = UserServices.getInstance();
		this.userParser = UserParser.getInstance();
		// this. onlineUser =OnlineUserInterface.getInstance();
		// this.offlineUser=OfflineUserInterface.getInstance();
		this.channelController = ChannelController.getInstance();
	}
}
