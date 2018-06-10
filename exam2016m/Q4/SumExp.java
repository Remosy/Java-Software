import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// SumExp - an expression that is a list of elements that are added together
// Eric McCreath 2016

public class SumExp extends Exp {

	public ArrayList<Exp> elements;

	public SumExp() {
		elements = new ArrayList<Exp>();
	}

	public SumExp(Exp... e) {
		elements = new ArrayList<Exp>();
		for (Exp i : e)
			elements.add(i);
	}



	// add an expression to the end of this SumExp
	public void append(Exp e) {
		elements.add(e);
	}

	@Override
	String show() {
		if (elements.size() == 0)
			return "0";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < elements.size(); i++) {
			sb.append(elements.get(i).show()
					+ (i < elements.size() - 1 ? " + " : ""));
		}
		return (elements.size() <= 1 ? sb.toString() : "(" + sb.toString()
				+ ")");
	}

	@Override
	public int compareTo(Exp o) {
		if (o instanceof SumExp) {
			for (int i = 0; i < Math.min(elements.size(),
					((SumExp) o).elements.size()); i++) {
				int c = elements.get(i).compareTo(((SumExp) o).elements.get(i));
				if (c > 0 || c < 0)
					return c;
			}
			return (elements.size() < ((SumExp) o).elements.size() ? -1 : 1);
		} else if (o instanceof LitExp)
			return 1;
		else if (o instanceof VarExp)
			return 1;
		else
			return -1;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof SumExp) {
			return elements.equals(((SumExp) o).elements);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return elements.hashCode();
	}

	// this multiplies this SumExp with the provided SumExp producing a new
	// SumExp
	public SumExp multiply(SumExp se) {
		SumExp res = new SumExp();
		for (Exp e1 : elements) {
			for (Exp e2 : se.elements) {
				MultExp m = new MultExp();
				m.append(((MultExp) e1));
				m.append(((MultExp) e2));
				res.append(m);
			}
		}
		return res;
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
			SumExp res = new SumExp();
			for (Exp e : elements) {
				res.append(e.replace(subexp, var));
			}
			return res;
		}
	}
}
