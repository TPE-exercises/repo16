package de.hsMannheim.ss17.tpe.martinDavid.uebung1;

public class MyBTree implements BTree {
	private int ordinal;
	private BTreeNode rootNode;
	
	MyBTree(int ordinal) {
		this.ordinal = ordinal;
	}
	
	@Override
	public boolean insert(Integer o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(String filename) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Integer o) {
		if (rootNode != null) {
			return rootNode.contains(o);
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
		return rootNode == null;
	}

	@Override
	public boolean addAll(BTree otherTree) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printInorder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printPostorder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printPreorder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printLevelorder() {
		// TODO Auto-generated method stub
		
	}

}
