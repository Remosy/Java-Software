
/**
 * DemoExpressions - a demo program for showing the working of "findReducingAssignment". 
 * @author Eric McCreath
 *
 */

public class DemoExpressions {

	public static void main(String[] args) {
        checkReducingAssignment(new SumExp(new LitExp(1),  new LitExp(1)));
        checkReducingAssignment(new SumExp(new VarExp("x"), new LitExp(1)));
        checkReducingAssignment(new MultExp(new SumExp(new VarExp("x"), new LitExp(1)),new SumExp(new VarExp("x"), new LitExp(1))));
        checkReducingAssignment(new MultExp(new SumExp(new VarExp("x"), new LitExp(1)),new SumExp(new LitExp(1), new VarExp("x"))));
        checkReducingAssignment(new SumExp(new MultExp(new LitExp(2),new VarExp("x")),new MultExp(new LitExp(2),new VarExp("x")),new MultExp(new LitExp(2),new VarExp("x"))));
        checkReducingAssignment(new SumExp(new MultExp(new MultExp(new LitExp(2),new VarExp("x")),new MultExp(new LitExp(2),new VarExp("x"))),new SumExp(new MultExp(new LitExp(2),new VarExp("x")), new VarExp("y")),new MultExp(new LitExp(2),new VarExp("x"))));
        checkReducingAssignment(new SumExp(new MultExp(new LitExp(2),new VarExp("x")), new MultExp(new LitExp(2),new VarExp("x")), new MultExp(new LitExp(3),new VarExp("y")), new MultExp(new LitExp(3),new VarExp("y"))));
        checkReducingAssignment(new SumExp(new SumExp(new MultExp(new LitExp(2),new VarExp("x")), new MultExp(new LitExp(2),new VarExp("x"))), new MultExp(new LitExp(3),new VarExp("y")), new MultExp(new LitExp(3),new VarExp("y"))));
        checkReducingAssignment(new SumExp(new SumExp(new MultExp(new LitExp(2),new VarExp("x")), new MultExp(new LitExp(3),new VarExp("y"))), new MultExp(new LitExp(2),new VarExp("x")), new MultExp(new LitExp(3),new VarExp("y"))));
        checkReducingAssignment(new SumExp(new SumExp(new MultExp(new LitExp(2),new VarExp("x")), new MultExp(new LitExp(3),new VarExp("y"))), new MultExp(new LitExp(3),new VarExp("y")), new MultExp(new LitExp(2),new VarExp("x"))));
        checkReducingAssignment(new SumExp(new MultExp(new SumExp(new VarExp("x"), new LitExp(1)),new SumExp(new VarExp("x"), new LitExp(1))),new MultExp(new SumExp(new VarExp("x"), new LitExp(1)),new SumExp(new VarExp("x"), new LitExp(1)))));
	}

	private static void checkReducingAssignment(Exp e) {
		int ocount =  e.countOperations();
		System.out.print("The expression " + e.show() + " has " + ocount+ " operation" + (ocount>1?"s":"")); 
		Assignment reducingAssignment = e.findReducingAssignment();
		if (reducingAssignment != null) {
			Exp reducedExpression = e.replace(reducingAssignment.expression, reducingAssignment.var);
			int recount = reducedExpression.countOperations();
			int acount = reducingAssignment.expression.countOperations(); 
			System.out.print(" if the assignment " + reducingAssignment + " was\n     made the expression would be reduced to " + 
			                   reducedExpression + " giving " + acount + " + " + recount + " = " + (acount + recount) + " operations.");
		} else {
			System.out.print(" and there is no reducing assignment.");
		}
		System.out.println();
	}
}
