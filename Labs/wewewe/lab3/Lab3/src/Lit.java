public class Lit extends Expression {
    int number;
    Lit(int number) {
        this.number =number;
    }

    @Override
    public String show() {
        return Integer.toString(this.number);
    }

    @Override
    public int evaluate() {
        return this.number;
    }
}
