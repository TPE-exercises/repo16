package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

import static org.junit.Assert.*;

import org.junit.Test;

public class CrypterReverseTest {

	@Test
	public void encrypt() {
		Crypter crypter = new CrypterReverse();
		assertEquals("CBA", crypter.encrypt("ABC"));
		assertEquals("", crypter.encrypt(""));
		assertEquals(null, crypter.encrypt(null));
		assertEquals("&/3", crypter.encrypt("3/&"));
	}
	
	@Test
	public void decrypt() {
		Crypter crypter = new CrypterReverse();
		assertEquals("CBA", crypter.decrypt("ABC"));
		assertEquals("", crypter.decrypt(""));
		assertEquals(null, crypter.decrypt(null));
		assertEquals("&/3", crypter.decrypt("3/&"));
	}
	
}
 