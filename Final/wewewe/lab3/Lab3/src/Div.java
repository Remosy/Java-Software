public class Div extends Expression {
    Expression l1;
    Expression m1;
    Div(Expression l1, Expression m1) {
        this.l1 = l1;
        this.m1 = m1;
    }

    @Override
    public String show() {
        return "("+l1.show()+"/ ("+m1.show()+")";
    }

    @Override
    public int evaluate() {
        if(m1.evaluate() == 0)return 0;
        return l1.evaluate()/m1.evaluate();
    }
}
