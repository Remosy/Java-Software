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

	// This method returns a simplified version of the expression.
	// This simplified version is a sum of products. The produces are a list
	// of variables possibly beginning with a constant literal. The elements of
	// both the products and the sum are sorted (using the Collections sort
	// method and the ordering provided).
	SumExp simplify() {
		// Apply simplify to each element concatenating the result into a new
		// SumExp.

		// Combine the MultExp's which are made up of the same variables.
		// Hint - use a HashMap to construct a mapping from the variables (as
		// the key) to the constant multiplication value. Note that extractVars
		// and extractLitConstant give you the variables and constant
		// multiplication value of a MultExp respectively.
		// Once the hash map is created it is turned back into a SumExp.
		SumExp res = new SumExp();
		// if there is no elements in the resulting SumExp then add a "0"
		// sort the elements of the SumExp and return the result
		return res;
	}

	// extractVars - create a MultExp from the provided MultExp which
	// only includes the Variable(s) if there are no variables then an empty MultExp is
	// returned.
	public static MultExp extractVars(MultExp m) {
		MultExp res = new MultExp();
		for (Exp e : m.elements) {
			if (e instanceof VarExp)
				res.append(e);
		}
		return res;
	}

	// extractLitConstant - multiply the literal constants within an expression
	// together returning the result
	public static int extractLitConstant(MultExp m) {
		int con = 1;
		for (Exp e : m.elements) {
			if (e instanceof LitExp)
				con *= ((LitExp) e).value();
		}
		return con;
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
}
