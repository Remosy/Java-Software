import java.text.ParseException;
import java.util.ArrayList;

public abstract class Expression {

    public abstract String show(); // this should show the expression as a string
    public abstract int evaluate();  // this should evaluate the expression
    public abstract ArrayList<String> drawExpress();



}
