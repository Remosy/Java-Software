
/*
 * Problem with the AVL implementation :
 * 
 * put your answer here
 * 
 * 
 */

public class NETree extends AVLTree {

	int value;
	AVLTree left, right;

	public NETree(int v, AVLTree l, AVLTree r) {
		value = v;
		this.left = l;
		this.right = r;
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
		if (res.bf() > 1) {
			NETree l = (NETree) res.left;
			if (l.bf() > 0) {
				NETree ll = (NETree) (l.left);
				res = new NETree(l.value, ll, new NETree(res.value, l.right, res.right));
			} else {
				NETree lr = (NETree) (l.right);
				res = new NETree(lr.value, new NETree(l.value, l.left, lr.left),
						new NETree(res.value, lr.right, res.right));
			}
		} else if (res.bf() < -1) {
			NETree r = (NETree) res.right;
			if (r.bf() < 0) {
				NETree rr = (NETree) (r.right);
				res = new NETree(r.value, new NETree(res.value, res.left, r.left), rr);
			} else {
				NETree rl = (NETree) (r.left);
				res = new NETree(rl.value, new NETree(res.value, res.left, rl.left), new NETree(
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
		return 1 + Math.max(left.h(), right.h());
	}
	
	@Override
	String show() {
		return (h()==0 ? value + "": value +  "[" + left.show() + "," + right.show() + "]");
	}

}
