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
	private ChannelDAO channelDAO;
	private Channel loginChannel;

	private ChannelService() {
		channelDAO = ChannelDAO.getInstance();
	}
	public static ChannelService getInstance() {
		if (channelService == null) {
			channelService = new ChannelService();
		}
		return channelService;
	}

	public Channel giveChannelToUser(User user) throws IllegalInputException, DataBaseException {

		Channel channel;
		try {
			channel = channelDAO.getChannelByUserName(user.getUserName());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage());
		}
		if (channel == null) {
			throw new IllegalInputException("CHANNEL WITH THIS CHANNEL NAME NOT FOUND!");
		}
		return channel;

	}

	
    //Parametar username, channel, channel id , video id ???
	
public void  followChannel(String channelName) throws IllegalInputException, DataBaseException{
	    if(checkForLoginChannel()){
	    	try {
	    	Channel followedChannel = channelDAO.getChannelByUserName(channelName);
			
				channelDAO.followChannel(this.loginChannel, followedChannel);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataBaseException(e.getMessage());
			} 
			
		}
	    
	}

//	public void setOnlineChannel(User user) throws SQLException, IllegalInputException {
//		this.channel = channelRepository.getChannelByUserName(user.getUserName());
//
//	} 
	
	public boolean checkForLoginChannel() throws IllegalInputException, DataBaseException{
		if(this.loginChannel==null){
			User onlineUser = UserServices.getInstance().getOnlineUser();
			if(onlineUser==null){
				return false;
			}
			try {
				this.loginChannel=channelDAO.getChannelByUserName(onlineUser.getUserName());
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataBaseException(e.getMessage());
			
			}
	    }
		return true;
		
	}	

public List<Video> search(String tags, SortVideoBy sort) {
	// TODO Auto-generated method stub
	return null;
}
public Channel openChannel(String channelName) throws IllegalInputException, DataBaseException {
	try {
		Channel channel = channelDAO.getChannelByUserName(channelName);
	} catch (SQLException e) {
		e.printStackTrace();
		throw new DataBaseException(e.getMessage());
	} 
	return loginChannel;
}
public void unfollowChannel(String channelName) throws IllegalInputException, DataBaseException {
	Channel channel;
	 if(checkForLoginChannel()){
	try {
		   channel = channelDAO.getChannelByUserName(channelName);
			channelDAO.unfollowChannel(this.loginChannel, channel);
		
	} catch (SQLException e) {
		e.printStackTrace();
		throw new DataBaseException(e.getMessage());
	} 
	 }
	
	
}
public List<Video>  showVideos() throws IllegalInputException, DataBaseException {
	List<Video> channelVideos = new ArrayList<>();
	if(checkForLoginChannel()){
       channelVideos =VideoController.getInstance().getVideosToChannel(this.loginChannel);
	}
	return channelVideos;
}
public List<Playlist> showPlaylists() throws IllegalInputException, DataBaseException {
	List<Playlist> channelPlaylists = new ArrayList<>();
	if(checkForLoginChannel()){
		channelPlaylists=PlaylistController.getInstance().getPlaylistForChannel(this.loginChannel);
	}
	return channelPlaylists;
}
public List<Channel> showChannels() throws IllegalInputException, DataBaseException {
	List<Channel> channelChannels =new ArrayList<>();
	if(checkForLoginChannel()){
		
		try {
			channelChannels=this.channelDAO.getFollowedChannels(this.loginChannel);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(e.getMessage());
		} 
	}
	return channelChannels;
}
public Channel getLoginChannel() throws IllegalInputException, DataBaseException {
	if(checkForLoginChannel()){
	   return this.loginChannel ;
	}
	return null;
}
public List<Channel> getFollowedChannels() throws  IllegalInputException, DataBaseException {
	
	List <Channel> channels = new ArrayList<>();
	try {
		if(checkForLoginChannel()){
		channels = this.channelDAO.getFollowedChannels(this.loginChannel);
		
		}
	
	} catch (SQLException e) {
		throw new DataBaseException(e.getMessage());
	}
	return channels;
}


}
