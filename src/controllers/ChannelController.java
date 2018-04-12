package controllers;

import java.util.List;
import dataclasses.Channel;
import dataclasses.Playlist;
import dataclasses.User;
import dataclasses.Video;
import exceptions.DataBaseException;
import exceptions.IllegalInputException;
import menus.ChannelMenu;
import menus.DefaultMenu;
import menus.HomeMenu;
import menus.Menu;
import menus.MyChannelsMenu;
import menus.MyPlaylistsMenu;
import menus.MyVideosMenu;
import menus.SettingsMenu;
import menus.VisitorChannelMenu;
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

	public Menu myVideos() throws IllegalInputException, DataBaseException {
		List<Video> videos =  VideoController.getInstance().getVideosToChannel(getLoginChannel());
		UserInterface.getInstance().printVideos(videos);
		return new MyVideosMenu();
	}


	public Menu myPlaylists() throws IllegalInputException, DataBaseException {
		List<Playlist> channelPlaylists = 
				PlaylistController.getInstance().getPlaylistForChannel(this.getLoginChannel());
		if(channelPlaylists==null||channelPlaylists.isEmpty()){
			System.out.println("CHANNEL DON'T HAVE PLAYLISTS");
			return new HomeMenu();
		}
		UserInterface.getInstance().printPlaylist(channelPlaylists);
		
		return new MyPlaylistsMenu();
	}

	public Menu myChannels() throws IllegalInputException, DataBaseException {
		List<Channel> channelChannels = this.channelService.getFollowedChannels();
				
		if(channelChannels==null||channelChannels.isEmpty()){
			System.out.println("CHANNEL DON'T FOLLOW CHANNELS");
			return new HomeMenu();
		}
		UserInterface.getInstance().printChannels(channelChannels);
		
		return new MyChannelsMenu();
	}

	public Menu settings() {
		return new SettingsMenu();
	}


	public Menu homepage() throws IllegalInputException, DataBaseException {
		if(this.isLogin()){
		return new HomeMenu();
		}
		return new DefaultMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu openChannel(String channelName) throws IllegalInputException, DataBaseException {
		channelService.openChannel(channelName);
		return new ChannelMenu(channelName);
	}

	public Menu followChannel(String channelName) throws IllegalInputException, DataBaseException {
		channelService.followChannel(channelName);
		return new ChannelMenu(this.getLoginChannel().getUser().getUserName());
	}

	public Menu unfollowChannel(String channelName) throws IllegalInputException, DataBaseException {
		channelService.unfollowChannel(channelName);
		return new MyChannelsMenu();
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showVideos() throws IllegalInputException, DataBaseException {
		Channel channel = VideoController.getInstance().getOpenedVideo().getChannel();
		List<Video> channelVideos = VideoController.getInstance().
				getVideosToChannel(channel);
		UserInterface.getInstance().printVideos(channelVideos);
		return new ChannelMenu(channel.getUser().getUserName());
		
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showPlaylists() throws IllegalInputException, DataBaseException {
	  List<Playlist> channelPlaylists = channelService.showPlaylists();
		return new ChannelMenu(this.getLoginChannel().getUser().getUserName());
	}

	// TODO if(super.getUser() == null) return visitorChannelMenu;
	public Menu showChannels() throws IllegalInputException, DataBaseException {
		List<Channel> channels = channelService.showChannels();
		return new ChannelMenu(this.getLoginChannel().getUser().getUserName());
	}

	public Menu addVideo(Video video) throws IllegalInputException, DataBaseException {
		Channel channel = this.getLoginChannel();
		VideoController.getInstance().addVideoToChannel(video,channel);
		System.out.println("YOUR VIDEO ADDED!");
		return new HomeMenu();
	}
	public Channel getLoginChannel() throws IllegalInputException, DataBaseException {
		
		return channelService.getLoginChannel();
	}


	public boolean isLogin() throws IllegalInputException, DataBaseException{
		
		return this.channelService.checkForLoginChannel();
	}



	
}
