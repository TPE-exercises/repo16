package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

class Node {
	Object val; // contains nodes of any kind
	Node next;

	// >>>>>>>>>> constructors <<<<<<<<<<<<<<<

	public Node(Object val, Node n) {
		this.val = val;
		this.next = n;
	}

	public Node() {
		this.val = null;
		this.next = null;
	}

	// --------------- public methods

	public void setNode(Object val) {
		this.val = val;
	}

	public String toString() {
		return "" + val;
	}

	public Object getNode() {
		return val;
	}

	public void setNext(Node n) {
		this.next = n;
	}

	public Node getNext() {
		return next;
	}
}

// ////////////list class ////////////////////////////////////////////////

public class LinkedList {
	Node head;

	// >>>>>>>>>> constructors <<<<<<<<<<<<<<<

	public LinkedList() {
		head = null;
	}

	// --------------- public methods
	// --------------------------------------------

	public void addLast(Object val) {

		// inserts val at a new element at tail of the list
		// element is created inside method addFirst

		if (head == null)
			head = new Node(val, null);
		else {
			Node l = head;
			while (l.getNext() != null)
				l = l.getNext();

			Node n = new Node(val, null); // create a new element
			l.setNext(n); // link the new element
		}
	}

	public boolean isEmpty() {
		return head == null;
	}

	public Object removeFirst() {

		// removes first element of the list

		Node p = head;

		if (!isEmpty()) {
			head = head.next;
			return p.val;
		} else
			return null;
	}

	public Object getFirst() {

		// gets first element of the list

		if (!isEmpty())
			return head.val;
		else
			return null;
	}
	public Object getLast() {
		if(this.isEmpty())  {
			return null;
		}
		
		Node lastNode = head;
		while(lastNode.getNext() != null) {
			lastNode = lastNode.getNext();
		}
		return lastNode.val;
	}
	public Object removeLast() {
		if(this.isEmpty())  {
			return null;
		}
		if(head.getNext() == null) {
			head = null;
			return head.val;
		}
		Node previousLastNode = head;
		Node lastNode = previousLastNode.getNext();
		
		
		while(lastNode.getNext() != null) {
			previousLastNode = lastNode;
			lastNode = lastNode.getNext();
		}
		previousLastNode.setNext(null);
		
		return lastNode.val;
	}

	public int printList() { // returns no. of printed elements

		Node p = head;
		int cnt = 0;
		int noe = this.size();
		
		if (noe == 0) return 0; // nothing to print

		while (cnt < noe - 1) { // print all elements but last
			System.out.print(p + ", ");
			p = p.getNext();
			cnt++;
		}
	
		System.out.println(p); // print last element

		return noe;

	}

	public int size() {

		Node p = head;
		int cnt = 0;

		while (p != null) {
			cnt++;
			p = p.getNext();
		}

		return cnt;

	}
	
}

// ////////////end of list class ///////////////////////////////////////
