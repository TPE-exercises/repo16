package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void toLowerCaseTest() {
		assertEquals("abc", StringUtils.toLowerCase("AbC"));
		assertEquals("", StringUtils.toLowerCase(""));
		assertEquals("\"%&", StringUtils.toLowerCase("\"%&"));
		assertEquals(null, StringUtils.toLowerCase(null));
	}
	
	@Test
	public void toUpperCaseTest() {
		assertEquals("ABC", StringUtils.toUpperCase("aBc"));
		assertEquals("", StringUtils.toUpperCase(""));
		assertEquals("\"%&", StringUtils.toUpperCase("\"%&"));
		assertEquals(null, StringUtils.toUpperCase(null));
	}
	
}
