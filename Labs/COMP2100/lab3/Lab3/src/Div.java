import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Div extends Expression {
    Expression l1;
    Expression l2;
    Div(Expression l1, Expression m1) {
        this.l1 = l1;
        this.l2 = m1;
    }

    @Override
    public String show() {
        return "("+l1.show()+"/"+l2.show()+")";
    }

    @Override
    public int evaluate() {
        if(l2.evaluate() == 0)return 0;
        return l1.evaluate()/l2.evaluate();
    }

    @Override
    public ArrayList<String> drawExpress() {
        String s0 = "";//Sign
        String s1 = "";//+---+
        String s2 = "";//|   |
        Stack stack = new  Stack();
        int size1 = l1.drawExpress().size();
        int size2 = l2.drawExpress().size();
        if (size1 < size2){
            for(int x = size2-1;x >= 0; x--) {
                String xx = "          "+l2.drawExpress().get(x);
                if(size1-x>=0){
                    xx = l1.drawExpress().get(x)+"          "+l2.drawExpress().get(x);
                }
                stack.push(xx);
            }

        }else if(size1 > size2){
            for(int x = size1-1;x >= 0; x--) {
                String xx = l1.drawExpress().get(x);
                if(size2-1-x>=0){
                    //System.out.println(size1);
                    //System.out.println(size2);
                    //System.out.println(x);
                    xx = l1.drawExpress().get(x)+"          "+l2.drawExpress().get(x);
                }
                stack.push(xx);
            }
        }else {
            for(int x = size1-1;x >= 0; x--) {
                String xx = l1.drawExpress().get(x)+"         "+l2.drawExpress().get(x);
                stack.push(xx);
            }
        }
        String top_string = stack.peek().toString();
        int sub_ct = 0;
        for(char c: top_string.toCharArray()){
            s0+= " ";
            if(c==' '){
                s2+=" ";
                if (sub_ct<1 || sub_ct>2) {
                    s1 += " ";
                }else {
                    s1+="-";
                }
            }else{
                System.out.println(sub_ct);
                sub_ct++;
                s2+="|";
                s1+="+";
            }
        }
        int mid = (int) Math.ceil(s2.length()/2);
        s1 = s1.substring(0,mid)+"+"+s1.substring(mid+1,s1.length());
        s0 = s0.substring(0,mid)+'/'+s0.substring(mid,s0.length());
        ArrayList<String> s = new ArrayList<>(Arrays.asList(s0,s1,s2));
        while (!stack.empty()){
            s.add(stack.pop().toString());
        }
        return s;
    }
}
