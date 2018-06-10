
/**
 * LitExp - a simple number literal expression
 * @author Eric McCreath 2016
 *
 */

public class LitExp extends Exp {

	int value;
	
	public LitExp(int value) {
		this.value = value;
	}
	
	@Override
	String show() {
		if (value < 0) return  String.format("(%d)", value);
		return String.format("%d", value);
	}
	
	public int value() {
		return value;
	}

	@Override
	public int compareTo(Exp o) {
		if (o instanceof LitExp) return new Integer(value).compareTo(new Integer(((LitExp) o).value()));
		return -1;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof LitExp) return value == ((LitExp) o).value();
		return false;
	}

	@Override
	public int hashCode() {
		return new Integer(value).hashCode();
	}

	@Override
	int countOperations() {
		return 0;
	}

	@Override
	Exp replace(Exp subexp, VarExp var) {
		return (subexp.equals(this)? var : this); 
	}

}
