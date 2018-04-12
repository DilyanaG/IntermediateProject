package enums;

import java.util.Map;

import exceptions.IllegalInputException;

public enum SortSearchBy {
	DATE, VIEWS, LIKES;
	
	public static SortSearchBy resolve(Map<String, String> argsMap) throws IllegalInputException {
		final String sortsearchby = "sortsearchby";
		 if(argsMap==null||argsMap.isEmpty()){
	    	  throw new IllegalInputException("INCORRECT INPUT!");
	      }
		if (argsMap.containsKey(sortsearchby)) {
			String sortBy = argsMap.get(sortsearchby);
			switch (sortBy) {
			case "newest":
				return SortSearchBy.DATE;
			case "views":
				return SortSearchBy.VIEWS;
			case "likes":
				return SortSearchBy.LIKES;
			default:
				throw new IllegalInputException("INCORRECT INPUT!");
			}
		}
		return null;
	}
}
