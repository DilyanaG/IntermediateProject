package controllers;

import java.util.*;

import datastorage.User;

public class UserController {
  private static Set<User> users;
  static{
	  users=new TreeSet<User>((u2,u1)->u1.getUserName().compareTo(u2.getUserName()));
  }
   
	
	
  public void register(){
	  
  }
  public void login(User user){
	  
  }
  public void logout(User user){
	  
  }
  public void deleteUser(User user){
	  
  }
}
