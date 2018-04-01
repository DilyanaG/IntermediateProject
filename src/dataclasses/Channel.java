package dataclasses;


public class Channel {
	private static final int DEFAULT_FOLLOWERS = 0;

	private int channelId;
	private final User user;//Channel has a user because the link is lost
	
	//maybe the channel does not have such a field because
	   //it is extracted for a certain situation by those responsible for its DAO 
	//in the DB schema channel have a channel id and user id
	//not have a videos
	//videos keep channel_id
	
//	private Set<Video> videoclips;
//	private Set<Playlist> playlists;
//	private Set<Channel> channels;
//	private long followers;
//   

	
	public Channel(int channelId,User user){
		this(user);
		setChannelId(channelId);
	}
	
	public Channel(User user) {
		this.user=user;
//		this.videoclips = new TreeSet<Video>(new VideoByUploadDateAscendingComparator());
//		this.playlists = new TreeSet<Playlist>(new PlaylistByLastVideoUploadComparator());
//		this.channels = new TreeSet<Channel>((c,c1)->c.getChannelId()-c1.getChannelId());
//		this.followers = DEFAULT_FOLLOWERS;
	}
	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public User getUser() {
		return user;
	}
//
//	public Set<Video> getVideoclips() {
//		return videoclips;
//	}
//
//	public void setVideoclips(Set<Video> videoclips) {
//		this.videoclips = videoclips;
//	}
//
//	public Set<Playlist> getPlaylists() {
//		return playlists;
//	}
//
//	public void setPlaylists(Set<Playlist> playlists) {
//		this.playlists = playlists;
//	}
//
//	public Set<Channel> getChannels() {
//		return channels;
//	}
//
//	public void setChannels(Set<Channel> channels) {
//		this.channels = channels;
//	}
//
//	public long getFollowers() {
//		return followers;
//	}
//
//	public void setFollowers(long followers) {
//		this.followers = followers;
//	}

}
