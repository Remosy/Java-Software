import java.util.ArrayList;

public class MathTokenizer extends Tokenizer{

    private String text;
    private int pos;
    private Token current;
    static final char whitespace[] = { ' ', '\n', '\t' };
    static final char symbol[] = { '(', ')', '/', '-', '*', '+' };


    @Override
    boolean hasNext() {
        return current != null;
    }

    @Override
    Token current() {
        return current;
    }

    @Override
    void next() {
        consumewhite();
        if (pos == text.length()) {
            current = null;
        } else if (isin(text.charAt(pos), symbol)) {
           switch (text.charAt(pos)) {
               case '(':
                   current = new Token(TokenType.LBRA,-1);
                   break;
               case ')':
                   current = new Token(TokenType.RBRA,-1);
                   break;
               case '/':
                   current = new Token(TokenType.DIV,-1);
                   break;
               case '-':
                   current = new Token(TokenType.MINUS,-1);
                   break;
               case '*':
                   current = new Token(TokenType.MULT,-1);
                   break;
               case '+':
                   current = new Token(TokenType.PLUS,-1);
                   break;
           }
            pos++;

        } else if (Character.isDigit(text.charAt(pos))) {
            int start = pos;
            while (pos < text.length() && Character.isDigit(text.charAt(pos)) ){
                pos++;
            }
            current = new Token(TokenType.INTLIT,Integer.parseInt(text.substring(start, pos)));

        } else {
            int start = pos;
            while (pos < text.length() && !isin(text.charAt(pos), symbol)
                    && !isin(text.charAt(pos), whitespace))
                pos++;
        }
    }

    MathTokenizer(String string){
        text = string;
        pos = 0;
        next();
    }

    private void consumewhite() {
        while (pos < text.length() && isin(text.charAt(pos), whitespace))
            pos++;
    }

    private boolean isin(char c, char charlist[]) {
        for (char w : charlist) {
            if (w == c)
                return true;
        }
        return false;
    }




}
