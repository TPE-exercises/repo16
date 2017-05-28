package de.hsMannheim.ss17.tpe.martinDavid.uebung4;
import static gdi.MakeItSimple.*;

public class Aufgabe3 {
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Enter a timer timeout in minutes: ");
		int minutes = readInt(); 
		int seconds = minutes * 60;
		 
		
		//System.out.println("Enter a timer timeout in seconds: ");
		//int seconds = readInt();
		
		
		ThreadSafeRingBuffer ringBuffer = new ThreadSafeRingBuffer(10);
		
		RandomNumberProducer[] producers = new RandomNumberProducer[]{
				new RandomNumberProducer(ringBuffer, 0, 250),
				new RandomNumberProducer(ringBuffer, 1, 500),
				new RandomNumberProducer(ringBuffer, 2, 1000),
		};
		
		NumberConsumer[] consumers = new NumberConsumer[]{
				new NumberConsumer(ringBuffer, 200),
				new NumberConsumer(ringBuffer, 1000),
		};
		
		//@tutor does a simple method to concatenate two generic arrays exists, not including the collections framework?
		Thread[] threads = new Thread[]{
				producers[0], producers[1], producers[2],
				consumers[0], consumers[1],
		};
		System.out.println("starting producer and consumer...");
		for(Thread thread: threads) {
			thread.start();
		}
		
		long miliseconds = seconds * 1000; 
		InterruptTimer timer = new InterruptTimer(producers, miliseconds);
		timer.start();
		System.out.println(seconds + "s timer started... ");
		timer.join();
		System.out.println("timer did timeout.");
		System.out.println("waiting until ring buffer is empty...");
		ringBuffer.waitUntilEmpty();
		System.out.println("finished.");
		System.out.println("Interupt consumer... ");
		for(NumberConsumer consumer: consumers) {
			consumer.interrupt();
		}
		for(NumberConsumer consumer: consumers) {
			consumer.join();
		}
		System.out.println("finished.");
	}
}
