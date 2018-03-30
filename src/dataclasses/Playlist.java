package dataclasses;


import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class Playlist {
	private int playlistId;
	private String name;
	private Set<Video> videos;
	private Date lastVideoUploaded;
	private Date creationDateTime; // TODO WHEN ADDING VIDEO UPDATE

	public Playlist(String name) {
		this.name = name;
		this.videos = new TreeSet<Video>();
		this.creationDateTime = new Date();
	}

	public Playlist(int playlistId, String name, Date lastVideoUploaded, Date creationDateTime) {
		this(name);
		this.lastVideoUploaded = lastVideoUploaded;
		this.creationDateTime = creationDateTime;
		this.playlistId = playlistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

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

}
