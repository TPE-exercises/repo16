package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public class RingBufferQueueTest extends QueueTest {

	@Override
	public Queue createQueue(int maxSize) {
		// TODO Auto-generated method stub
		return new RingBufferQueue(maxSize);
	}
}
