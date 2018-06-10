import static org.junit.Assert.*;

import org.junit.Test;

/**
 * TestSimplify - a set of unit tests on the simplify method.
 * @author Eric McCreath
 *
 */

public class TestSimplify {

	@Test
	public void testLits() {
		assertEquals(new SumExp(new MultExp(new LitExp(0))), (new LitExp(0)).simplify());  // 0 -> 0
		assertEquals(new SumExp(new MultExp(new LitExp(1))), (new LitExp(1)).simplify()); // 1 -> 1
		assertEquals(new SumExp(new MultExp(new LitExp(4))), (new LitExp(4)).simplify()); // 4 -> 4
	}
	
	@Test
	public void testVars() {
		assertEquals(new SumExp(new MultExp(new VarExp("x"))), (new VarExp("x")).simplify()); // x -> x
	}
	
	@Test
	public void testNoVarExp() {
		assertEquals(new SumExp(new MultExp(new LitExp(2))), (new SumExp(new LitExp(1), new LitExp(1))).simplify()); // 1 + 1 -> 2
		assertEquals(new SumExp(new MultExp(new LitExp(6))), (new MultExp(new LitExp(2), new LitExp(3))).simplify()); // 2 * 3 -> 6
		assertEquals(new SumExp(new MultExp(new LitExp(45))), (new MultExp(new SumExp(new LitExp(2), new LitExp(3)), new SumExp(new LitExp(4), new LitExp(5)))).simplify()); // (2+3) * (4+5) -> 45
		assertEquals(new SumExp(new MultExp(new LitExp(0))), (new MultExp(new SumExp(new LitExp(2), new LitExp(3)), new SumExp(new LitExp(-4), new LitExp(4)))).simplify()); // (2+3) * (-4+4) -> 0	
	}
	
	@Test
	public void testVarExp() {
		assertEquals(new SumExp(new MultExp(new LitExp(4)), new MultExp(new VarExp("x"))), (new SumExp(new VarExp("x"), new LitExp(4))).simplify()); // x + 4 -> 4 + x
		assertEquals(new SumExp(new MultExp(new LitExp(1)), new MultExp(new VarExp("x"))), (new SumExp(new VarExp("x"), new LitExp(1))).simplify()); // x + 1 -> 1 + x
		assertEquals(new SumExp(new MultExp(new VarExp("x"))), (new SumExp( new LitExp(-1), new VarExp("x"), new LitExp(1))).simplify()); // (-1) + x + 1 -> x
		assertEquals(new SumExp(new MultExp(new VarExp("a")), new MultExp(new VarExp("b"))), (new SumExp(new VarExp("b"), new VarExp("a"))).simplify()); // b + a -> a + b
		assertEquals(new SumExp(new MultExp(new LitExp(7)), new MultExp(new VarExp("x")), new MultExp(new VarExp("y"))), (new SumExp(new SumExp(new LitExp(4), new VarExp("x")), new SumExp(new  LitExp(3), new VarExp("y")))).simplify()); // (4 + x) + (3 + y) -> 7 + x + y
		assertEquals(new SumExp(new MultExp(new LitExp(12)),  new MultExp(new LitExp(3), new VarExp("y")), new MultExp(new LitExp(4) ,new VarExp("x")),new MultExp(new VarExp("x"), new VarExp("y"))), (new MultExp(new SumExp(new LitExp(4), new VarExp("y")), new SumExp(new  LitExp(3), new VarExp("x")))).simplify()); // (4 + y) * (3 + x) -> 12 + 4 * x + 3 * y + x * y	
		assertEquals(new SumExp(new MultExp(new VarExp("a"),new VarExp("b"),new VarExp("c"),new VarExp("d"))), (new MultExp(new VarExp("d"),new MultExp(new VarExp("c"), new MultExp(new VarExp("b"), new VarExp("a"))) )).simplify()); // (d*(c*(b*a))) -> a*b*c*d
	}
}
