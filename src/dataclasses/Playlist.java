package dataclasses;


import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class Playlist {
	private int playlistId;
	private String playlistName;

//TODO	private Set<Video> videos;
	private Date lastVideoUploaded;
	private Date creationDateTime; // TODO WHEN ADDING VIDEO UPDATE

	public Playlist(String playlistName) {
		this.playlistName = playlistName;
	//TODO	this.videos = new TreeSet<Video>();
		this.creationDateTime = new Date();
	}

	public Playlist(int playlistId, String playlistName, Date lastVideoUploaded, Date creationDateTime) {
		this(playlistName);
		this.lastVideoUploaded = lastVideoUploaded;
		this.creationDateTime = creationDateTime;
		this.playlistId = playlistId;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setName(String playlistName) {
		this.playlistName = playlistName;
	}

	//TODO
//	public Set<Video> getVideos() {
//		return videos;
//	}
//
//	public void setVideos(Set<Video> videos) {
//		this.videos = videos;
//	}

	public Date getLastVideoUploaded() {
		return lastVideoUploaded;
	}

	public void setLastVideoUploaded(Date lastVideoUploaded) {
		this.lastVideoUploaded = lastVideoUploaded;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public int getId() {
		return this.playlistId;
	}

}
