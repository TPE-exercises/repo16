package de.hsMannheim.ss17.tpe.martinDavid.uebung3;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class CaesarReaderTest {

	private static final String rootDirectory = "src/de/hsMannheim/ss17/tpe/martinDavid/uebung3/";
	private static int key = 3;
	
	@Test
	public void encryptTest() throws IOException {
		String original = "abcÜü%";
		
		CaesarReader caesarReader = new CaesarReader(new StringReader(original), key);
	
		String decrypted = "";
		
		int characterRead;
		Character read;
		
		while((characterRead = caesarReader.read()) != -1) {
			read = (char) characterRead;
			decrypted += read;
		}
		
		caesarReader.close();
		
		assertEquals("XYZzÜ%", decrypted);
	}

}
