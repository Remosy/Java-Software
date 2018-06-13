import javax.swing.*;
import java.awt.*;

public class Counter extends JLabel {

    int maxNum =0;
    int current=0;


    public Counter(int max){
        this.maxNum = max;
        this.setText("The number is:"+current);
        this.setPreferredSize(new Dimension(300,100));
    }

    public void setCurrent(int current){
        this.current = current;
    }

    public int getCurrent() {
        return current;
    }
}
