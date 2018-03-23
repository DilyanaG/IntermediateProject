package dataclasses;

import java.util.Set;
import java.util.TreeSet;

import exceptions.IllegalUserArgumentException;

public class Channel {
	private static final int DEFAULT_FOLLOWERS = 0;
	
	private final User user;
	private Set<Video> videoclips;
	private Set<Set<Video>> playlists;
	private Set<Channel> channels;
	private long followers;
	
	public Channel(User user) throws IllegalUserArgumentException{
		if(user != null){
			this.user = user;
		}else{
			throw new IllegalUserArgumentException();
		}
		
		this.videoclips = new TreeSet<Video>();
		this.playlists = new TreeSet<Set<Video>>();
		
		for (Set<Video> playlist : playlists) {
			playlist = new TreeSet<Video>();
		}
		
		this.channels = new TreeSet<Channel>();
		this.followers = DEFAULT_FOLLOWERS;
	}
}
