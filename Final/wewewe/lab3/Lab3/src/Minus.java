public class Minus extends Expression {
    Expression l1;
    Minus(Expression l1) {
        this.l1 = l1;
    }

    @Override
    public String show() {
        return "-("+l1.show()+")";
    }

    @Override
    public int evaluate() {
        return -l1.evaluate();
    }
}
