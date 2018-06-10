
/**
 * DemoExpressions - a demo program for showing the working of simplify. 
 * @author Eric McCreath
 *
 */

public class DemoExpressions {

	public static void main(String[] args) {
        showAndSimplify(new SumExp(new LitExp(1),  new LitExp(1)));
        showAndSimplify(new SumExp(new VarExp("x"), new LitExp(1)));
        showAndSimplify(new SumExp(new LitExp(1),  new MultExp(new LitExp(2), new LitExp(3))));
        showAndSimplify(new SumExp(new LitExp(7),  new LitExp(-4),  new LitExp(-9)));
        showAndSimplify(new SumExp(new LitExp(7),  new VarExp("x"),  new LitExp(8)));
        showAndSimplify(new MultExp(new LitExp(2),  new LitExp(3)));
        showAndSimplify(new SumExp(new LitExp(3),  new VarExp("y"),  new LitExp(5), new LitExp(1),new VarExp("x"),new LitExp(-2) ));
        showAndSimplify(new SumExp(new LitExp(3),  new VarExp("y"),  new LitExp(-5),new VarExp("x"),new LitExp(2) ));
        showAndSimplify(new MultExp(new LitExp(3),  new VarExp("y"),  new LitExp(-5),new VarExp("x"),new LitExp(2) ));
        showAndSimplify(new MultExp(new LitExp(3),  new VarExp("y"),  new LitExp(-5),new VarExp("x"),new LitExp(0) ));
        showAndSimplify(new MultExp(new SumExp(new LitExp(7),  new VarExp("x"),  new LitExp(8)),  new SumExp(new VarExp("y"),  new VarExp("x"),  new LitExp(8)) ));
        showAndSimplify(new MultExp(new SumExp( new VarExp("a"),  new VarExp("b")), new SumExp(new VarExp("c"),  new VarExp("d")), new SumExp(new VarExp("e"),  new VarExp("f"))));
	}

	private static void showAndSimplify(Exp e) {
		System.out.println(e.show() + " simplifies to " + e.simplify().show());
	}
}
