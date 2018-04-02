package parsers;

import java.util.Map;

import dataclasses.Channel;

public class ChannelParser {
	private static ChannelParser channelParser = null;

	public ChannelParser() {
	}

	public static ChannelParser getInstance() {
		if (channelParser == null) {
			channelParser = new ChannelParser();
		}
		return channelParser;
	}

	public Channel parse(Map<String, String> argsMap) {
		return null;

	//TODO makeParser;
	}
}
