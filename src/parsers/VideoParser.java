package parsers;

import java.util.Map;

import dataclasses.Video;

public class VideoParser {

	private static VideoParser videoParser;

	private VideoParser() {
	}

	public static VideoParser getInstance() {
		if (videoParser == null) {
			videoParser = new VideoParser();
		}
		return videoParser;
	}

	public Video parse(Map<String, String> argsMap) {
		final String url = argsMap.get("url");
		final String title = argsMap.get("title");
		final String description = argsMap.get("description");

		return new Video(url, null, title, description);
	}
}
