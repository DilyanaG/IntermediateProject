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
	
	public Boolean parseToBoolean(Map<String, String> argsMap, String key) {
		if (argsMap.containsKey(key)) {
			if(argsMap.get(key).equals("true")){
				return true;
			}
			
			if(argsMap.get(key).equals("false")){
				return false;
			}
		}
		
		return null;
	}
	
	public Integer parseToInteger(Map<String, String> argsMap, String key) {
		Integer x=0;
		if (argsMap.containsKey(key)) {
			x= Integer.parseInt(argsMap.get(key));
		//	System.out.println(x);
			return x;
			
		}
		
		return null;
	}

}
