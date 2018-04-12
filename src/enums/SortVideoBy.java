package enums;

import java.util.Map;

import exceptions.IllegalInputException;

public enum SortVideoBy {
	NEWEST, OLDEST, LIKES;
	
	public static SortVideoBy resolve(Map<String, String> argsMap) throws IllegalInputException {
		final String sortvideoby = "sortvideoby";
		 if(argsMap==null||argsMap.isEmpty()){
	    	  throw new IllegalInputException("INCORRECT INPUT!");
	      }
		if (argsMap.containsKey(sortvideoby)) {
			String sortBy = argsMap.get(sortvideoby).toLowerCase();
			switch (sortBy) {
			case "newest":
				return SortVideoBy.NEWEST;
			case "oldest":
				return SortVideoBy.OLDEST;
			case "likes":
				return SortVideoBy.LIKES;
			default:
				throw new IllegalInputException("INCORRECT INPUT!");
			}
		}
		return null;
	}
}
