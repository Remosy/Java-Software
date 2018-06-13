package btree;

import Trees.StrTable;

import java.util.ArrayList;

// BtreeNode - a simple btree implementation of a table that maps Strings to Strings.
// Eric McCreath - 2015


public class BtreeNode implements StrTable {

	static final int MAXKEYSPERNODE = 2; // must be even

	ArrayList<btree.KeyAndValue> keysandvalues; // This holds the elements (key and value mappings)
	                                      // for this node of the tree.  Note they will be 
	                                      // in ascending order.

	ArrayList<BtreeNode> children;  // These sit between the keyandvalues,
					// so the size of this list should be one
					// more than the size of the keysandvalues
					// list (except if the node is a leaf in
					// which case there will be no children).

	public BtreeNode() {
		keysandvalues = new ArrayList<btree.KeyAndValue>();
		children = new ArrayList<BtreeNode>();
	}

	
	// lookup - This method will look up the "key" in the table and return the 
	// value that that key maps to.  If the key is not in the table then a null is returned.
	@Override
	public String lookup(String key) {
		for (btree.KeyAndValue kv : keysandvalues) if (kv.key.equals(key)) return kv.value;
		if (children.size() > 0) {
			for (int i = 0; i <= keysandvalues.size(); i++) {
				if (i== keysandvalues.size() || 
						key.compareTo(keysandvalues.get(i).key) < 0) {
					return children.get(i).lookup(key);
				}
			}
		}
		return null;
	}

	@Override
	public void insert(String key, String name) {
		btree.SpitRes down = insertDown(key, name);
		if (down != null) {
			keysandvalues.clear();
			children.clear();
			keysandvalues.add(down.keyvalue);
			children.add(down.left);
			children.add(down.right);
		}
	}
	
    // insertDown - this recursively adds a new mapping to this node.  If the node needs to be 
	// split then a SpitRes (holding a KeyAndValue and left/right Nodes) is returned. Whereas if splitting
	// is not required then a null is return and one can assume to mapping was successfully added. 
	private btree.SpitRes insertDown(String key, String name) {
		// overwrite existing values if this node has a key which is the same as
		// "key"
		for (int i = 0; i < keysandvalues.size(); i++) {
			if (key.equals(keysandvalues.get(i).key)) {
				keysandvalues.get(i).value = name;
				return null;
			}
		}

		// add at this node (many result in a node with too many elements)
		boolean added = false;
		int i = 0;
		while (!added && i <= keysandvalues.size()) {
			if (i == keysandvalues.size()
					|| key.compareTo(keysandvalues.get(i).key) < 0) {
				if (children.size() == 0) { // it is a leaf
					keysandvalues.add(i, new btree.KeyAndValue(key, name));
				} else { // it is a inner node
					btree.SpitRes addres = null;
					addres = children.get(i).insertDown(key, name);
					if (addres != null) {
						keysandvalues.add(i, addres.keyvalue);
						children.remove(i);
						children.add(i, addres.left);
						children.add(i + 1, addres.right);
					}
				}
				added = true;
			}
			i++;
		}

		// split this node into two nodes if it has too many elements
		if (keysandvalues.size() > MAXKEYSPERNODE) { 
			btree.SpitRes res = new btree.SpitRes();
			res.left = new BtreeNode();
			res.right = new BtreeNode();
			res.keyvalue = keysandvalues.remove(MAXKEYSPERNODE / 2);
			for (int j = 0; j < MAXKEYSPERNODE / 2; j++) {
				res.left.keysandvalues.add(keysandvalues.remove(0));
			}
			for (int j = 0; j < MAXKEYSPERNODE / 2; j++) {
				res.right.keysandvalues.add(keysandvalues.remove(0));
			}
			if (!(children.size() == 0)) {
				for (int j = 0; j <= MAXKEYSPERNODE / 2; j++) {
					res.left.children.add(children.remove(0));
				}
				for (int j = 0; j <= MAXKEYSPERNODE / 2; j++) {
					res.right.children.add(children.remove(0));
				}
			}
			return res;
		} else {
			return null;
		}
	}

	public String showTree(int indent) {
		String res = "";
		if (children.size() == 0) {
			res += space(indent) + "[";
			for (int i = 0; i < keysandvalues.size(); i++) {
				res += (i == 0 ? "" : ",") + keysandvalues.get(i).show();
			}
			res += "]\n";
		} else {
			res += space(indent) + "[\n";
			res += children.get(0).showTree(indent + 1);
			for (int i = 0; i < keysandvalues.size(); i++) {
				res += space(indent) + keysandvalues.get(i).show() + "\n";
				res += children.get(i + 1).showTree(indent + 1);
			}
			res += space(indent) + "]\n";
		}
		return res;
	}

	private String space(int indent) {
		String res = "";
		for (int i = 0; i < indent * 3; i++)
			res += " ";
		return res;
	}
}
