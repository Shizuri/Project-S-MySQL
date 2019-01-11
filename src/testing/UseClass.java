package testing;

public class UseClass {

	public static void main(String[] args) {
		System.out.println(DBtester.bob);
		DBtester.bob = "new value";
		System.out.println(DBtester.bob);
	}
}
