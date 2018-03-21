package datastorage;

import java.util.*;
import controllers.*;
import exceptions.*;


public class User {

	private static final int MIN_PASSWORD_SIZE = 6;
	
	private final String userName;
	private String password;
	private String email;
	private Channel channel;
	private boolean isOnline;

	public User(String userName) throws IllegalNameException {
		if (userName==null||userName.length()<4) {
			throw new IllegalNameException();
		}
		this.userName = userName;
		try {
			this.channel = new Channel(this);
		} catch (IllegalUserArgumentException e) {
			e.printStackTrace();
		}
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

	public void setEmail(String email) {
		this.email = email;
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

}
