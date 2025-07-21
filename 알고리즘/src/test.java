public class test {
	public static void main(String[] args) {
		B b1 = new B();
		A b2 = new B();
		System.out.println(b1.a + b2.a);
		System.out.println(b2.a);
	}
}


class A {
	int a = 10;

	public A() {
		System.out.print("가");
	}

	public A(int x) {
		System.out.print("나");
	}
}


class B extends A {
	int a = 20;

	public B() {
		System.out.print("다");
	}

	public B(int x) {
		System.out.print("라");
	}
}