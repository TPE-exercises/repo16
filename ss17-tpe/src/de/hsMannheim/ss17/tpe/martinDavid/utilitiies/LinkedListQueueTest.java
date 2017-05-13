package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public class LinkedListQueueTest extends QueueTest {
	
	public Queue createQueue(int maxSize) {
		return new LinkedListQueue(maxSize);
	}

}
