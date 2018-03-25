package parsers;

import controllers.ChannelController;
import dataclasses.Channel;
import dataclasses.Video;
import exceptions.*;


public class ChannelParser {
	private static ChannelParser channelParser;
	
	private ChannelController channelController;

	public ChannelParser() {
		this.channelController=ChannelController.getInstance();
	}


	public void addNewVideoToChannel(Channel channel, String url, String title, String discription) 
			throws IllegalURLException, IllegalChannelArgumentException,
					IllegalVideoTitleException, IllegalVideoDescriptionException {
		
		Video video = new Video(url, channel, title,discription);
		this.channelController.addNewVideoToChannel(channel,video);
		
	}
	public static ChannelParser getInstance() {
		if (channelParser == null) {
			channelParser = new ChannelParser();
		}
		return channelParser;
	}
}
