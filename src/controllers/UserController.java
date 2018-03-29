package controllers;

import java.io.*;
import java.util.*;
import dataclasses.User;
import exceptions.*;
import parsers.*;
import services.*;
import ui.*;


public class UserController {

	private static UserController userController;

	private UserServices userService ;
	private UserParser userParser;
   
    private ChannelController channelController;

	private UserController() {
		
	}
  
	
	public boolean login(String username, String password) {
		addFields();
            try {
            	
				User user =this.userService.login(username,password);
				channelController.showChannel(user,true);
				return true;
			} catch (IllegalPasswordException | IllegalNameException 
					 | UserNotFoundException e) {
			
			   System.out.println(e.getMessage());	
				return false;
			}
	}


	public boolean register(String username, String password, String email) {
		 addFields();
			try {
				userService.register(userParser.register(username, password, email));
			    return true;
		           
               } catch (IllegalNameException | IllegalEmailException | IllegalPasswordException
					| IllegalUserArgumentException|InvalidDataException e) {
          	   return false;
			}
		 
		
	}

	public static void logout(User user) {
       
	}

	public static void deleteUser(User user) {

	}

	public static UserController getInstance() {
		if (userController == null) {
			userController = new UserController();
		}
		return userController;
	}
	//use this method because throw stackoverflow error
	  private void addFields(){
	    	this.userService = UserServices.getInstance();
	    	this. userParser = UserParser.getInstance();
//	        this. onlineUser =OnlineUserInterface.getInstance();
//	        this.offlineUser=OfflineUserInterface.getInstance();
	        this.channelController = ChannelController.getInstance();
	    }

}
