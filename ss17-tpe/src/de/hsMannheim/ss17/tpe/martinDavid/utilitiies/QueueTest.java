package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public abstract class QueueTest {
	
	public int initalMaxSize = 4;
	
	Queue queue;
	@Before
	public void setUp() {
		queue = createQueue(initalMaxSize);
	}
	
	abstract public Queue createQueue(int maxSize);

	@Test
	public void testEnqueueAndDequeue() throws OverflowException, UnderflowException {
		String enqueuedElement = "1";
		queue.enqueue(enqueuedElement);
		String dequeuedElement = (String)queue.dequeue();
		assertEquals(enqueuedElement, dequeuedElement);
	}
	
	@Test(expected=UnderflowException.class)
	public void shouldUnderflow() throws OverflowException, UnderflowException {
		int elementCount = initalMaxSize;
		
		for(int i = 0; i < elementCount; i++) {
			queue.enqueue("Element: " + i);
		}
		for(int i = 0; i < (elementCount + 1); i++) {
			queue.dequeue();
		}
	}
	
	@Test
	public void shouldNotUnderflow() throws OverflowException, UnderflowException {
		int elementCount = initalMaxSize;
		
		for(int i = 0; i < elementCount; i++) {
			queue.enqueue("Element: " + i);
		}
		for(int i = 0; i < elementCount; i++) {
			queue.dequeue();
		}
	}
	
	@Test
	public void shouldDoubleSize() throws OverflowException {
		assertEquals(initalMaxSize, queue.maxSize());
		for(int i = 0; i < 5; i++) {
			queue.enqueue("Element: " + i);
		}
		assertEquals(initalMaxSize * 2, queue.maxSize());
	}
	
	@Test
	public void shouldNotDoubleSize() throws OverflowException {
		assertEquals(initalMaxSize, queue.maxSize());
		for(int i = 0; i < 4; i++) {
			queue.enqueue("Element: " + i);
		}
		assertEquals(initalMaxSize, queue.maxSize());
	}
	
	@Test(expected=OverflowException.class)
	public void shouldOverflow() throws OverflowException {
		for(int i = 0; i < 9; i++) {
			queue.enqueue("Element: " + i);
		}
	}
	@Test
	public void shouldNotOverflow() throws OverflowException {
		for(int i = 0; i < 8; i++) {
			queue.enqueue("Element: " + i);
		}
	}

}
