
public class ETree extends AVLTree {

	public ETree() {
		
	}
	
	@Override
	public AVLTree insert(int v) {
		return new NETree(v, this,this);
	}

	@Override
	public boolean isin(int v) {
		
		return false;
	}

	@Override
	int bf() {
		return 0;
	}
	
	@Override
	int h() {
		return -1;
	}

	@Override
	String show() {
		return "-";
	}

}
