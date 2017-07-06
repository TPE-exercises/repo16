package klausurvorbereitung.ss14.aufgabe1;

public class Parameteruebergabe {

	private String a;
	private String b;
	
	void swap(String a, String b) {
		String temp = a;
		a = b;
		b = temp;
	}
	
	void testSwap() {
		a = "AB";
		b = "CD";
		System.out.println(a.hashCode());
		swap(a,b);
		System.out.println(a + " " + b);
	}
	
	public static void main(String[] args) {
		new Parameteruebergabe().testSwap();
	}
}
