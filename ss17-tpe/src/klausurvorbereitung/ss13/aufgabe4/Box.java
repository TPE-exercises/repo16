package klausurvorbereitung.ss13.aufgabe4;

public class Box <E extends Number> {
	
	private E element;
	
	E get() {
		return element;
	}

	void put(E element) {
		this.element = element;
	}
	
	public static void main(String[] args) {
		Box<? extends Number> box = new Box<Number>();
		
		Box<Integer> integerBox = new Box<>();
		box = integerBox;
	}
}
