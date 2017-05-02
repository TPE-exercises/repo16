package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

public class MyString implements Comparable<MyString> {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	MyString(String value) {
		this.setValue(value);
	}
	
	@Override
	public int compareTo(MyString other) {
		//MyString other = (MyString)o;
		return this.value.compareTo(other.value);
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
