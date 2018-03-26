package parsers;

import dataclasses.Playlist;
import exceptions.IllegalChannelArgumentException;

public class PlaylistParser {
private static PlaylistParser playlistParser;
	
	private PlaylistParser(){
	}

	public static PlaylistParser getInstance() {
		if (playlistParser == null) {
			playlistParser = new PlaylistParser();
		}
		return playlistParser;
	}
	
	public Playlist parse(String name) throws IllegalChannelArgumentException{
		Playlist playlist = new Playlist(name);
		return playlist;
	}
}
