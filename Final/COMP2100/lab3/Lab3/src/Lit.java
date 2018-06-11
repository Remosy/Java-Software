import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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

    @Override
    public ArrayList<String> drawExpress() {
        String s0 =show();
        ArrayList<String> s = new ArrayList<>(Arrays.asList(s0));
        return s;
    }
}
