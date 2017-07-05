package klausurvorbereitung.ss07.aufgabe5;

import java.util.Iterator;

public class Queuee<E> implements Iterable<E> {
	
	public void put(E object) {
		
	}
	
	public E leave() {
		return null;
	}
	
	public boolean isEmpty() {
		return true;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

class Main {
	public static void main(String[] args) {
		Queuee<Integer> que1 = new Queuee<>();
		Queuee<String> que2 = new Queuee<>();
	}
}

class MyIterator<E> implements Iterator<E> {

	private Queuee<E> queue;
	
	public MyIterator(Queuee<E> queuee) {
		queue = queuee;
	}
	
	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}

	@Override
	public E next() {
		return queue.leave();
	}
	
}
