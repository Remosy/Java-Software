

public class Parser {

    public static Expression parse(Tokenizer tok) throws ParseException {
        Token t = tok.current();
        if (t.tokenType.equals(TokenType.INTLIT)) {
            int v = (Integer) t.value;
            tok.next();
            return new Lit(v);
        } else if (t.tokenType.equals(TokenType.LBRA)) {
            tok.next();
            Expression exp1 = parse(tok);
            if (tok.current().tokenType.equals(TokenType.PLUS)) {
                //System.out.println(tok.current().toString());
                tok.parse(new Token(TokenType.PLUS, -1));
                Expression exp2 = parse(tok);
                if (tok.current().tokenType.equals(TokenType.RBRA)) {
                    tok.parse(new Token(TokenType.RBRA, -1));
                }
                return new Add(exp1, exp2);

            } else if (tok.current().tokenType.equals(TokenType.MINUS)) {
                tok.parse(new Token(TokenType.MINUS, -1));
                Expression exp2 = parse(tok);
                if (tok.current().tokenType.equals(TokenType.RBRA)) {
                    tok.parse(new Token(TokenType.RBRA, -1));
                }
                return new Sub(exp1, exp2);

            } else if (tok.current().tokenType.equals(TokenType.DIV)) {
                tok.parse(new Token(TokenType.DIV, -1));
                Expression exp2 = parse(tok);
                if (tok.current().tokenType.equals(TokenType.RBRA)) {
                    tok.parse(new Token(TokenType.RBRA, -1));
                }
                return new Div(exp1, exp2);
            }
            else{
                tok.parse(new Token(TokenType.MULT, -1));
                Expression exp2 = parse(tok);
                if (tok.current().tokenType.equals(TokenType.RBRA)) {
                    tok.parse(new Token(TokenType.RBRA, -1));
                }
                return new Mult(exp1, exp2);
            }

        } else if (t.tokenType.equals(TokenType.MINUS)) {
            tok.parse(new Token(TokenType.MINUS, -1));
            tok.next();
            Expression exp1 = parse(tok);
            return new Minus(exp1);
        } else {
            throw new ParseException();
        }
    }
}
