package parsers;

import java.util.Map;

public class GenericParser {
	private static GenericParser genericParser = null;

	public GenericParser() {
	}

	public static GenericParser getInstance() {
		if (genericParser == null) {
			genericParser = new GenericParser();
		}
		return genericParser;
	}

	public String parseToString(Map<String, String> argsMap, String key) {
		if (argsMap.containsKey(key)) {
			return argsMap.get(key);
		}
		
		return null;
	}

}
