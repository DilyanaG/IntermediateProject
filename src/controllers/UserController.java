package controllers;

import java.util.*;
import datastorage.*;
import demo.Menu;
import exceptions.*;

public class UserController {
	private static Map<String,User> users;
	static {
		users = new TreeMap<String,User>();
	}

	public static void register() {
		String username;
		String password;
		String email;
       Scanner sc =new Scanner(System.in);
		System.out.println("........REGISTER.......");
		System.out.println("Enter username:");
		try{
		username = sc.next().trim().toLowerCase();
		if (checkForUser(username)) {
			System.out.println("USER WITH USERNAME '"+username +"' ALREADY EXIST!!");
			System.out.println("PLEASE ENTER OTHER USERNAME!");
			throw new IllegalNameException();
		}
		
		System.out.println("Enter password");
		password=sc.next().trim().toLowerCase();
		System.out.println("Enter email");
		email=sc.next().trim(); 
		User user= new User(username, password, email);
		users.put(username, user);
	    Menu.loginMenu();
		
		
		}catch(IllegalNameException |IllegalEmailException e){
			register();
		}

	}

	public static void login(String username) throws UserNotFoundException {
		if (!checkForUser(username)) {
			throw new UserNotFoundException();
		}
          //TODO ...
	}

	public static void logout(User user) {

	}

	public static void deleteUser(User user) {

	}
	public static void printUsers(){
		for(Map.Entry<String, User> entry:users.entrySet()){
			System.out.println(entry.getKey());
		}
	}
	private static boolean checkForUser(String username) {
		// TODO check all users
		if(username.equals("ahmed"))return true;
		return false;
	}
}
