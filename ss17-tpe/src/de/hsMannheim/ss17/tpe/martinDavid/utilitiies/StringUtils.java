package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public class StringUtils {

	/**
	 * Changes all upper case letters from the German alphabet into lower case letters
	 * @param text original text
	 * @return text in lower case
	 */
	public static String toLowerCase(String text) {
		
		if(text == null)
			return null;
		
		char[] lowerCaseText = text.toCharArray();
		
		for(int i = 0; i < text.length(); i++) {
			char letter = text.charAt(i);
			
			if(letter >= 'A' && letter <= 'Z') {
				letter += 'a' - 'A';
				
				lowerCaseText[i] = letter;
			}
		}
		
		return new String(lowerCaseText);
	}
	
}
