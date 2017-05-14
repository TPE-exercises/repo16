package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public class LinkedListStack implements Stack {
	
	private int maxSize;
	private LinkedList list = new LinkedList();
	/**
	 * the max size of the stack with which it was created
	 */
	private final int initalMaxSize;
			
	LinkedListStack() {
		this(4);
	}
	LinkedListStack(int maxSize) {
		this.initalMaxSize = maxSize;
		this.maxSize(maxSize);
	}
	@Override
	public void push(Object element) throws OverflowException {
		if(this.size() == this.initalMaxSize) {
			this.pushAndDoubleSizeIfNeeded(element);
		} else {
			this.pushOrThrow(element);
		}
	}
	/**
	 * push the element on the stack. if it exceeds the max size it doubles the max size
	 * @param element element to push on top of the stack
	 */
	private void pushAndDoubleSizeIfNeeded(Object element) {
		try {
			this.pushOrThrow(element);
		} catch(OverflowException overflowException) {
			//TODO ich verstehe aus der Aufgabenstellung, dass diese Exception auch bis zu push durchgereicht werden soll und nicht nur hier abgefangen werden soll.
			
			this.maxSize(this.maxSize() * 2);
			this.pushAndDoubleSizeIfNeeded(overflowException.getElementCausedOverflow());
		}
	}
	/**
	 * push the element on the stack. if it exceeds the max size it throws a OverflowException
	 * @param element element to push on top of the stack
	 */
	private void pushOrThrow(Object element) throws OverflowException {
		if(this.size() >= this.maxSize()) {
			throw new OverflowException("This element would exceede the maximum size of the Stack", element);
		}
		this.list.addLast(element);
	}

	@Override
	public Object pop() throws UnderflowException {
		if(this.isEmpty()) {
			throw new UnderflowException("The Stack is empty. Can not pop");
		}
		return this.list.removeLast();
	}

	@Override
	public int size() {
		return this.list.size();
	}
	/**
	 * sets the max size to the given value.
	 * @param maxSize
	 */
	private void maxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	@Override
	public int maxSize() {
		return this.maxSize;
	}
	
}
