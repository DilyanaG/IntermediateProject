package controllers;

import java.util.List;
import dataclasses.Channel;
import dataclasses.Playlist;
import dataclasses.User;
import dataclasses.Video;
import exceptions.IllegalInputException;
import menus.ChannelMenu;
import menus.HomeMenu;
import menus.Menu;
import menus.MyChannelsMenu;
import menus.MyPlaylistsMenu;
import menus.MyVideosMenu;
import menus.SettingsMenu;
import services.ChannelService;
import services.UserServices;
import ui.UserInterface;

public class ChannelController {

	static ChannelController channelController;
	private ChannelService channelService= ChannelService.getInstance();

	private ChannelController() {

	}

	public static ChannelController getInstance() {
		if (channelController == null) {
			channelController = new ChannelController();
		}
		return channelController;
	}

	// TODO where should addVideo be - VideorController or ChannelController?
//	public Menu addVideo(String title, String url, String description) {
//		try {
//			
//		} catch (IllegalInputException e) {
//			System.out.println(e.getMessage()); // TODO This should be handles
//												// by the UI
//		} finally {
//		return new HomeMenu();
//		}
//	}

	public Menu myVideos() throws IllegalInputException {
		List<Video> videos =  VideoController.getInstance().getVideosToChannel(getLoginChannel());
		UserInterface.getInstance().printVideos(videos);
		return new MyVideosMenu();
	}


	public Menu myPlaylists() {
		return new MyPlaylistsMenu();
	}

	public Menu myChannels() {
		return new MyPlaylistsMenu();
	}

	public Menu settings() {
		return new SettingsMenu();
	}

	// TODO if(super.getUser() == null) return defaultMenu;
	public Menu homepage() {
		return new HomeMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu openChannel(String channelName) throws IllegalInputException {
		channelService.openChannel(channelName);
		return new ChannelMenu(channelName);
	}

	public Menu followChannel(String channelName) throws IllegalInputException {
		channelService.followChannel(channelName);
		return new ChannelMenu(this.getLoginChannel().getUser().getUserName());
	}

	public Menu unfollowChannel(String channelName) throws IllegalInputException {
		channelService.unfollowChannel(channelName);
		return new MyChannelsMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showVideos() throws IllegalInputException {
		List<Video> channelVideos = channelService.showVideos();
		return new ChannelMenu(this.getLoginChannel().getUser().getUserName());
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showPlaylists() throws IllegalInputException {
	  List<Playlist> channelPlaylists = channelService.showPlaylists();
		return new ChannelMenu(this.getLoginChannel().getUser().getUserName());
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showChannels() throws IllegalInputException {
		List<Channel> channels = channelService.showChannels();
		return new ChannelMenu(this.getLoginChannel().getUser().getUserName());
	}

	public Menu addVideo(Video video) throws IllegalInputException {
		Channel channel = this.getLoginChannel();
		VideoController.getInstance().addVideoToChannel(video,channel);
		System.out.println("YOUR VIDEO ADDED!");
		return new HomeMenu();
	}
	public Channel getLoginChannel() throws IllegalInputException {
		
		return channelService.getLoginChannel();
	}


	public boolean isLogin() throws IllegalInputException{
		
		return this.channelService.checkForLoginChannel();
	}



	
}
