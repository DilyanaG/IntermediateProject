package dataclasses;

import java.util.Set;
import java.util.TreeSet;

import comparators.ChannelByUserNameComparator;
import comparators.PlaylistByLastVideoUploadComparator;
import comparators.VideoByUploadDateAscendingComparator;


public class Channel {
	private static final int DEFAULT_FOLLOWERS = 0;

	private final User user;
	private int channelId;
	private Set<Video> videoclips;
	private Set<Playlist> playlists;
	private Set<Channel> channels;
	private long followers;

	
	public Channel(int id,User user){
		this(user);
		setChannelId(id);
	}
	
	public Channel(User user) {
        this.user = user;
		this.videoclips = new TreeSet<Video>(new VideoByUploadDateAscendingComparator());
		this.playlists = new TreeSet<Playlist>(new PlaylistByLastVideoUploadComparator());
		this.channels = new TreeSet<Channel>(new ChannelByUserNameComparator());
		this.followers = DEFAULT_FOLLOWERS;
	}

	public Set<Video> getVideoclips() {
		return videoclips;
	}

	public void setVideoclips(Set<Video> videoclips) {
		this.videoclips = videoclips;
	}

	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Set<Channel> getChannels() {
		return channels;
	}

	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}

	public long getFollowers() {
		return followers;
	}

	public void setFollowers(long followers) {
		this.followers = followers;
	}

	public static int getDefaultFollowers() {
		return DEFAULT_FOLLOWERS;
	}

	public User getUser() {
		return user;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
}
