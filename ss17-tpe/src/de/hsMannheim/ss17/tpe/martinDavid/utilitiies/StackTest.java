package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public abstract class StackTest {
	
	public int initalMaxSize = 4;
	Stack stack;
	@Before
	abstract public void setup();
	
	@Test
	public void pushAndPop() throws OverflowException, UnderflowException {
		String pushedElement = "1";
		stack.push(pushedElement);
		String popedElement = (String)stack.pop();
		assertEquals(pushedElement, popedElement);
	}
	
	@Test(expected=UnderflowException.class)
	public void shouldUnderflow() throws OverflowException, UnderflowException {
		int elementCount = initalMaxSize;
		
		for(int i = 0; i < elementCount; i++) {
			stack.push("Element: " + i);
		}
		for(int i = 0; i < (elementCount + 1); i++) {
			stack.pop();
		}
	}
	
	@Test
	public void shouldNotUnderflow() throws OverflowException, UnderflowException {
		int elementCount = initalMaxSize;
		
		for(int i = 0; i < elementCount; i++) {
			stack.push("Element: " + i);
		}
		for(int i = 0; i < elementCount; i++) {
			stack.pop();
		}
	}
	
	@Test
	public void shouldDoubleSize() throws OverflowException {
		assertEquals(initalMaxSize, stack.maxSize());
		for(int i = 0; i < 5; i++) {
			stack.push("Element: " + i);
		}
		assertEquals(initalMaxSize * 2, stack.maxSize());
	}
	
	@Test
	public void shouldNotDoubleSize() throws OverflowException {
		assertEquals(initalMaxSize, stack.maxSize());
		for(int i = 0; i < 4; i++) {
			stack.push("Element: " + i);
		}
		assertEquals(initalMaxSize, stack.maxSize());
	}
	
	@Test(expected=OverflowException.class)
	public void shouldOverflow() throws OverflowException {
		for(int i = 0; i < 9; i++) {
			stack.push("Element: " + i);
		}
	}
	@Test
	public void shouldNotOverflow() throws OverflowException {
		for(int i = 0; i < 8; i++) {
			stack.push("Element: " + i);
		}
	}

}
