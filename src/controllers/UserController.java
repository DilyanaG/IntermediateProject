package controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import dataclasses.User;
import demo.Menu;
import exceptions.IllegalEmailException;
import exceptions.IllegalNameException;
import exceptions.IllegalUserArgumentException;
import exceptions.UserNotFoundException;

public class UserController {
	private static UserController userController;
	private static final String USER_JSON_FILE = ".//JSONfiles//users.json";
	private static Map<String, User> users;
	static {
		users = new TreeMap<String, User>();
		users = getUsersFromJSONFILE();
	}
	private ChannelController channelController;
	private UserController() {


	}

	public static UserController getUserControllerInstance() {
		if (userController == null) {
			userController = new UserController();

		}
		return userController;

	}

	public void register() throws IllegalUserArgumentException {
		String username;
		String password;
		String email;
		Scanner sc = new Scanner(System.in);
		System.out.println("........REGISTER.......");
		System.out.println("Enter username:");
		try {
			username = sc.next().trim().toLowerCase();
			if (checkForUser(username)) {
				System.out.println("USER WITH USERNAME '" + username + "' ALREADY EXIST!!");
				System.out.println("PLEASE ENTER OTHER USERNAME!");
				boolean continueReg = false;
				System.out.println("DO YOU WANT CONTINUE REGISTRATION? [Enter:Yes/No]");
				while (!continueReg) {

					String ch = sc.next().trim().toLowerCase();
					switch (ch) {
					case "no":
						Menu.getInstance().mainMenu();
						break;
					case "yes":
						continueReg = true;
						break;
					default: {
						System.out.println("INCORRECT INPUT!ENTER AGAIN!");
						continue;

					}
					}
				}

				throw new IllegalNameException();
			}
			System.out.println("Enter password");
			password = sc.next().trim().toLowerCase();
			System.out.println("Enter email");
			email = sc.next().trim().toLowerCase();
			User user = new User(username, password, email);
			users.put(username, user);
			user.setChannel(channelController.getChannel(user));
			writeUsersToJSONFile(users);
			System.out.println("Successfully registered!!");
			Menu.getInstance().loginMenu();

		} catch (IllegalNameException | IllegalEmailException  e) {
			// e.printStackTrace();
			register();
		}

	}

	public void login(String username) throws Exception{
		if (!checkForUser(username)) {
			throw new UserNotFoundException();
		}
		System.out.println("check for login method in usercontrolller");
		User user=users.get(username);
	    user.setChannel(channelController.getChannel(user));
		user.setOnline(true);
		
		// TODO ...
	}

	public static void logout(User user) {

	}

	public static void deleteUser(User user) {

	}

	
	private boolean checkForUser(String username) {
		// TODO check all users
		if (UserController.users.containsKey(username)){
			return true;
		}
		return false;
	}

	private void writeUsersToJSONFile(Map<String, User> users) {
		Gson gson = new Gson();
		String json = gson.toJson(users);
		// System.out.println(json);
		try (FileWriter writer = new FileWriter(USER_JSON_FILE)) {

			gson.toJson(users, writer);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Map<String, User> getUsersFromJSONFILE() {
		Gson gson = new Gson();
		Map<String, User> map = null;
		try (Reader reader = new FileReader(USER_JSON_FILE)) {
			JsonElement json = gson.fromJson(reader, JsonElement.class);
			String jsonInString = gson.toJson(json);

			// System.out.println(jsonInString);
			map = gson.fromJson(jsonInString, new TypeToken<Map<String, User>>() {
			}.getType());

		} catch (IOException e) {
			e.printStackTrace();
		}
		if(map==null){
			map=new TreeMap<>();
		}
		return map;

	}
	public void printUsers() {
		for (Entry<String, User> entry : users.entrySet()) {
			System.out.println((entry).getKey());
		}
	}

}
