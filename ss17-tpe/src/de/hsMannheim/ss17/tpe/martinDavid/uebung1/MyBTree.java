package de.hsMannheim.ss17.tpe.martinDavid.uebung1;

import static gdi.MakeItSimple.*;

public class MyBTree implements BTree {
	private final int ordinal;
	private BTreeNode rootNode;
	
	MyBTree(int ordinal) {
		this.ordinal = ordinal;
	}
	
	@Override
	public boolean insert(Integer element) {		
		if(rootNode == null) {
			rootNode = new BTreeNode(ordinal);
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
	public boolean insertAll(Integer[] elements) {
		for(Integer element: elements) {
			if(!insert(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean insert(String filename) {
		Object file;
		
		try {
			file = openInputFile(filename);
			
			while(!isEndOfInputFile(file)) {
				int elementToInsert = readInt(file);
				insert(elementToInsert);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean contains(Integer element) {
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
	public Integer getMax() {
		if (rootNode != null) {
			return rootNode.getMax();
		}
		return null;
	}

	@Override
	public Integer getMin() {
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
	public Integer[] getAllElements() {
		if(rootNode != null) {
			return rootNode.getAllElements();
		}
		
		return new Integer[0];
	}

	@Override
	public boolean addAll(BTree otherTree) {
		
		boolean allInsertionsSuccessfull = true;
		
		if(otherTree != null) {
			for(int elementToInsert : otherTree.getAllElements()) {
				if(!insert(elementToInsert)) {
					//could not insert this element
					allInsertionsSuccessfull = false;
				}
			}
		} 
		
		return  allInsertionsSuccessfull;
	}
	
	@Override
	public BTree clone() {
		MyBTree tree = new MyBTree(ordinal);
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

}
