package datastorage;

import java.util.*;
import controllers.*;
import exceptions.*;
import java.util.Scanner;

public class User {

	private static final int MIN_PASSWORD_SIZE = 6;

	private final String userName;
	private String password;
	private final String email;
	private Channel channel;
	private boolean isOnline;



	public User(String userName, String password, String email) throws IllegalNameException, IllegalEmailException {
		if (!checkForUserName(userName)) {
			throw new IllegalNameException();
		}
		this.userName = userName;

		if (!checkForEmail(email)) {
			throw new IllegalEmailException();
		}
		this.email = email;
		
		setPassword(password);
   	  try {
			this.channel = new Channel(this);
		} catch (IllegalUserArgumentException e) {
			e.printStackTrace();
		}

	}

	private boolean checkForUserName(String userName) {

		return false;
	}
	
	private boolean checkForEmail(String email) {

		return false;
	}

	public void setPassword(String password) {
		while (true) {
			if (password == null || password.length() < MIN_PASSWORD_SIZE) {			
					System.out.println("YOUR PASSWORD IS NOT CORRECT!\nPLEASE ENTER NEW PASSWORD:");
					password = new Scanner(System.in).next().trim();
					continue;
			}
			break;
		}
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	
	public String getUserName() {
		return userName;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public void setEmail(String email2) {
		// TODO Auto-generated method stub
		
	}

	public void setChannel(Object channel2) {
		// TODO Auto-generated method stub
		
	}

}
