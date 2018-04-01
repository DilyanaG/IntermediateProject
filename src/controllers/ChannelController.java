package controllers;

import java.util.Map;

import dataclasses.Channel;
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

	private UserInterface onlineUser;
	private UserInterface offlineUser;
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
	public Menu addVideo(String title, String url, String description) {
		try {
			videoServices.addVideo(title, url, description);
		} catch (IllegalInputException e) {
			System.out.println(e.getMessage()); // TODO This should be handles
												// by the UI
		} finally {
			return new HomeMenu();
		}
	}

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
	public Menu openChannel(String channelName) {
		channelService.openChannel(channelName);
		return new ChannelMenu();
	}

	public Menu followChannel(String channelName) {
		channelService.followChannel(channelName);
		return new ChannelMenu();
	}

	public Menu unfollowChannel(String channelName) {
		channelService.openChannel(channelName);
		return new MyChannelsMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showVideos(int channelID) {
		channelService.showVideos(channelID);
		return new ChannelMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showPlaylists(int channelID) {
		channelService.showPlaylists(channelID);
		return new ChannelMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showChannels(int channelID) {
		channelService.showChannels(channelID);
		return new ChannelMenu();
	}

	/////////////////
	
	// TODO what is this?
	private void getFields() {
		// onlineUser = OnlineUserInterface.getInstance();
		channelService = ChannelService.getInstance();
		offlineUser = OfflineUserInterface.getInstance();

	}

	// TODO Why do we need this?
	public ChannelController getChannel(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO why do we need this?
	public void showChannel(User user, boolean isOnline) {
		getFields(); // TODO what is this?
		Channel channel = null;
		try {
			channel = channelService.giveChannelToUser(user);
		} catch (IllegalUserArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// if(isOnline){
		// onlineUser.channelMenu(channel);
		// return;
		// }
		// offlineUser.channelMenu(channel);
	}

	// TODO Why do we need this?
	public Map<Integer, Video> giveVideosToChannel(Channel channel) {
		Map<Integer, Video> videos;
		videos = channelService.giveVideosToChannel(channel);
		return videos;
	}
}
