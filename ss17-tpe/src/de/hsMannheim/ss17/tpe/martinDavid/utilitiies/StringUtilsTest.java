package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void toLowerCaseTest() {
		assertEquals("abc", StringUtils.toLowerCase("ABC"));
		assertEquals("", StringUtils.toLowerCase(""));
		assertEquals("\"%&", StringUtils.toLowerCase("\"%&"));
		assertEquals(null, StringUtils.toLowerCase(null));
	}
	
}
