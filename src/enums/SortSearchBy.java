package enums;

import java.util.Map;

public enum SortSearchBy {
	DATE, VIEWS, LIKES;
	
	public static SortSearchBy resolve(Map<String, String> argsMap) {
		final String sortsearchby = "sortsearchby";

		if (argsMap.containsKey(sortsearchby)) {
			String sortBy = argsMap.get(sortsearchby);
			switch (sortBy) {
			case "newest":
				return SortSearchBy.DATE;
			case "oldest":
				return SortSearchBy.VIEWS;
			case "likes":
				return SortSearchBy.LIKES;
			default:
				break;
			}
		}
		return null;
	}
}
