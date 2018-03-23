package dataclasses;

import java.time.LocalDateTime;

import java.util.Set;
import java.util.TreeSet;

import exceptions.IllegalChannelArgumentException;


public class Playlist {
	private String name;
	private Set<Video> videos;
	private LocalDateTime lastVideoUploaded;
	private final LocalDateTime creationDateTime; //TODO WHEN ADDING VIDEO UPDATE
	
	public Playlist(String name) throws IllegalChannelArgumentException {
		if(name != null){
			this.name = name;
		}else{
			throw new IllegalChannelArgumentException();
		}
		
		this.videos = new TreeSet<Video>();
		this.creationDateTime = LocalDateTime.now();
	}
	
	public LocalDateTime getLastVideoUploaded(){
		return this.lastVideoUploaded;
	}

}
