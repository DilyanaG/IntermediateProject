package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoServices {
   private static final String URL_PATTERN =
			"^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	
	
	
	
	
	 private boolean checkForUrl(String url) {
		  if(url!=null){
		        try {
		            Pattern patt = Pattern.compile(URL_PATTERN);
		            Matcher matcher = patt.matcher(url);
		            return matcher.matches();
		        } catch (RuntimeException e) {
		        	 return false;
		    }
		   
		  }
		  return false;
	 }
}
