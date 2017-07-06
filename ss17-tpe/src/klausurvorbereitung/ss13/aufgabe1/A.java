package klausurvorbereitung.ss13.aufgabe1;

public class A {

	public A() {
		p();
	}
	
	public void m() {
		System.out.println("0");
		this.p();
	}
	
	public void m (A a) {
		System.out.println("1");
		this.p();
	}
	
	public void p() {
		System.out.println("11");
	}
	
	public static void main(String[] args) {
		A a = new A();
		System.out.println();
		B b = new B();
		System.out.println();
		
		a = b;
		System.out.println();
		a.m(a);
		System.out.println();
		System.out.println();
		a.m(b);
		System.out.println();
		b.m((A)b);
		System.out.println();
		b.m(b);
		System.out.println();
		a.m((B) a);
		System.out.println();
		a.m();
	}
}

class B extends A {
	public void m (A a) {
		System.out.println("2");
		((A)this).p();
	}
	
	public void m (B b) {
		System.out.println("3");
	}
	
	public void p() {
		System.out.println("22");
	}
}
