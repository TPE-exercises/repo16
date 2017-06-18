package de.hsMannheim.ss17.tpe.martinDavid.uebung5;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class CountWordsTest {

	@Test
	public void countWordsString() {
		String string = "hallo hallo Hallo ,hallo, test next test hallo test";
		
		CountWords count = new CountWords();
		
		Map<String, Integer> map = count.countWords(string);
		
		assertEquals(5, map.get("hallo").intValue());
		assertEquals(3, map.get("test").intValue());
	}

}
