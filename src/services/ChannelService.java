package services;

import java.util.Map;

import dataclasses.Channel;
import dataclasses.User;
import dataclasses.Video;
import exceptions.IllegalUserArgumentException;
import repositories.ChannelRepository;



public class ChannelService {
	
	private static ChannelService channelService;
	private ChannelRepository channelRepository =  ChannelRepository.getInstance();

	
	
	public Channel giveChannelToUser(User user) throws IllegalUserArgumentException {
		Channel channel =  channelRepository.getChannelToUser(user);
		return channel;
		// TODO Auto-generated method stub
		
	}

	public Map<Integer, Video> giveVideosToChannel(Channel channel) {
		// TODO Auto-generated method stub
		return null;
	}
   public static ChannelService getInstance(){
	   if(channelService == null){
		   channelService = new ChannelService();
	   }
	   return channelService;
   }
}
