
/**
 * Assignment - this is an assignment statement  e.g. "x = y+2;"
 * @author Eric McCreath 2016
 *
 */

public class Assignment {

	VarExp var;  // the variable that is assigned
	Exp expression; // the expression that is evaluated to be assigned to the variable
	
	public Assignment(VarExp var, Exp expression) {
		this.var = var;
		this.expression = expression;
	}
	
	public String toString() {
		return var + " = " + expression + ";";
	}
	
	
}
