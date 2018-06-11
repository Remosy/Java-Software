
/*
 * Problem with the AVL implementation :
 * 
 * put your answer here
 * 
 * 
 */

public class NETree extends AVLTree {

	int value;
	int h;
	AVLTree left, right;

	public NETree(int v, AVLTree l, AVLTree r) {
		value = v;
		this.left = l;
		this.right = r;
		h = 1 + Math.max(left.h(), right.h());
	}

	@Override
	public AVLTree insert(int v) {
		NETree res = null;
		if (v == value) {
			return this;
		} else if (v < value) {
			res = new NETree(value, left.insert(v), right);
		} else {
			res = new NETree(value, left, right.insert(v));
		}
		if (bf() > 1) {
			NETree l = (NETree) left;
			if (l.bf() > 0) {
				NETree ll = (NETree) (l.left);
				res = new NETree(l.value, ll, new NETree(value, l.right, right));
			} else {
				NETree lr = (NETree) (l.right);
				res = new NETree(lr.value, new NETree(l.value, l.left, lr.left),
						new NETree(value, lr.right, right));
			}
		} else if (bf() < -1) {
			NETree r = (NETree) right;
			if (r.bf() < 0) {
				NETree rr = (NETree) (r.right);
				res = new NETree(r.value, new NETree(value, left, r.left), rr);
			} else {
				NETree rl = (NETree) (r.left);
				res = new NETree(rl.value, new NETree(value, left, rl.left), new NETree(
						r.value, rl.right, r.right));
			}
		}
		return res;
	}

	@Override
	public boolean isin(int v) {
		return (v == value ? true : (v < value ? left.isin(v) : right.isin(v)));
	}

	@Override
	int bf() {
		return left.h() - right.h();
	}

	@Override
	int h() {
		return h;
	}
	
	@Override
	String show() {
		return (h()==0 ? value + "": value +  "[" + left.show() + "," + right.show() + "]");
	}

}
