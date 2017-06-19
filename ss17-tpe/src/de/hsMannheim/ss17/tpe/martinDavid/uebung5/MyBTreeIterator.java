package de.hsMannheim.ss17.tpe.martinDavid.uebung5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyBTreeIterator<E extends Comparable<E>> implements Iterator<E> {
	private List<BTreeNode<E>> queue = new ArrayList<>();
	private BTreeNode<E> currentNode;
	private int currentElementIndex = 0;
	public MyBTreeIterator(BTreeNode<E> node) {
		if(node != null && !node.isEmpty()) {
			queue.add(queue.size() - 1, node);
		}
	}
	@Override
	public boolean hasNext() {
		return currentNode != null || !queue.isEmpty();
	}

	@Override
	public E next() {
		if (currentNode == null) {
			currentNode = (BTreeNode<E>) queue.remove(0);
			for(BTreeNode<E> child: currentNode.getChildren()) {
				if(child == null) {
					break;
				}
				if(!child.isEmpty()) {
					queue.add(queue.size() - 1, child);
				}
			}
		}
		E[] elements = currentNode.getElements();
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
