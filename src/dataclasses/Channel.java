package dataclasses;

import java.util.Set;
import java.util.TreeSet;

import comparators.ChannelByUserNameComparator;
import comparators.PlaylistByLastVideoUploadComparator;
import comparators.VideoByUploadDateAscendingComparator;
import exceptions.IllegalUserArgumentException;

public class Channel {
	private static final int DEFAULT_FOLLOWERS = 0;
	
	private final User user;
	private Set<Video> videoclips;
	private Set<Playlist> playlists;
	private Set<Channel> channels;
	private long followers;
	
	public Channel(User user) throws IllegalUserArgumentException{
		if(user != null){
			this.user = user;
		}else{
			throw new IllegalUserArgumentException();
		}
		
		this.videoclips = new TreeSet<Video>(new VideoByUploadDateAscendingComparator());
		this.playlists = new TreeSet<Playlist>(new PlaylistByLastVideoUploadComparator());
		this.channels = new TreeSet<Channel>(new ChannelByUserNameComparator());
		this.followers = DEFAULT_FOLLOWERS;
	}
	
	public User getUser(){
		return this.user;
	}
}
