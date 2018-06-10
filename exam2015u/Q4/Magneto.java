import java.util.ArrayList;

// Magneto - a simple ??????? implementation of a table that maps Strings to Strings.
// Eric McCreath - 2015


public class Magneto implements StrTable {

	static final int MAXKEYSPERNODE = 2; // must be even

	ArrayList<KeyAndValue> keysandvalues; 
	ArrayList<Magneto> twoface;  

	public Magneto() {
		keysandvalues = new ArrayList<KeyAndValue>();
		twoface = new ArrayList<Magneto>();
	}

	
	// lookup - This method will look up the "key" in the table and return the 
	// value that the key maps to.  If the key is not in the table then a null is returned.
	@Override
	public String lookup(String key) {
		// State the data structure that is used to store the table in this comment. 
		//
		//
		
        // add your code here to implement the lookup method.
		

		return "Not Implemented!";
	}

	@Override
	public void insert(String key, String name) {
		SpitRes down = joker(key, name);
		if (down != null) {
			keysandvalues.clear();
			twoface.clear();
			keysandvalues.add(down.keyvalue);
			twoface.add(down.left);
			twoface.add(down.right);
		}
	}
	
   
	private SpitRes joker(String key, String name) {
	
		for (int i = 0; i < keysandvalues.size(); i++) {
			if (key.equals(keysandvalues.get(i).key)) {
				keysandvalues.get(i).value = name;
				return null;
			}
		}

		boolean added = false;
		int i = 0;
		while (!added && i <= keysandvalues.size()) {
			if (i == keysandvalues.size()
					|| key.compareTo(keysandvalues.get(i).key) < 0) {
				if (twoface.size() == 0) { // it is a leaf
					keysandvalues.add(i, new KeyAndValue(key, name));
				} else { // it is a inner node
					SpitRes addres = null;
					addres = twoface.get(i).joker(key, name);
					if (addres != null) {
						keysandvalues.add(i, addres.keyvalue);
						twoface.remove(i);
						twoface.add(i, addres.left);
						twoface.add(i + 1, addres.right);
					}
				}
				added = true;
			}
			i++;
		}

		
		if (keysandvalues.size() > MAXKEYSPERNODE) { 
			SpitRes res = new SpitRes();
			res.left = new Magneto();
			res.right = new Magneto();
			res.keyvalue = keysandvalues.remove(MAXKEYSPERNODE / 2);
			for (int j = 0; j < MAXKEYSPERNODE / 2; j++) {
				res.left.keysandvalues.add(keysandvalues.remove(0));
			}
			for (int j = 0; j < MAXKEYSPERNODE / 2; j++) {
				res.right.keysandvalues.add(keysandvalues.remove(0));
			}
			if (!(twoface.size() == 0)) {
				for (int j = 0; j <= MAXKEYSPERNODE / 2; j++) {
					res.left.twoface.add(twoface.remove(0));
				}
				for (int j = 0; j <= MAXKEYSPERNODE / 2; j++) {
					res.right.twoface.add(twoface.remove(0));
				}
			}
			return res;
		} else {
			return null;
		}
	}

	public String showTree(int indent) {
		String res = "";
		if (twoface.size() == 0) {
			res += space(indent) + "[";
			for (int i = 0; i < keysandvalues.size(); i++) {
				res += (i == 0 ? "" : ",") + keysandvalues.get(i).show();
			}
			res += "]\n";
		} else {
			res += space(indent) + "[\n";
			res += twoface.get(0).showTree(indent + 1);
			for (int i = 0; i < keysandvalues.size(); i++) {
				res += space(indent) + keysandvalues.get(i).show() + "\n";
				res += twoface.get(i + 1).showTree(indent + 1);
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
