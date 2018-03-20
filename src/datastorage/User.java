package datastorage;

import java.util.Scanner;

import exceptions.IllegalEmailException;
import exceptions.IllegalNameException;
import exceptions.IllegalUserArgumentException;

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
				System.out.println("Invalid password!!\n Please enter new password");
				password = new Scanner(System.in).next();
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

}
