package de.hsMannheim.ss17.tpe.martinDavid.uebung3;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class CaesarReaderTest {

	private static final String rootDirectory = "src/de/hsMannheim/ss17/tpe/martinDavid/uebung3/";
	private static int key = 3;
	
	@Test
	public void decryptTest() throws IOException {		
		String original = "abcÜü%";
		
		BufferedReader bufferedReader = new BufferedReader(new CaesarReader(new StringReader(original), key));
		
		String decrypted = "";
		
		String lineRead;
		boolean firstLine = true;
		
		while((lineRead = bufferedReader.readLine()) != null) {
			
			//Add a new line character after each line
			if(firstLine)
				firstLine = false;
			else 
				decrypted += System.getProperty("line.separator");
				
			decrypted += lineRead;
		}
		
		bufferedReader.close();
		
		assertEquals("XYZzÜ%", decrypted);
	}

}
