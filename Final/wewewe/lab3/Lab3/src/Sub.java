public class Sub extends Expression{
    Expression l1;
    Expression l2;
    Sub(Expression l1, Expression l2) {
        this.l1 = l1;
        this.l2 = l2;
    }

    @Override
    public String show() {
        return "("+l1.show()+"- ("+l2.show()+"))";
    }

    @Override
    public int evaluate() {
        return l1.evaluate() - (l2.evaluate());
    }
}
