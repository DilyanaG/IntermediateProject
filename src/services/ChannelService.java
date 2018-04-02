package services;


import java.sql.SQLException;
import java.util.*;

import com.mysql.fabric.xmlrpc.base.Array;

import controllers.PlaylistController;
import controllers.VideoController;
import dataclasses.*;
import exceptions.*;
import enums.SortVideoBy;
import repositories.*;
public class ChannelService {

	private static ChannelService channelService;
	private ChannelRepository channelRepository;
	private Channel loginChannel;

	private ChannelService() {
		channelRepository = ChannelRepository.getInstance();
	}
	public static ChannelService getInstance() {
		if (channelService == null) {
			channelService = new ChannelService();
		}
		return channelService;
	}

	public Channel giveChannelToUser(User user) throws IllegalInputException {

		Channel channel;
		try {
			channel = channelRepository.getChannelByUserName(user.getUserName());
		} catch (SQLException e) {
			e.getMessage();
			throw new IllegalInputException("SORRY!CONNECTION PROBLEM!");
		}
		if (channel == null) {
			throw new IllegalInputException("CHANNEL WITH THIS CHANNEL NAME NOT FOUND!");
		}
		return channel;

	}

	
    //Parametar username, channel, channel id , video id ???
	
public void  followChannel(String channelName) throws IllegalInputException{
	    if(!checkForLoginChannel()){
	    	try {
	    	Channel followedChannel = channelRepository.getChannelByUserName(channelName);
			
				channelRepository.followChannel(this.loginChannel, followedChannel);
			} catch (SQLException e) {
				e.getMessage();
				throw new IllegalInputException("DATABASE ERROR!");
			} 
			
		}
	    
	}

//	public void setOnlineChannel(User user) throws SQLException, IllegalInputException {
//		this.channel = channelRepository.getChannelByUserName(user.getUserName());
//
//	} 
	
	private boolean checkForLoginChannel() throws IllegalInputException{
		if(this.loginChannel==null){
			User onlineUser = UserServices.getInstance().getOnlineUser();
			if(onlineUser==null){
				return false;
			}
			try {
				this.loginChannel=channelRepository.getChannelByUserName(onlineUser.getUserName());
			} catch (SQLException e) {
				e.getMessage();
				throw new IllegalInputException("DATABASE ERROR!");
			
			}
	}
		return true;
	}	

public List<Video> search(String tags, SortVideoBy sort) {
	// TODO Auto-generated method stub
	return null;
}
public Channel openChannel(String channelName) throws IllegalInputException {
	try {
		Channel channel = channelRepository.getChannelByUserName(channelName);
	} catch (SQLException e) {
		throw new IllegalInputException("DATABASE PROBLEM");
		//e.printStackTrace();
	} 
	return loginChannel;
}
public void unfollowChannel(String channelName) throws IllegalInputException {
	Channel channel;
	try {
		channel = channelRepository.getChannelByUserName(channelName);
			channelRepository.unfollowChannel(this.loginChannel, channel);
		
	} catch (SQLException e) {
		throw new IllegalInputException("DATABASE PROBLEM");
		//e.printStackTrace();
	} 
	
	
}
public List<Video>  showVideos() throws IllegalInputException {
	List<Video> channelVideos = new ArrayList<>();
	if(checkForLoginChannel()){
       channelVideos =VideoController.getInstance().giveVideosToChannel(this.loginChannel);
	}
	return channelVideos;
}
public List<Playlist> showPlaylists() throws IllegalInputException {
	List<Playlist> channelPlaylists = new ArrayList<>();
	if(checkForLoginChannel()){
		channelPlaylists=PlaylistController.getInstance().getPlaylistForChannel(this.loginChannel);
	}
	return channelPlaylists;
}
public List<Channel> showChannels() throws IllegalInputException {
	List<Channel> channelChannels =new ArrayList<>();
	if(checkForLoginChannel()){
		
		try {
			channelChannels=this.channelRepository.getFollowedChannels(this.loginChannel);
		} catch (SQLException e) {
			throw new IllegalInputException("DATABASE PROBLEM");
			//e.printStackTrace();
		} 
	}
	return channelChannels;
}
public Channel giveLoginChannel() {
	
	return this.loginChannel ;
}


}
