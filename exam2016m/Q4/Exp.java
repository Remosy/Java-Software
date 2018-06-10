import java.util.LinkedList;

public abstract class Exp implements Comparable<Exp> {
	
	abstract int countOperations(); // count the total number of * or + operations in the expression
	
	abstract String show(); // turn the expression into a string  
	
	abstract Exp replace(Exp subexp, VarExp var); // return the expression which has cases of "subexp" replaced with "var"

	abstract public boolean equals(Object o);

	abstract public int hashCode();

	@Override
	public String toString() {
		return show();
	}
	
        // This finds the assignment statement that can be used to reduce the instruction count.
        // "null" is return if no reducing assignment exists.  (see assignment spec for details)
	Assignment findReducingAssignment() {
		Assignment bestResult = null;
		return bestResult;
	}
	
}
