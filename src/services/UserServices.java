package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServices {
	private static final String EMAIL_PATTERN =

			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	
	
	
	 private boolean checkForPattern(String url) {
		  if(url!=null){
		        try {
		            Pattern patt = Pattern.compile(EMAIL_PATTERN);
		            Matcher matcher = patt.matcher(url);
		            return matcher.matches();
		        } catch (RuntimeException e) {
		        	 return false;
		    }
		   
		  }
		  return false;
	 }
}
