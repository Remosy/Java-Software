
public class Demo {

	public static void main(String[] args) {
		AVLTree t = new ETree();
		t = t.insert(1);
		t = t.insert(2);
		t = t.insert(3);
		t = t.insert(4);
		System.out.println("Is 3 in the set? : " + t.isin(3));
		System.out.println("Is 5 in the set? : " + t.isin(5));
		System.out.println(t.show());
	}

}
