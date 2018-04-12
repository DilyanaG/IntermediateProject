package ui;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import dataclasses.Channel;
import dataclasses.Comment;
import dataclasses.Playlist;
import dataclasses.Video;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;
import menus.DefaultMenu;
import menus.Menu;
import sun.net.www.content.text.plain;


public class UserInterface {
	
	private static UserInterface instance;

	private UserInterface() {
		
	}
	private Menu menu = new DefaultMenu();

	private final Scanner scanner = new Scanner(System.in);

	public void run() {
		try {
			runApplication();
			System.out.println("Good bye");
		
		
		} catch (Exception e) {
			if(e instanceof DataBaseException){
				System.out.println("DATABASE PROBLEM!");
			}
			System.out.println(e.getMessage());
			e.printStackTrace();
			Logger.getGlobal().severe(e.getMessage() + " " + e.getStackTrace());
			System.out.println("Internal server error! Application will terminate. Sorry :(");
		} finally {
			scanner.close();
		}
	}

	public void runApplication() throws DataBaseException {
		while (menu != null) {
			final String presentation = menu.presnet();
			System.out.println(presentation);
           while(true){
			final String input = scanner.nextLine();
			try {
				menu = menu.process(input);
				break;
			} catch (IllegalInputException e) {
				System.out.println(e.getMessage());
				if(checkForYesOrNo()){
					break;
				}else{
					return;
				}
			}
		}}
	}



public boolean checkForYesOrNo() {
	
	System.out.println("DO YOU WANT TRY AGAIN!\nENTER [YES] or [NO]");

	while (true) {
		String choice = new Scanner(System.in).next();
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

public static UserInterface getInstance() {
	if(instance == null){
		instance = new UserInterface();
	}
	return instance;
}

public void printVideos(List<Video> videos) {
	if(videos == null||videos.isEmpty()){
		System.out.println("VIDEOS NOT FOUND!");
	}else{
		System.out.println("VIDEO ID\t\t TITLE\tDATE\tLIKES");
		for(Video video : videos){
			
			System.out.println(video.getVideoId()+"\t\t"+video.getTitle()+"\t"+video.getUploadDate()+"\t\t"+video.getCountOfLikes());
		}
	}
	System.out.println("----------------------------------------");
}

public void showVideo(Video video) {

System.out.println("_____________VIDEO______________ ");
System.out.println("url:"+video.getUrl());
System.out.println("title: "+video.getTitle());
System.out.println("description:"+video.getDescription());
System.out.println("likes: "+video.getCountOfLikes());
System.out.println("dislikes: "+video.getCountOfDislikes());
System.out.println("views: "+video.getViews());
System.out.println("upload date: "+video.getUploadDate());
System.out.println("channel: "+video.getChannel().getUser().getUserName());
System.out.println("----------------------------------------");

	
}

public void printComments(List<Comment> comments) {
		for(Comment comment : comments){
			System.out.println("____________COMMENT__________");
			System.out.println("Channel :"+comment.getChannel().getUser().getUserName());
			System.out.println("Comment ID : "+comment.getCommentId());
			System.out.println("Comment content : "+comment.getContent());
			System.out.println("Likes: "+comment.getLikes());
			System.out.println("Dislikes: "+comment.getDislikes());
			System.out.println("Date: "+comment.getPublicationDate());
			
		}
		System.out.println("--------------------------------");
	}

public void printPlaylist(List<Playlist> channelPlaylists) {
	for(Playlist playlist :channelPlaylists){
	System.out.println("____________Playlist_____________");
	System.out.println("name: "+playlist.getPlaylistName());
	System.out.println("create date: "+playlist.getCreationDateTime());
	System.out.println("last video add date: "+playlist.getLastVideoUploaded());
	
	
	}
	System.out.println("------------------------------------------");
}

public void printChannels(List<Channel> channelChannels) {
	for(Channel channel:channelChannels){
		System.out.println("_____________Channel_______________");
		System.out.println("name: "+channel.getUser().getUserName());
	}
	System.out.println("----------------------------------------");
	
}
	
}

/*

// YA CHANGES

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
 */
