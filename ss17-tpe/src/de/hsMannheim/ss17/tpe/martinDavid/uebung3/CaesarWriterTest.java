package de.hsMannheim.ss17.tpe.martinDavid.uebung3;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

public class CaesarWriterTest {

	private static final String rootDirectory = "src/de/hsMannheim/ss17/tpe/martinDavid/uebung3/";
	private static int key = 3;
	
	@Test
	public void encryptTest() throws IOException {
		StringWriter stringWriter = new StringWriter();
		CaesarWriter casear = new CaesarWriter(stringWriter, key);
		
		String original = "äZa&";
		casear.write(original);
		casear.flush();
		casear.close();
		
		String encrypted = stringWriter.toString();
		assertEquals("Acd&", encrypted);
	}

}
