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

	
	// simplify - this returns a simplified expression which is in the sum of product form. 
	SumExp simplify() {

		// expand out the expression by multiplying the terms and accumulating the 
		// result into accumulate.
		SumExp accumulate = new SumExp(new MultExp(new LitExp(1)));
		for (Exp e : elements) {
			SumExp se = e.simplify();
			SumExp multacc = accumulate.multiply(se);
			accumulate = new SumExp();
			for (Exp e2 : multacc.elements) {
				MultExp me = (MultExp) e2;
				MultExp vars = SumExp.extractVars(me);
				Collections.sort(vars.elements);
				int c = SumExp.extractLitConstant(me);
				MultExp meres = new MultExp();
				if (c == 0) {
					meres.append(new LitExp(0));
				} else if (c == 1) {
					meres.append(vars);
				} else {
					meres.append(new LitExp(c));
					meres.append(vars);
				}
				accumulate.append(meres);
			}
		}
		
		// We need to apply simplify to the expression once expanded. Noting we don't do
		// this with expressions that have one element in them as it would create infinite recursion.
		return (accumulate.elements.size() > 1? accumulate.simplify() : accumulate);
		
		
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

}
