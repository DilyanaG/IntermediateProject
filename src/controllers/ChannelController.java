package controllers;

import java.util.Map;

import dataclasses.Channel;
import dataclasses.User;
import dataclasses.Video;
import exceptions.IllegalUserArgumentException;
import services.ChannelService;
import ui.OfflineUserInterface;
import ui.OnlineUserInterface;
import ui.UserInterface;

public class ChannelController {
    
	static ChannelController channelController;
	
	 private UserInterface onlineUser;
	 private UserInterface offlineUser;
	 private ChannelService channelService;
	
	 private void getFields(){
		 onlineUser = OnlineUserInterface.getInstance();
		 channelService = ChannelService.getInstance();
		 offlineUser = OfflineUserInterface.getInstance();
		 
	 }
	 
	 private ChannelController() {
		
	}
	
	public void showChannel(User user, boolean isOnline) {
		getFields();
		Channel channel = null;
		try {
			channel = channelService.giveChannelToUser(user);
		} catch (IllegalUserArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isOnline){
			onlineUser.channelMenu(channel);
			return;
		}
		offlineUser.channelMenu(channel);
	}
	
	public void makePlaylist(String name) {

	}

	public void removePlaylist(String name) {

	}

	public void followChannel(Channel channel) {

	}

	public void addVideo(Video video) {

	}

	public void removeVideo(Video video) {

	}

	public static ChannelController getInstance() {
		if(channelController==null){
			channelController = new ChannelController();
		}
		return channelController;
	}

	public ChannelController getChannel(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addNewVideoToChannel(Channel channel, Video video) {
		// TODO Auto-generated method stub
		
	}

	public Map<Integer,Video> giveVideosToChannel(Channel channel) {
		Map<Integer,Video> videos;
		videos = channelService.giveVideosToChannel(channel);
		return videos;
	}

	
}
