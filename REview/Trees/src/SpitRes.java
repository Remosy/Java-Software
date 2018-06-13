package btree;

public class SpitRes {

btree.KeyAndValue keyvalue;
btree.BtreeNode left;
btree.BtreeNode right;
	
    public SpitRes() {
    	
    }

	public SpitRes(btree.KeyAndValue keyvalue, btree.BtreeNode left, btree.BtreeNode right) {
		this.keyvalue = keyvalue;
		this.left = left;
		this.right = right;
	}

}
