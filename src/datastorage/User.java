package datastorage;

import java.util.Scanner;
import exceptions.IllegalNameException;
import exceptions.PasswordException;

public class User {

	private static final int MIN_PASSWORD_SIZE = 6;
	
	private final String userName;
	private String password;
	private String email;
	private Channel channel;
	private boolean isOnline;

	public User(String userName) throws IllegalNameException {
		if (!checkForUserName(userName)) {
			throw new IllegalNameException();
		}
		this.userName = userName;
		this.channel = new Channel();
	}

	private boolean checkForUserName(String userName) {

		return false;
	}

	public void setPassword(String password) {
		while (true) {
			if (password == null || password.length() < MIN_PASSWORD_SIZE) {
				try {
					throw new PasswordException();
				} catch (PasswordException e) {
					System.out.println("Invalid password!!\n Please enter new password");
					password = new Scanner(System.in).next();
					// e.printStackTrace();
					continue;
				}
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
