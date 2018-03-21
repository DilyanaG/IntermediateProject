package demo;

import java.util.Scanner;

import controllers.*;
import exceptions.*;

public class Menu {
	static void mainMenu() {

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
				UserController.register();
				isChoice = false;
				break;
			}
			case "search": {
				isChoice = false;
				break;
			}
			case "exit": {
				isChoice = false;
				break;
			}
			case "printusers": {
				UserController.printUsers();
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

	public static void loginMenu() {
		System.out.println(".........LOGIN..........");
		String username;
		while (true) {
			System.out.println("back  -return to main menu");
			System.out.println("Enter your username:");
			username = new Scanner(System.in).next().trim();
			if (username.toLowerCase().equals("back")) {
				mainMenu();
				return;
			}
			try {
				UserController.login(username);
			} catch (UserNotFoundException e) {
				System.out.println("USER WITH USERNAME '" + username + "' NOT EXISTS!");
				loginMenu();
			}

		}

	}
}
