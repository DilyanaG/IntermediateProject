package services;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataclasses.User;
import exceptions.IllegalEmailException;
import exceptions.IllegalNameException;
import exceptions.IllegalPasswordException;
import exceptions.InvalidDataException;
import exceptions.UserNotFoundException;
import repositories.UserRepository;

public class UserServices {
	
	private static final int MIN_PASSWORD_SIZE = 4;
	private static UserServices userServices;
   private static final String EMAIL_PATTERN =

			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static UserRepository userRepository;
	
	private UserServices() {
		userRepository=UserRepository.getInstance();
	}
	
	
	public boolean register(User user)
			throws IllegalPasswordException, IllegalEmailException,
				IllegalNameException, InvalidDataException {
		if(user==null)
			 throw new InvalidDataException();
		//check for valid user registration data 
		if(!this.checkForPassword(user))
			throw new IllegalPasswordException();
		if(!this.checkForPattern(user.getEmail()))
			throw new IllegalEmailException();
		try {
		if(this.checkForUser(user.getUserName()))
			throw new IllegalNameException();
		//add new user in BD/FILE
          this.userRepository.addNewUserToDB(user);
			return true;
		} catch (SQLException e) {
			e.getMessage();
			return false;
		}
		
	} 
	

	private boolean checkForUser(String userName) throws SQLException {
		
		return userRepository.getAllUsers().containsKey(userName);
	}


	public User login(String username, String password) 
			  throws IllegalPasswordException, IllegalNameException,
			          UserNotFoundException, SQLException {
		if(username==null)
			throw new IllegalNameException("INCORRECT NAME!");
		if(password==null)
			throw new IllegalPasswordException("INCORRECT PASSWORD!");
		if(!checkForUser(username))
			throw  new UserNotFoundException();
		User user = userRepository.getUserByUserName(username);
		if(!user.getPassword().equals(password)){
			throw new IllegalPasswordException("INCORRECT PASSWORD!");
		}
		return user;	
		
	}
	
	
	
	private boolean checkForPassword(User user){
		if(user.getPassword().length()<MIN_PASSWORD_SIZE){
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

	
}
