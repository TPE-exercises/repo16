package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

public class MyInt implements Comparable<MyInt> {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	MyInt(int value) {
		this.setValue(value);
	}
	
	@Override
	public int compareTo(MyInt other) {
		//MyInt other = (MyInt)o;
		if(this.value == other.value) {
			return 0;
		} else if (this.value < other.value){
			return -1;
		} else {
			return 1;
		}
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.value);
	}
}
