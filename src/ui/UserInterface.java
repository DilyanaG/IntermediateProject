package ui;

import java.util.Map;
import java.util.Scanner;

import controllers.ChannelController;
import controllers.UserController;
import controllers.VideoController;
import dataclasses.Channel;
import dataclasses.Video;

public class UserInterface {

	public static final String SUCCESSFUL_SIGN_UP_MESSAGE = "YOU SUCCESSFULLY REGISTERED !!!";

	private UserController userController;
	private UserInterface onlineUser;
	private UserInterface offlineUser;
	private ChannelController channelController;
	private VideoController videoController;

	public UserInterface() {
		userController = UserController.getInstance();
		channelController = ChannelController.getInstance();
		videoController = VideoController.getInstance();
	}

	public void mainMenu() {

		this.getOnlineandOfflineUserIntefaces();
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
				return;
			}
			case "signup": {
				this.SignUpMenu();
				return;
			}
			case "search": {
				this.searchMenu();
				return;
			}
			case "exit": {
				//TODO
				return;
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
		if (userController.register(username, password, email)) {
			System.out.println(onlineUser.SUCCESSFUL_SIGN_UP_MESSAGE);
			this.loginMenu();
			return;
		}

		if (this.checkForYesOrNo(true)) {
			this.SignUpMenu();
		} else {
			mainMenu();
		}
	}

	public void loginMenu() {
		System.out.println(" _____________LOGIN______________");
		System.out.println("|Enter your username:");
		String username = getStringFromKeyboard();
		System.out.println("|Enter your password:");
		String password = getStringFromKeyboard();

		if (!this.userController.login(username, password)) {
			if (this.checkForYesOrNo(true)) {
				loginMenu();
			} else {
				mainMenu();
			}
		}

	}

	// TODO
	private void searchMenu() {
		// Rething menu logic into classes of UI and interfaces
	}

	public void channelMenu(Channel channel) {
		System.out.println(" __________CHANNEL '" + channel.getUser().getUserName().toUpperCase() + "'_______");
		channelPrintBar();
		System.out.println("|>EXIT");
		enterCommand(channel);
	}

	protected void channelPrintBar() {
		System.out.println("|>Search");
		System.out.println("|>Videos");
		System.out.println("|>Playlist");
		System.out.println("|>Followed");
	}

	protected void enterCommand(Channel channel) {
		while(true){ 
			System.out.println("Enter command:");
			String command = getStringFromKeyboard();
			if(giveCommandsFromChannel(command,channel)){
				break;
			}
			}
	}

	protected boolean giveCommandsFromChannel(String command,Channel channel) {

		switch (command) {
		case "search": {
         
			return true;
		}
		
		case "videos": {
			this.channelVideosMenu(channel);
			return true;
		}
		case "playlist": {
			
			return true;
		}
		case "followed": {
			return true;
		}
		
		case "exit": {
			return true;
		}

		default: {
			System.out.println("WRONG COMMAND!!! TRY AGAIN!");
			System.out.println("Exit ->end the program!");
			return false;
		}

		}
	}

	private void channelVideosMenu(Channel channel) {
		System.out.println("--------'"+channel.getUser().getUserName()+"' UPLOAD VIDEOS-----------");
		Map<Integer,Video> videos = videoController.giveVideosToChannel(channel);
		if(videos!=null&&!videos.isEmpty()){
		for(Map.Entry<Integer,Video> entry : videos.entrySet()){
			System.out.println(entry.getKey()+" : "+entry.getValue().getTitle());
		}
		System.out.println("Enter video id to open video: ");
		//TO DO validate
		int id=new Scanner(System.in).nextInt();
		this.videoMenu(videos.get(id));
		
		}else{
			System.out.println("CHANNEL DON'T HAVE A UPLOAD VIDEOS!");
		}
	}

	private void videoMenu(Video video) {
		 System.out.println("---------------------------");
		 System.out.println("title: "+video.getTitle());
         System.out.println("");		
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
		// if (!.parse(url, channel.getUser().getUserName(), title,
		// discription)) {
		// if (checkForYesOrNo(true)) {
		// addVideoMenu(channel);
		// } else {
		// this.channelMenu(channel, true);
		// }
		// }
	}

	protected String getStringFromKeyboard() {
		Scanner scanner = new Scanner(System.in);
		return scanner.next().trim().toLowerCase();
	}

	public boolean checkForYesOrNo(boolean forData) {
		if (forData) {
			System.out.println("INCORRECTLY ENTERED DATA!");
		}
		System.out.println("DO YOU WANT TRY AGAIN!\nENTER [YES] or [NO]");

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

	private void getOnlineandOfflineUserIntefaces() {
		this.onlineUser = OnlineUserInterface.getInstance();
		this.offlineUser = OfflineUserInterface.getInstance();
	}

}
