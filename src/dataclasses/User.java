package dataclasses;

import exceptions.IllegalEmailException;
import exceptions.IllegalNameException;
import exceptions.IllegalPasswordException;
import exceptions.IllegalUserArgumentException;

public class User {

	private static final boolean DEFAULT_STATUS = false;

	private final String userName;
	private String password;
	private final String email;
	private Channel channel;
	private boolean isOnline;

	public User(String userName, String password, String email) 
		throws IllegalNameException, IllegalEmailException, 
		IllegalPasswordException, IllegalUserArgumentException {
		if (userName != null) {
			this.userName = userName;
		}else{
			throw new IllegalNameException();
		}

		if (email != null) {
			this.email = email;
		}else{
			throw new IllegalEmailException();
		}
		
		if (password != null) {
			this.password = password;
		}else{
			throw new IllegalPasswordException();
		}

		this.channel = new Channel(this);
		this.isOnline = DEFAULT_STATUS;
	}

	public String getUserName() {
		return userName;
	}
}
