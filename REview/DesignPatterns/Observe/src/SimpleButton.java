import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;

public abstract class SimpleButton extends JButton {

    ArrayList<MyObserver> observers = new ArrayList<>();

    public SimpleButton(){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notice(changeCurrent());
            }
        });
    }

    protected abstract int changeCurrent();

    public void register(MyObserver o ){
        observers.add(o);
    }

    public void unregister(MyObserver o){
        if (observers.contains(o)){
            observers.remove(o);
        }
    }

    public void notice(int x){
        for(MyObserver o :observers){
            o.update(x);
        }
    }



}
