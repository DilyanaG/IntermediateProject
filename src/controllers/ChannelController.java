package controllers;

import java.util.List;
import java.util.Map;

import dataclasses.Channel;
import dataclasses.Playlist;
import dataclasses.User;
import dataclasses.Video;
import exceptions.IllegalInputException;
import exceptions.IllegalUserArgumentException;
import menus.ChannelMenu;
import menus.DefaultMenu;
import menus.HomeMenu;
import menus.Menu;
import menus.MyChannelsMenu;
import menus.MyPlaylistsMenu;
import menus.MyVideosMenu;
import menus.SettingsMenu;
import services.ChannelService;
import ui.OfflineUserInterface;
import ui.UserInterface;

public class ChannelController {

	static ChannelController channelController;
	private ChannelService channelService;

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
//			return new HomeMenu();
//		}
//	}

	public Menu myVideos() {
		
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
		return new ChannelMenu();
	}

	public Menu followChannel(String channelName) throws IllegalInputException {
		channelService.followChannel(channelName);
		return new ChannelMenu();
	}

	public Menu unfollowChannel(String channelName) throws IllegalInputException {
		channelService.unfollowChannel(channelName);
		return new MyChannelsMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showVideos() throws IllegalInputException {
		List<Video> channelVideos = channelService.showVideos();
		return new ChannelMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showPlaylists() throws IllegalInputException {
	  List<Playlist> channelPlaylists = channelService.showPlaylists();
		return new ChannelMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showChannels() throws IllegalInputException {
		List<Channel> channels = channelService.showChannels();
		return new ChannelMenu();
	}

	public Menu addVideo(Video video) {
		// TODO Auto-generated method stub
		return null;
	}

	

//	// TODO Why do we need this?
//	public ChannelController getChannel(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
}
