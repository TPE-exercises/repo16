package de.hsMannheim.ss17.tpe.martinDavid.uebung5;

import static gdi.MakeItSimple.isEndOfInputFile;
import static gdi.MakeItSimple.openInputFile;
import static gdi.MakeItSimple.println;
import static gdi.MakeItSimple.readInt;

import java.sql.Array;
import java.util.Iterator;
import java.util.List;

public class MyBTree<E extends Comparable<E>> implements BTree<E>, Iterable<E> {
	private final int ordinal;
	private BTreeNode<E> rootNode;
	
	MyBTree(int ordinal) {
		this.ordinal = ordinal;
	}
	
	@Override
	public boolean insert(E element) {		
		if(rootNode == null) {
			rootNode = new BTreeNode<E>(ordinal);
		}
		
		
		boolean successful = rootNode.insert(element);
		if(rootNode.getParent() != null) {
			rootNode = rootNode.getParent();
		}
		return successful;
	}
	
	/**
	 * inserts all elements to the tree
	 * @param elements
	 * @return
	 */
	public boolean insertAll(E[] elements) {
		for(E element: elements) {
			if(!insert(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean insert(String filename) {
		/*
		Object file;
		
		try {
			file = openInputFile(filename);
			
			while(!isEndOfInputFile(file)) {
				E elementToInsert = readInt(file);
				insert(elementToInsert);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true; */
		//not possible with generics
		return false;
	}

	@Override
	public boolean contains(E element) {
		if (rootNode != null) {
			return rootNode.contains(element);
		}
		return false;
	}

	@Override
	public int size() {
		if (rootNode != null) {
			return rootNode.size();
		}
		return 0;
	}

	@Override
	public int height() {
		if (rootNode != null) {
			return rootNode.height();
		}
		return 0;
	}

	@Override
	public E getMax() {
		if (rootNode != null) {
			return rootNode.getMax();
		}
		return null;
	}

	@Override
	public E getMin() {
		if (rootNode != null) {
			return rootNode.getMin();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		if(rootNode == null) {
			return true;
		} else {
			return rootNode.isEmpty();
		}
	}
	
	@Override
	public E[] getAllElements() {
		if(rootNode != null) {
			return rootNode.getAllElements();
		}
		
		return (E[]) new Comparable[0];
	}

	@Override
	public boolean addAll(BTree<E> otherTree) {
		
		boolean allInsertionsSuccessfull = true;
		
		if(otherTree != null) {
			for(E elementToInsert : otherTree.getAllElements()) {
				if(!insert(elementToInsert)) {
					//could not insert this element
					allInsertionsSuccessfull = false;
				}
			}
		} 
		
		return  allInsertionsSuccessfull;
	}
	
	@Override
	public BTree<E> clone() {
		MyBTree<E> tree = new MyBTree<E>(ordinal);
		tree.rootNode = rootNode.deepClone();
		
		return tree;
	}

	@Override
	public void printInorder() {
		if(rootNode != null) {
			rootNode.printInorder();
		}
		
		println();
	}

	@Override
	public void printPostorder() {
		if(rootNode != null) {
			rootNode.printPostorder();
		}
		
		println();
	}

	@Override
	public void printPreorder() {
		if(rootNode != null) {
			rootNode.printPreorder();
		}
		
		println();		
	}

	@Override
	public void printLevelorder() {
		if(rootNode != null) {
			int height = rootNode.height();
			for(int i = 0; i < height; i++) {
				rootNode.printLevel(i);
				
				System.out.println("");
			}
		}
		
		println();
	}

	@Override
	public Iterator<E> iterator() {
		return new MyBTreeIterator(rootNode);
	}

	@Override
	public boolean delete(E object) {
		MyBTree<E> newTree = new MyBTree<E>(ordinal);
		
		boolean deleted = false;
		E[] allElements = getAllElements();
		for(int i = 0; i < allElements.length; i++) {
			if(!deleted && allElements[i] == object) {
				deleted = true;
			} else {
				newTree.insert(allElements[i]);
			}
		}
		
		this.rootNode = newTree.rootNode;
		
		return deleted;
	}

}
