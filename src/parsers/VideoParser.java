package parsers;

import dataclasses.Channel;
import dataclasses.Video;
import exceptions.IllegalChannelArgumentException;
import exceptions.IllegalURLException;
import exceptions.IllegalVideoDescriptionException;
import exceptions.IllegalVideoTitleException;

public class VideoParser {
	// TODO we need to add ID in the classes and pass them instead of the objects
	// UUID.toLong()

	private static VideoParser videoParser;
	private final ChannelParser channelParser = ChannelParser.getInstance();

	private VideoParser() {
	}

	public static VideoParser getInstance() {
		if (videoParser == null) {
			videoParser = new VideoParser();
		}
		return videoParser;
	}

	
	// TODO channelName => channelID because we should have ID-s instead of
	// references to other Objects because of the DB
    // public Video parse(String url, String channelName, String title) {
    // return parse(url, channelName, title, "");
    //	}

	
	public boolean parse(String url, String channelName, String title, String description) {
		Channel channel = channelParser.parse(channelName);
		Video video;
		try {
			video = new Video(url, channel, title, description);
		} catch (IllegalURLException | IllegalChannelArgumentException | IllegalVideoTitleException
				| IllegalVideoDescriptionException e) {
			return false;
			//e.printStackTrace();
		}
       
		return true;
	}
}
