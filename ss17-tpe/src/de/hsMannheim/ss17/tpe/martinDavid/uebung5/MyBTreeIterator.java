package de.hsMannheim.ss17.tpe.martinDavid.uebung5;

import java.util.Iterator;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.LinkedList;

public class MyBTreeIterator implements Iterator<Integer> {
	private LinkedList queue = new LinkedList();
	private BTreeNode currentNode;
	private int currentElementIndex = 0;
	public MyBTreeIterator(BTreeNode node) {
		if(node != null && !node.isEmpty()) {
			queue.addLast(node);
		}
	}
	@Override
	public boolean hasNext() {
		return currentNode != null || !queue.isEmpty();
	}

	@Override
	public Integer next() {
		if (currentNode == null) {
			currentNode = (BTreeNode)queue.removeFirst();
			for(BTreeNode child: currentNode.getChildren()) {
				if(child == null) {
					break;
				}
				if(!child.isEmpty()) {
					queue.addLast(child);
				}
			}
		}
		Integer[] elements = currentNode.getElements();
		if(currentElementIndex < elements.length && elements[currentElementIndex] != null) {
			int index = currentElementIndex;
			currentElementIndex++;
			//check if the currentNode has some elements left
			if (currentElementIndex >= (elements.length -1) || elements[currentElementIndex] == null) {
				//reset state
				currentNode = null;
				currentElementIndex = 0;
			}
			return elements[index];
		}
		return null;
	}

}
