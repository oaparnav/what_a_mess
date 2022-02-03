package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Helper {
	
	private static final Pattern smallLetters = Pattern.compile(".*[a-z].*");
	   
	   private static final Pattern capitalLetters = Pattern.compile(".*[A-Z].*");
	   
	   private static final Pattern integerValues = Pattern.compile(".*[^0-9].*");

	    public static String decryptPassword(String encryptedPassword) {
	    	StringBuilder decryptedValue = new StringBuilder();
	        List<String> chars = new ArrayList<>();
	        
	        for (char c : encryptedPassword.toCharArray()) {
	        	chars.add(String.valueOf(c));
			}
	        
	        decryptAlphabetsAndSpecialCharecters(chars);
	        decryptNumbers(chars);
	        
	        chars.forEach(value -> decryptedValue.append(value));
	        return decryptedValue.toString();

	    }

		private static void decryptNumbers(List<String> chars) {
			for(int i =0; i<chars.size()-1; i++) {
				if(isMatchingIntegers(chars.get(i))) {
					int index = chars.lastIndexOf("0");
					 chars.set(index, chars.get(i));
					 chars.remove(i);
					 i = i+1;
				}
			}
			
		}

		private static void decryptAlphabetsAndSpecialCharecters(List<String> chars) {
			for(int i =0; i<chars.size()-1; i++) {
	        	String firstString = chars.get(i);
	        	String secondString = chars.get(i+1);
	        	if(isMatchingCapitalLetters(firstString) && isMatchingSmallLetters(secondString)) {
	        		chars.set(i, secondString);
	        		chars.set(i+1, firstString);
	        		chars.remove(i+2);
	        		i = i+1;
	        	} 
	        }
		}
	    
	    private static boolean isMatchingIntegers(String value) {
			return integerValues.matcher(value).matches();
		}

		private static boolean isMatchingCapitalLetters(String value) {
			return capitalLetters.matcher(value).matches();
		}

		private static boolean isMatchingSmallLetters(String value) {
			return smallLetters.matcher(value).matches();
		}

}
