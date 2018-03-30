package enums;

import java.util.Map;

public enum SortPlaylistBy {
	NEWEST, OLDEST, VIDEOS;

	public static SortPlaylistBy resolve(Map<String, String> argsMap) {
		final String sortplaylistby = "sortplaylistby";

		if (argsMap.containsKey(sortplaylistby)) {
			String sortBy = argsMap.get(sortplaylistby);
			switch (sortBy) {
			case "newest":
				return SortPlaylistBy.NEWEST;
			case "oldest":
				return SortPlaylistBy.OLDEST;
			case "likes":
				return SortPlaylistBy.VIDEOS;
			default:
				break;
			}
		}
		return null;
	}
}
