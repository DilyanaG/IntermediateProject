package repositories;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.sql.*;
import java.util.*;
import java.util.Set;
import dataclasses.User;
import exceptions.*;

public class UserRepository {
	// FOR DB
	private static final String SELECT_ALL_USERS = 
			"SELECT user_id, user_name, email, password FROM users;";
	private static final String SELECT_USER_BY_USERNAME =
			  "SELECT user_id, user_name, email, password FROM users WHERE user_name = ? ;";
	private static final String INSERT_INTO_USERS =
			"INSERT INTO users (user_name, password, email) VALUES (?, ?, ?);";
	//TODO function for this
	private static final String UPDATE_USER_PASSWORD =
			"UPDATE users SET user_password = ? WHERE user_name = ?;";
	

//    FOR JSON
//	private static final String USER_JSON_FILE = ".//JSONfiles//users.json";
//	private Map<String, User> users;
	private static UserRepository userRepository;
     
	//connection to DB 
	private Connection connection;
    
	private UserRepository() {
		connection = DBManager.getInstance().getConnection();
//		for JSON
//		users = new HashMap<String, User>();
//		users = getUsersFromJSONFILE();
	}
	
	public static UserRepository getInstance() {
		if (userRepository == null) {
			userRepository = new UserRepository();
		}
		return userRepository;
	}

	public Map<String, User> getAllUsers() throws SQLException {
        Map<String, User> users = new HashMap<String, User>();
		PreparedStatement userST = connection.prepareStatement(SELECT_ALL_USERS);
		ResultSet usersRS = userST.executeQuery();
		// get all users
		while (usersRS.next()) {
			User user = new User(usersRS.getInt("user_id"),
								 usersRS.getString("username"), 
								 usersRS.getString("email"),
								 usersRS.getString("password"));
			//put users to map -key is usarname, value User object
			users.put(usersRS.getString("username"), user);
		}
		usersRS.close();
        // System.out.println("Users loaded successfully");
        return Collections.unmodifiableMap(users);
	}

	public void addNewUserToDB(User user) throws SQLException {

		PreparedStatement st = connection.prepareStatement(INSERT_INTO_USERS);
		st.setString(1, user.getUserName());
		st.setString(2, user.getPassword());
		st.setString(3, user.getEmail());
		st.executeUpdate();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		int id = res.getInt(1);
		res.close();
		st.close();
		ChannelRepository.getInstance().addNewChannelToDB(user);
	}

	public User getUserByUserName(String username) throws SQLException  {
		return this.getAllUsers().get(username);
		
	}
	

	
	
//using JSON
	
//	public boolean checkForUser(String username) {
//		if (this.users == null) {
//			this.users = new TreeMap<>();
//		}
//		return users.containsKey(username);
//	}
//
//	private static Map<String, User> getUsersFromJSONFILE() {
//		Gson gson = new Gson();
//		Map<String, User> map = null;
//		try (Reader reader = new FileReader(USER_JSON_FILE)) {
//			JsonElement json = gson.fromJson(reader, JsonElement.class);
//			String jsonInString = gson.toJson(json);
//
//			// System.out.println(jsonInString);
//			map = gson.fromJson(jsonInString, new TypeToken<Map<String, User>>() {
//			}.getType());
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		if (map == null) {
//			map = new TreeMap<>();
//		}
//		return map;
//
//	}
//
//	private void writeUsersToJSONFile(Map<String, User> users) {
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//		String json = gson.toJson(users);
//		// System.out.println(json);
//		try (FileWriter writer = new FileWriter(USER_JSON_FILE)) {
//
//			gson.toJson(users, writer);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void addNewUserToJSON(User user) throws InvalidDataException {
//		if (user == null)
//			throw new InvalidDataException();
//		users.put(user.getUserName(), user);
//		// updete to json files with users
//		this.writeUsersToJSONFile(users);
//
//	}

}
