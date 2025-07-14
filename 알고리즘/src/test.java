public class test {
	public static void main(String[] args) {
		new Child();
		System.out.println(Parent.total);
	}
}


class Parent {
	static int total = 0;
	int v = 1;

	public Parent() {

		total += (++v);
		System.out.println(total);
		show();
	}

	public void show() {
		System.out.println("부모 실행");
		total += total;
	}
}


class Child extends Parent {
	int v = 10;

	public Child() {
		v += 2;
		total += v++;
		show();
	}

	@Override
	public void show() {
		System.out.println("자식실행");
		total += total * 2;
	}
}