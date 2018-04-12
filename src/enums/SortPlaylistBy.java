package enums;

import java.util.Map;

import exceptions.IllegalInputException;

public enum SortPlaylistBy {
	NAME, CREATEDATE, LASTADDEDVIDEODATE;

	public static SortPlaylistBy resolve(Map<String, String> argsMap) throws IllegalInputException {
		final String sortplaylistby = "sortplaylistby";
      if(argsMap==null||argsMap.isEmpty()){
    	  throw new IllegalInputException("INCORRECT INPUT!");
      }
		if (argsMap.containsKey(sortplaylistby)) {
			String sortBy = argsMap.get(sortplaylistby);
			switch (sortBy) {
			case "name":
				return SortPlaylistBy.NAME;
			case "createdate":
				return SortPlaylistBy.CREATEDATE;
			case "lastaddedvideodate":
				return SortPlaylistBy.LASTADDEDVIDEODATE;
			default:
				throw new IllegalInputException("INCORRECT INPUT!");
			}
		}
		return null;
	}
}
