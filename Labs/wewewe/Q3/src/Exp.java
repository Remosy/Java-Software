import java.util.ArrayList;

/*
 * Exp 
 * Eric McCreath 2017
 */

public abstract class Exp {
	public abstract int evaluate(Subs subs, Functions funs);

	public abstract String show();

	
	static public Exp parseExp(Tokenizer tok) {
		if(tok.equals(0)){
			int v = 0;
			tok.next();
			return new Lit(v);
		}else if(tok.equals(tok.)){

		}

		return null;
	}

	private static ArrayList<Exp> parseExps(Tokenizer tok) {
		// add your code here
		return null;
	}

	private static Function parseFunction(Tokenizer tok) {
		
		// add your code here
		return null;
	}

	private static Vars parseVars(Tokenizer tok) {
		// add your code here
		return null;
	}
	
	
	public static Functions parseFunctions(Tokenizer tok) {
		// add your code here
		return null;
	}

}