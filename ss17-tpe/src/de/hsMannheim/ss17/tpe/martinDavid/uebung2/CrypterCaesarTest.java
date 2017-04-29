package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

import static org.junit.Assert.*;

import org.junit.Test;

public class CrypterCaesarTest {

	@Test
	public void encryptCaesarTest() {
		Crypter caesar = new CrypterCaesar(0);
		assertEquals("AAA", caesar.encrypt("aaa"));
		
		caesar = new CrypterCaesar(28); //key = 2
		assertEquals("CDB", caesar.encrypt("ABZ"));
		assertEquals("az&r", caesar.decrypt(caesar.encrypt("az&r")));
		assertEquals(null, caesar.encrypt(null));
	}
	
	@Test
	public void decryptCaesarTest() {
		Crypter caesar = new CrypterCaesar(0);
		assertEquals("aaa", caesar.decrypt("aAa"));
		
		caesar = new CrypterCaesar(28); //key = 2
		assertEquals("abz", caesar.decrypt("CDB"));
		assertEquals(null, caesar.decrypt(null));
	}
	
}
