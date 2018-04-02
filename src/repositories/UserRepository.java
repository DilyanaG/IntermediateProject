package repositories;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataclasses.User;
import exceptions.InvalidDataException;

public class UserRepository{
	// DB
	//selects
	private static final String BY_USERNAME =
			"SELECT user_id,user_name,email,password FROM users WHERE user_name = ?";
	private static final String SELECT_ALL_USERS = 
			"SELECT user_id, user_name, email, password FROM users;";
	//insert
	private static final String INSERT_INTO_USERS =
			"INSERT INTO users (user_name, password, email) VALUES (?, ?, ?);";
	private static final String BY_ID = 
			"SELECT user_id,user_name,email,password FROM users WHERE user_id = ?";
	//update
	private static final String UPDATE_USER_PASSWORD =
			"UPDATE users SET user_password = ? WHERE user_id = ?;";
	//delete
	private static final String DELETE_USER = 
			"DELETE FROM users WHERE user_id = ?;";
	

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
		List<User> list = getUsersFromRezultSet(usersRS);
		for(User user:list){
			users.put(user.getUserName(),user);
		}
		usersRS.close();
        // System.out.println("Users loaded successfully");
        return Collections.unmodifiableMap(users);
	}

	private List <User> getUsersFromRezultSet(ResultSet usersRS) throws SQLException {
		List<User> users = new ArrayList<>();
		while (usersRS.next()) {
			User user = new User(usersRS.getInt("user_id"),
								 usersRS.getString("user_name"), 
								 usersRS.getString("email"),
								 usersRS.getString("password"));
			//put users to map -key is usarname, value User object
			users.add (user);
		}
		return users;
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

	public User getUserByUserName(String username) throws SQLException, InvalidDataException  {
		PreparedStatement userST = connection.prepareStatement(BY_USERNAME);
		userST.setString(1, username);
		ResultSet usersRS = userST.executeQuery();
		List<User> user = new ArrayList<>();
		user = getUsersFromRezultSet(usersRS);
		if(user.isEmpty()){
			throw new InvalidDataException("USER WITH THIS USERNAME NOT FOUND!");
		}
		return user.get(0);
		
	}
	//TODO
    public User getUserById(int userId) throws SQLException, InvalidDataException{
    	PreparedStatement userST = connection.prepareStatement(BY_ID);
		userST.setInt(1, userId);
		ResultSet usersRS = userST.executeQuery();
		List<User> user = new ArrayList<>();
		user = getUsersFromRezultSet(usersRS);
		if(user.isEmpty()){
			throw new InvalidDataException("USER WITH THIS ID NOT FOUND!");
		}
		return user.get(0);
    	
    }
	
    public void updatePassword(User user,String newPassword) throws SQLException{
    	PreparedStatement st = connection.prepareStatement(UPDATE_USER_PASSWORD);
		st.setString(1, newPassword);
		st.setInt(2, user.getUserId());
		st.executeUpdate();
	}
   
    public void deleteUser(User user) throws SQLException, InvalidDataException{
    	PreparedStatement st = connection.prepareStatement(DELETE_USER);
		st.setInt(1, user.getUserId());
		st.executeUpdate(); 
		ChannelRepository.getInstance().deleteChannelByUsername(user.getUserName());
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
