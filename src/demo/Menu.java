package demo;

import java.util.Scanner;

import dataclasses.Channel;
import exceptions.IllegalChannelArgumentException;
import exceptions.IllegalEmailException;
import exceptions.IllegalNameException;
import exceptions.IllegalPasswordException;
import exceptions.IllegalURLException;
import exceptions.IllegalUserArgumentException;
import exceptions.IllegalVideoDescriptionException;
import exceptions.IllegalVideoTitleException;
import exceptions.InvalidDataException;
import parsers.ChannelParser;
import parsers.UserParser;
import parsers.VideoParser;

public class Menu {

	private static Menu menu;
	
	private UserParser userParser;
	private ChannelParser channelParser;
	private VideoParser videoParser;

	private Menu() {
		userParser = UserParser.getInstance();
		channelParser = ChannelParser.getInstance();
		videoParser = VideoParser.getInstance();
	}

	public void mainMenu() {

		System.out.println(" ________________YOUTUBE__________________");
		System.out.println("|>Login                                   |");
		System.out.println("|>SignUp                                  |");
		System.out.println("|>Search                                  |");
		System.out.println("|>Exit                                    |");
		System.out.println("|_________________________________________|");
		System.out.println("Enter command:");
		String choice = getStringFromKeyboard();

		while (true)
			switch (choice.toLowerCase()) {
			case "login": {
				this.loginMenu();
				break;
			}
			case "signup": {
				this.SignUpMenu();
				break;
			}
			case "search": {
				this.searchMenu();
				break;
			}
			case "exit": {
				break;
			}

			default: {
				System.out.println("WRONG COMMAND!!! TRY AGAIN!");
				System.out.println("Exit ->end the program!");
				System.out.println("Enter command:");
				choice = getStringFromKeyboard();
			}

			}

	}

	public void SignUpMenu() {
		System.out.println(" ______________SIGN UP______________");
		System.out.println("|Enter username:");
		String username = getStringFromKeyboard();
		System.out.println("|Enter pasword");
		String password = getStringFromKeyboard();
		System.out.println("|Enter email");
		String email = getStringFromKeyboard();

//		if (!userParser.register(username, password, email)) {
//
//			if (checkForYesOrNo(true)) {
//				this.SignUpMenu();
//			} else {
//				this.mainMenu();
//			}
//		}
	}

	public void loginMenu() {
		System.out.println(" _____________LOGIN______________");
		System.out.println("|Enter your username:");
		String username = getStringFromKeyboard();
		System.out.println("|Enter your password:");
		String password = getStringFromKeyboard();
//		try {
//			this.userParser.login(username, password);
//		} catch (InvalidDataException e) {
//			if (checkForYesOrNo(true)) {
//				this.loginMenu();
//			} else {
//				this.mainMenu();
//			}
//		}

	}

	private void searchMenu() {
		// Rething menu logic into classes of UI and interfaces
	}

	private void channelMenu(Channel channel, boolean isLogin) {
		System.out.println(" __________CHANNEL '" + channel.getUser().getUserName().toUpperCase() + "_______'");
		System.out.println("|>Search");
		if (isLogin) {
			System.out.println("|>AddVideo");
		}
		System.out.println("|>Videos");
		System.out.println("|>Playlist");
		System.out.println("|>Followed");
		if (isLogin) {
			System.out.println("|>Settings");
			System.out.println("|>Logout");
		}
		System.out.println("|>EXIT");
		while (true) {
			System.out.println("Enter command:");
			String command = getStringFromKeyboard();
			switch (command) {
			case "search": {

				break;
			}
			case "addvideo": {
				this.addVideoMenu(channel);
				break;
			}
			case "videos": {
				// TODO
				// this.channelVideosMenu();
				break;
			}
			case "playlist": {
				break;
			}
			case "followed": {
				break;
			}
			case "settings": {
				// TODO
				// this.settingsMenu();
				break;
			}
			case "logout": {
				break;
			}
			case "exit": {
				break;
			}

			default: {
				System.out.println("WRONG COMMAND!!! TRY AGAIN!");
				System.out.println("Exit ->end the program!");
			}
			}
		}
	}

	private void addVideoMenu(Channel channel) {
		System.out.println("------ADD_NEW_VIDEO------");
		System.out.println("Enter video title:");
		String title = getStringFromKeyboard();
		System.out.println("Enter video url:");
		String url = getStringFromKeyboard();
		System.out.println("Do you want to add description[yes/no]:");
		String discription = "";
		if (checkForYesOrNo(false)) {
			discription = getStringFromKeyboard();
		}
		// TODO This should be the VideoParser
		if (!videoParser.parse(url, channel.getUser().getUserName(), title, discription)) {
			if (checkForYesOrNo(true)) {
				addVideoMenu(channel);
			} else {
				this.channelMenu(channel, true);
			}
		}
	}

	private String getStringFromKeyboard() {
		Scanner scanner = new Scanner(System.in);
		return scanner.next().trim().toLowerCase();
	}

	private boolean checkForYesOrNo(boolean forData) {
		if (forData) {
			System.out.println("INCORRECTLY ENTERED DATA!");
			System.out.println("DO YOU WANT TRY AGAIN!\nENTER [YES] or [NO]");
		}
		while (true) {
			String choice = getStringFromKeyboard();
			switch (choice) {
			case "no":
				return false;
			case "yes":
				return true;
			default: {
				System.out.println("INCORRECT INPUT!ENTER AGAIN!");
				continue;
			}
			}
		}
	}

	public static Menu getInstance() {
		if (menu == null) {
			menu = new Menu();
		}
		return menu;
	}
}
