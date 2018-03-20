package controllers;

import java.util.*;

import datastorage.User;

public class UserController {
  private static Set<User> users;
  static{
	  users=new TreeSet<User>((u2,u1)->u1.getUserName().compareTo(u2.getUserName()));
  }
   
	
	
  public static void register(){
	  
  }
  public static void login(User user){
	  
  }
  public static void logout(User user){
	  
  }
  public static void deleteUser(User user){
	  
  }
}
