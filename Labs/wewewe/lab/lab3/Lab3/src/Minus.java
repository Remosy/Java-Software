import java.util.ArrayList;
import java.util.Arrays;

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

    @Override
    public ArrayList<String> drawExpress() {
        String s0 = "     -     ";
        String s1 = "+----+----+";
        String s2 = "|         |";
        String s3 = "0         "+l1.drawExpress().get(0);
        ArrayList<String> s = new ArrayList<>(Arrays.asList(s0,s1,s2,s3));
        return  s;
    }
}
