package parsers;

import java.util.Map;

import dataclasses.Playlist;

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
	
	
	public Playlist parse(Map<String, String> argsMap){
		final String playlistName = argsMap.get("playlistname");
		
		return new Playlist(playlistName);
	}
}
