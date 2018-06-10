
/*
 * Exp - a mathmatical expression 
 * Eric McCreath 2016
  */

public abstract class Exp implements Comparable<Exp> {
	abstract SumExp simplify();
	
	abstract String show();

	abstract public boolean equals(Object o);

	abstract public int hashCode();

	@Override
	public String toString() {
		return show();
	}
}
