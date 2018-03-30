package enums;

import java.util.Map;

public enum SortVideoBy {
	NEWEST, OLDEST, LIKES;
	
	public static SortVideoBy resolve(Map<String, String> argsMap) {
		final String sortvideoby = "sortvideoby";

		if (argsMap.containsKey(sortvideoby)) {
			String sortBy = argsMap.get(sortvideoby);
			switch (sortBy) {
			case "newest":
				return SortVideoBy.NEWEST;
			case "oldest":
				return SortVideoBy.OLDEST;
			case "likes":
				return SortVideoBy.LIKES;
			default:
				break;
			}
		}
		return null;
	}
}
