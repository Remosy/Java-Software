public class Mult extends Expression {
    Expression l1;
    Expression s1;
    Mult(Expression l1, Expression s1) {
        this.l1 = l1;
        this.s1 = s1;
    }

    @Override
    public String show() {
        return "("+this.l1.show()+"*"+this.s1.show()+")";
    }

    @Override
    public int evaluate() {
        return l1.evaluate() * s1.evaluate();
    }
}
