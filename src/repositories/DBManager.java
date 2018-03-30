package repositories;

import java.sql.*;

public class DBManager {

	private static DBManager instance;
	
	private Connection connection;
	
	private static final String DB_IP = "127.0.0.1";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "mydb";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "12345qwerty";
	private static final String URL = "jdbc:mysql://"+DB_IP+":"+DB_PORT+"/"+DB_NAME;
	
	private DBManager(){
		
		try {
			//load driver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry, driver not loaded or does not exist!!");
			
		}
		try {
			//create connection 
			connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println("Sorry,connection failed! Maybe wrong credentials!");
			System.out.println(e.getMessage());
		}
	}
	
	public synchronized static DBManager getInstance(){
		if(instance == null){
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}