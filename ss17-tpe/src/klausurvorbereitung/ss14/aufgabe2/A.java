package klausurvorbereitung.ss14.aufgabe2;

public class A {
	private int a;
	
	public A(int a) {this.a = a; }
	
	public int getA() {return this.a; }
	
	public int add(A a) { 
		return this.a + a.getA(); 
	}
	
	public int m() {return 1;}
	
	public int n() {return this.m(); }
	
	public String toString() { return "Ich bin ein A."; }

	public static void main(String[] args) {
		A o1 = new A(2);
		B o2 = new B(3);
		A o3 = new B(4);
		
		System.out.println(o1.m() + o2.m());
		System.out.println(o2.m() + o3.m());
		
		System.out.println(o2.n());
		
		System.out.println(C.print(o1));
		System.out.println(C.print(o2));
		System.out.println(C.print(o3));
		
		System.out.println(o1.add(o1));
		System.out.println(o1.add(o2));
		System.out.println(o2.add(o3));
		System.out.println(o2.add(o2));
	}
}

class B extends A {
	private int a;
	
	public B(int a) {super(a); }
	
	public int getA() {  
		return this.a; 
	}
	
	public int add (B b) { return this.getA() + b.getA(); }
	
	public int m() { return 2; }
	
	public String toString() {return "Ich bin ein B."; }
}

class C {
	
	public static String print(A a) { return "Parameter A: " + a.toString(); }
	
	public static String print(B b) { return "Parameter B: " + b.toString(); }
	
}
