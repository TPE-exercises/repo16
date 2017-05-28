package de.hsMannheim.ss17.tpe.martinDavid.uebung4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NumberConsumerTest {
	static private final int MAX_SIZE = 1000;
	private ThreadSafeRingBuffer ringBuffer;
	private RandomNumberProducer producer;
	
	@Before
	public void makeFullRingBuffer() throws InterruptedException {
		ringBuffer = new ThreadSafeRingBuffer(MAX_SIZE);
		producer = new RandomNumberProducer(ringBuffer, 0, 0);
		producer.start();
		ringBuffer.waitUntilFull();
		producer.interrupt();
		producer.join();
		
		assertTrue(ringBuffer.isFull());
	}

	@Test
	public void oneNonBlockingConsumer() throws InterruptedException {
		NumberConsumer consumer = new NumberConsumer(ringBuffer, 0);
		consumer.start();
		
		InterruptTimer timer = new InterruptTimer(new Thread[]{consumer}, 1);
		timer.start();
		timer.join();
		
		assertEquals(MAX_SIZE - ringBuffer.size(), consumer.consumedNumbers.size());
	}
	@Test
	public void twoNonBlockingConsumer() throws InterruptedException {
		NumberConsumer firstConsumer = new NumberConsumer(ringBuffer, 0);
		firstConsumer.start();
		NumberConsumer secondConsumer = new NumberConsumer(ringBuffer, 0);
		secondConsumer.start();
		
		InterruptTimer timer = new InterruptTimer(new Thread[]{firstConsumer, secondConsumer}, 1);
		timer.start();
		timer.join();
		
		assertEquals(
				MAX_SIZE - ringBuffer.size(), 
				firstConsumer.consumedNumbers.size() + secondConsumer.consumedNumbers.size()
			);
	}
	@Test
	public void oneBlockingConsumer() throws InterruptedException {
		NumberConsumer consumer = new NumberConsumer(ringBuffer, 0);
		consumer.start();
		ringBuffer.waitUntilEmpty();
		
		InterruptTimer timer = new InterruptTimer(new Thread[]{consumer}, 1);
		timer.start();
		timer.join();
		
		assertEquals(MAX_SIZE - ringBuffer.size(), consumer.consumedNumbers.size());
	}
	
	@Test
	public void twoBlockingConsumer() throws InterruptedException {
		NumberConsumer firstConsumer = new NumberConsumer(ringBuffer, 0);
		firstConsumer.start();
		NumberConsumer secondConsumer = new NumberConsumer(ringBuffer, 0);
		secondConsumer.start();
		
		ringBuffer.waitUntilEmpty();
		
		InterruptTimer timer = new InterruptTimer(new Thread[]{firstConsumer, secondConsumer}, 1);
		timer.start();
		timer.join();
		
		assertEquals(
				MAX_SIZE - ringBuffer.size(), 
				firstConsumer.consumedNumbers.size() + secondConsumer.consumedNumbers.size()
			);
	}

}
