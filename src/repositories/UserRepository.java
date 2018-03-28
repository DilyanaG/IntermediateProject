package repositories;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import controllers.UserController;
import dataclasses.User;
import exceptions.IllegalNameException;
import exceptions.InvalidDataException;
import exceptions.UserNotFoundException;

public class UserRepository {
	
	private static final String USER_JSON_FILE = ".//JSONfiles//users.json";
	private static UserRepository userRepository;
	
	private  Map<String, User> users;
	
	private UserRepository() {
		users = new TreeMap<String, User>();
		users = getUsersFromJSONFILE();
	}
	
	public  boolean checkForUser(String username) {
        if(this.users==null){
        	this.users=new TreeMap<>();
        }
		return users.containsKey(username);
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

	public static UserRepository getInstance() {
	if(userRepository==null){
			userRepository=new UserRepository();
		}
		return userRepository;
	}

	public void addNewUser(User user) throws InvalidDataException {
         if(user==null) 
        	 throw new InvalidDataException();
		users.put(user.getUserName(), user);
		//updete to json files with  users
		this.writeUsersToJSONFile(users);
		
	}

	public User getUserByUserName(String username)
			throws UserNotFoundException, IllegalNameException {
		if(username==null)
		  throw new IllegalNameException();
		if(!users.containsKey(username)){
			throw new UserNotFoundException("USER WITH THIS USERNAME DOES NOT EXIST!");
		}
		return users.get(username);
	}
}
