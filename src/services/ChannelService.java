package services;


import java.sql.SQLException;
import java.util.*;
import dataclasses.*;
import exceptions.*;
import enums.SortVideoBy;
import repositories.*;
public class ChannelService {

	private static ChannelService channelService;
	private ChannelRepository channelRepository;
	private Channel channel;

	private ChannelService() {
		channelRepository = ChannelRepository.getInstance();
	}
	public static ChannelService getInstance() {
		if (channelService == null) {
			channelService = new ChannelService();
		}
		return channelService;
	}

	public Channel giveChannelToUser(User user) throws InvalidDataException {

		Channel channel;
		try {
			channel = channelRepository.getChannelByUserName(user.getUserName());
		} catch (SQLException e) {
			e.getMessage();
			throw new InvalidDataException("SORRY!CONNECTION PROBLEM!");
		}
		if (channel == null) {
			throw new InvalidDataException("CHANNEL WITH THIS CHANNEL NAME NOT FOUND!");
		}
		return channel;

	}

	public Set<Video> giveVideosToChannel(Channel channel) {
		Set<Video> videos = new HashSet<>();
		try {
			videos = VideoRepository.getInstance().getVideosForChannel(channel);
		} catch (SQLException e) {
			e.getMessage();
		}
		return videos;
	}
    //Parametar username, channel, channel id , video id ???
	
	public boolean  followChannel(Channel folowedChannel){
		
		channelRepository.followToChannel(this.channel,folowedChannel);
		return true;
	}

	public void setOnlineChannel(User user) throws SQLException {
		this.channel = channelRepository.getChannelByUserName(user.getUserName());

	}

public List<Video> search(String tags, SortVideoBy sort) {
	// TODO Auto-generated method stub
	return null;
}

}
