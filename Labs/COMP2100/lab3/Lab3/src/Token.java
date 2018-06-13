public class Token {
    TokenType tokenType;
    int value;
    private static final char whitespace[] = { ' ', '\n', '\t' };
    private static final char symbol[] = { '(', ')', '=', ':', '?', ',', '*', '+' };

    Token(TokenType tokenType, int value){
        this.tokenType = tokenType;
        this.value = value;
    }

    @Override
    public String toString() {
        if(tokenType == TokenType.INTLIT){
            return tokenType.toString()+"("+value+")";
        }
        return tokenType.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return this.tokenType.equals(((Token)obj).tokenType);
    }
}
