import java.util.ArrayList;
import java.util.Collections;
//MultExp - an expression that is a list of elements that are multiplied together
//Eric McCreath 2016

public class MultExp extends Exp {

	public ArrayList<Exp> elements;

	public MultExp() {
		elements = new ArrayList<Exp>();
	}

	public MultExp(Exp... e) {
		elements = new ArrayList<Exp>();
		for (Exp i : e)
			elements.add(i);
	}


	public void append(Exp e) {
		elements.add(e);
	}

	public void append(MultExp me) {
		elements.addAll(me.elements);
	}

	@Override
	String show() {
		if (elements.size() == 0)
			return "(1)";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < elements.size(); i++) {
			sb.append(elements.get(i).show()
					+ (i < elements.size() - 1 ? " * " : ""));
		}
		return (elements.size() <= 1 ? sb.toString() : "(" + sb.toString()
				+ ")");
	}

	@Override
	public int compareTo(Exp o) {
		if (o instanceof MultExp) {
			if (elements.size() != ((MultExp) o).elements.size())
				return elements.size() - ((MultExp) o).elements.size();
			for (int i = 0; i < Math.min(elements.size(),
					((MultExp) o).elements.size()); i++) {
				int c = elements.get(i)
						.compareTo(((MultExp) o).elements.get(i));
				if (c > 0 || c < 0)
					return c;
			}
			return 0;
		} else if (o instanceof SumExp)
			return 1;
		else if (o instanceof LitExp)
			return 1;
		else if (o instanceof VarExp)
			return 1;
		else
			return -1;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MultExp) {
			return elements.equals(((MultExp) o).elements);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return elements.hashCode();
	}

	@Override
	int countOperations() {
		int sum = Math.max(0, elements.size() - 1);
		for (Exp e : elements) sum += e.countOperations();
		return sum;
	}
	
	@Override
	Exp replace(Exp subexp, VarExp var) {
		if (subexp.equals(this)) {
			return var; 
		} else {
			MultExp res = new MultExp();
			for (Exp e : elements) {
				res.append(e.replace(subexp, var));
			}
			return res;
		}
	}
	
}
