package de.hsMannheim.ss17.tpe.martinDavid.uebung4;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.queue.RingBuffer;

public class RandomNumberProducerTest {

	@Test
	public void oneNonBlockingProducerTest() throws InterruptedException {
		ThreadSafeRingBuffer ringBuffer = new ThreadSafeRingBuffer(1000);
		RandomNumberProducer producer = new RandomNumberProducer(ringBuffer, 0, 0);
		producer.start();
		
		InterruptTimer timer = new InterruptTimer(new Thread[]{producer}, 2);
		timer.start();
		timer.join();
		
		assertEquals(ringBuffer.size(), producer.generatedNumbers.size());
	}
	
	@Test
	public void twoNonBockingProducerTest() throws InterruptedException {
		ThreadSafeRingBuffer ringBuffer = new ThreadSafeRingBuffer(10000);
		RandomNumberProducer firstProducer = new RandomNumberProducer(ringBuffer, 0, 0);
		firstProducer.start();
		RandomNumberProducer secondProducer = new RandomNumberProducer(ringBuffer, 0, 0);
		secondProducer.start();
		
		InterruptTimer timer = new InterruptTimer(new Thread[]{firstProducer, secondProducer}, 1);
		timer.start();
		timer.join();
		
		assertEquals(ringBuffer.size(), firstProducer.generatedNumbers.size() + secondProducer.generatedNumbers.size());
	}
	
	@Test
	public void oneBlockingProducerTest() throws InterruptedException {
		ThreadSafeRingBuffer ringBuffer = new ThreadSafeRingBuffer(50);
		RandomNumberProducer producer = new RandomNumberProducer(ringBuffer, 0, 0);
		producer.start();
		
		ringBuffer.waitUntilFull();
		
		InterruptTimer timer = new InterruptTimer(new Thread[]{producer}, 2);
		timer.start();
		timer.join();
		
		assertTrue(ringBuffer.isFull());
		assertEquals(ringBuffer.size(), producer.generatedNumbers.size());
	}
	
	@Test
	public void twoBockingProducerTest() throws InterruptedException {
		ThreadSafeRingBuffer ringBuffer = new ThreadSafeRingBuffer(100);
		RandomNumberProducer firstProducer = new RandomNumberProducer(ringBuffer, 0, 0);
		firstProducer.start();
		RandomNumberProducer secondProducer = new RandomNumberProducer(ringBuffer, 0, 0);
		secondProducer.start();
		
		ringBuffer.waitUntilFull();
		
		InterruptTimer timer = new InterruptTimer(new Thread[]{firstProducer, secondProducer}, 1);
		timer.start();
		timer.join();
		
		assertTrue(ringBuffer.isFull());
		assertEquals(ringBuffer.size(), firstProducer.generatedNumbers.size() + secondProducer.generatedNumbers.size());
	}

}
