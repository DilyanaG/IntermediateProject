package dataclasses;

public class User {
	
	private int userId;
	private final String userName;
	private String password;
	private final String email;

	public User(int id, String userName, String password, String email){
		this(userName, password, email);
		setUserId(id);
	}

	public User(String userName, String password, String email) {

		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}
}


