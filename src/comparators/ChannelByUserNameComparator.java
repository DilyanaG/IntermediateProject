package comparators;

import java.util.Comparator;

import dataclasses.Channel;

public class ChannelByUserNameComparator implements Comparator<Channel> {

	@Override
	public int compare(Channel channel1, Channel channel2) {
		return channel1.getUser().getUserName().compareTo(channel2.getUser().getUserName());
	}

}
