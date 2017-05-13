package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;
import static gdi.MakeItSimple.*;

public class QueueMenu {
	static Queue[] queues;
	static int queueId = 0;
	public static void main(String[] args) {
		queues = new Queue[]{
				new RingBufferQueue(),
				new LinkedListQueue(),
		};
		
		while(true) {
			Queue queue = queues[queueId];
			System.out.print("Current Queue implementation: ");
			if(queueId == 0) {
				System.out.println("RingBufferQueue()");
			} else if(queueId == 1) {
				System.out.println("LinkedListQueue()");
			}
			printDescription(queue);
			printMenu();
			int selectOption = readInt();
			readLine();
			try{
				switch(selectOption) {
				case 1:
					System.out.println("Enter a string:");
					String elementToEnqueue = readLine();
					queue.enqueue(elementToEnqueue);
					break;
				case 2:
					String dequeuedElement = (String)queue.dequeue();
					System.out.println("Dequeued element:" + dequeuedElement);
					break;
				case 3:
					queueId = 0;
					break;
				case 4:
					queueId = 1;
					break;
				default:
					System.out.println("Unkown option");
					break;
				}
			} catch(OverflowException | UnderflowException exception) {
				System.err.println(exception.getMessage());
				readLine();
			}
			
		}
	}
	
	static void printMenu() {
		System.out.println("1: queue.enqueue(String);");
		System.out.println("2: queue.dequeue();");
		System.out.println("3: switch to RingBufferQueue() implementation");
		System.out.println("3: switch to LinkedListQueue() implementation");
	}
	
	static void printDescription(Queue queue) {
		System.out.println("queue.isEmpty() = " + queue.isEmpty());
		System.out.println("queue.size() = " + queue.size());
		System.out.println("queue.maxSize() = " + queue.maxSize());
	}
}
