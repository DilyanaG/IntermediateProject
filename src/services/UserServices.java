package services;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import dataclasses.User;
import exceptions.IllegalInputException;
import repositories.UserRepository;

public class UserServices {
	
	private static final int MIN_PASSWORD_SIZE = 4;
	private static UserServices userServices;
   private static final String EMAIL_PATTERN =

			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static UserRepository userRepository;
	
	private User onlineUser = null;
	
	private UserServices() {
		userRepository=UserRepository.getInstance();
	}
	
	
	public boolean register(User user)
			throws  IllegalInputException {
		if(user.getUserName()==null||user.getUserName().length()<4)
			throw new IllegalInputException("INCORRECT NAME!");
		//check for valid user registration data 
		if(!this.checkForPassword(user.getPassword()))
			throw new IllegalInputException("PASSWORD IS NOT CORRECT!");
		if(!this.checkForPattern(user.getEmail()))
			throw new IllegalInputException("EMAIL IS NOT CORRECT!");
		try {
		if(this.checkForUser(user.getUserName()))
			throw new IllegalInputException("USERNAME IS NOT CORRECT");
		//add new user in BD/FILE
          this.userRepository.addNewUserToDB(user);
			return true;
		} catch (SQLException e) {
			e.getMessage();
			return false;
		}
		
	} 
	

	

	public User login(String username, String password) 
			  throws IllegalInputException {
		if(username==null||username.length()<4)
			throw new IllegalInputException("INCORRECT NAME!");
		if(password==null)
			throw new IllegalInputException("INCORRECT PASSWORD!");
		User user=null;
		try {
			if(!checkForUser(username)){
				throw  new IllegalInputException("USER WITH THIS USERNAME DOES NOT EXIST!");
			}
			
			user = userRepository.getUserByUserName(username);
			if(!user.getPassword().equals(password)){
				throw new IllegalInputException("INCORRECT PASSWORD!");
			}
		} catch (SQLException e) {
			  e.getMessage();
			  throw new IllegalInputException("PROBLEM WITH CONNECTION!");
		}
		this.onlineUser = user;
		
	    return user;	
		
	}
	
	private boolean checkForUser(String username) throws SQLException {
		return userRepository.getAllUsers().containsKey(username);
	}

	
	private boolean checkForPassword(String password){
		if(password==null||password.length()<MIN_PASSWORD_SIZE){
			return false;
		}
		return true;
	}
	
	 private boolean checkForPattern(String url) {
		  if(url!=null){
		        try {
		            Pattern patt = Pattern.compile(EMAIL_PATTERN);
		            Matcher matcher = patt.matcher(url);
		            return matcher.matches();
		        } catch (RuntimeException e) {
		        	 return false;
		    }
		   
		  }
		  return false;
	 }


	public static UserServices getInstance() {
		if(userServices==null){
			userServices= new UserServices();
		}
		return userServices;
	}

 public User getOnlineUser() {
	return onlineUser;
}
	public void logout(String username) {
		this.onlineUser=null;
	}


	public void changePassword(String newPassword) throws IllegalInputException  {
		if(!checkForPassword(newPassword)){
			throw new IllegalInputException("NEW PASSWORD IS NOT CORRECT!");
		}
		try {
			userRepository.updatePassword(onlineUser, newPassword);
		} catch (SQLException e) {
		   //System.out.println(e.getMessage());
			throw new IllegalInputException("DATABASE PROBLEM ");
		}
		
	}


	public void deleteAccount(String password) throws IllegalInputException {
		if(!this.onlineUser.getPassword().equals(password)){
			throw new IllegalInputException("WRONG PASSWORD !");
		}
		try {
			this.userRepository.deleteUser(onlineUser);
		} catch (SQLException e) {
			throw new IllegalInputException("DATEBASE PROBLEM!");
			//System.out.println(e.getMessage());
		}
		this.logout(onlineUser.getUserName());
		
		}
	
}

