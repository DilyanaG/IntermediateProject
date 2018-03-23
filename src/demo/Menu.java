package demo;

import java.util.Scanner;

import controllers.ChannelController;
import controllers.UserController;
import exceptions.IllegalUserArgumentException;
import exceptions.UserNotFoundException;

public class Menu {
private static Menu menu=null;
	private Menu() {
		
	}
	public static Menu getInstance(){
		if(menu==null){
			menu=new Menu();
		}
		return menu;
	}
	
	private static UserController userController=UserController.getUserControllerInstance();
public  void mainMenu() {
		

		System.out.println("**************YOUTUBE*********************");
		System.out.println("*login                                   *");
		System.out.println("*register                                *");
		System.out.println("*search                                  *");
		System.out.println("*exit                                    *");
		System.out.println("******************************************");

		System.out.println("Enter command:");
		String choice = new Scanner(System.in).next();
		boolean isChoice = true;
		while (isChoice)
			switch (choice.toLowerCase()) {
			case "login": {
				loginMenu();
				isChoice = false;
				break;
			}
			case "register": {
				try {
					userController.register();
				} catch (IllegalUserArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				isChoice = false;
				break;
			}
			case "search": {
				isChoice = false;
				break;
			}
			case "exit":System.exit(0);
			case "printusers": {
				userController.printUsers();
				isChoice = false;
				break;
			}
			case "printchannels": {
			//	ChannelController.getInstance().printChannels();
				isChoice = false;
				break;
			}
			default: {
				System.out.println("WRONG COMMAND!! TRY AGAIN!");
				System.out.println("exit -end the program");
				System.out.println("Enter command:");
				choice = new Scanner(System.in).next();
			}

			}

	}


	public  void loginMenu() {
		System.out.println(".........LOGIN..........");
		String username;
		while (true) {
			System.out.println("back  -return to main menu");
			System.out.println("Enter your username:");
			username = new Scanner(System.in).next().trim();
			if (username.toLowerCase().equals("back")) {
				try {
					mainMenu();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			try {
				userController.login(username);
			} catch (UserNotFoundException e) {
				System.out.println("USER WITH USERNAME '" + username + "' NOT EXISTS!");
				loginMenu();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
