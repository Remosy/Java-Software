

public abstract class Tokenizer {
    abstract boolean hasNext();  // checks if there is more tokens to come
    abstract Token current();  // returns the current token the tokenizer is pointing at
    abstract void next();  // move onto the next token

    public void parse(Token o) throws ParseException{
        if (current() == null || !current().equals(o))
            throw new ParseException();
        next();
    }

}
