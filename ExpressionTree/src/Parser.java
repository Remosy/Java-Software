

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
            Expression exp1 = parse(tok);
            return new Minus(exp1);
        } else {
            throw new ParseException();
        }
    }

    public static Expression parseExp(Tokenizer tok)throws ParseException{
        Expression term = parseTerm(tok);
        Token t = tok.current();
        if(t.tokenType.equals(TokenType.PLUS)){
            tok.parse(new Token(TokenType.PLUS, -1));
            Expression exp = parseExp(tok);
            return new Add(term,exp);
        }else if(t.tokenType.equals(TokenType.MINUS)){
            tok.parse(new Token(TokenType.MINUS, -1));
            Expression exp = parseExp(tok);
            return new Sub(term,exp);
        }else if(!t.tokenType.equals(TokenType.PLUS) && !t.tokenType.equals(TokenType.MINUS)){
            return term;
        }else {
            throw new ParseException(); //Has to Return!!!
        }
    }

    public static Expression parseTerm(Tokenizer tok)throws ParseException{
        Expression factor = parseFactor(tok);
        Token t = tok.current();
        if(t.tokenType.equals(TokenType.MULT)){
            tok.parse(new Token(TokenType.MULT, -1));
            System.out.println(tok.current().toString());
            Expression term = parseTerm(tok);
            return new Mult(factor,term);
        }else if(t.tokenType.equals(TokenType.DIV)){
            tok.parse(new Token(TokenType.DIV, -1));
            Expression term = parseTerm(tok);
            return new Div(factor,term);
        }else if(!t.tokenType.equals(TokenType.MULT) && !t.tokenType.equals(TokenType.DIV)){
            return factor;
        }else {
            throw new ParseException();
        }
    }

    public static Expression parseFactor(Tokenizer tok)throws ParseException{
        Token t = tok.current();
        if (t.tokenType.equals(TokenType.INTLIT)) {
            int v = (Integer) t.value;
            if(tok.hasNext()==true) {
                tok.next();
            }
            return new Lit(v);
        } else if(t.tokenType.equals(TokenType.MINUS)){
            tok.parse(new Token(TokenType.MINUS, -1));
            Expression exp1 = parseExp(tok);
            return new Minus(exp1);
        }else if(t.tokenType.equals(TokenType.LBRA)){
            tok.next();
            Expression exp1 = parseExp(tok);
            if (tok.current().tokenType.equals(TokenType.RBRA)) {
                tok.parse(new Token(TokenType.RBRA, -1));
            }
            return exp1;
        }else{
            throw new ParseException();
        }


    }
}
