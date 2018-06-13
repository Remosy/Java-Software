import javax.swing.*;
import java.awt.*;

public class HelloGUI implements Runnable,MyObserver {

	/**
	 * HelloGUI - a simple "hello world" swing GUI. Eric McCreath 2015 -
	 */

	JFrame jframe;
	// add a label
	Counter counter;
	// add Max
	static final int MAXNUM = 10;
	//add button
	SimpleButton exit;
	SimpleButton enter;
	JPanel panel;


	public HelloGUI() {

		SwingUtilities.invokeLater(this);

	}

	public static void main(String[] args) {
		new HelloGUI();
	}

	public void run() {
		counter = new Counter(MAXNUM);
		exit = new Exit();
		exit.register(this);
		enter = new Enter();
		enter.register(this);
		panel = new JPanel();

		jframe = new JFrame("HelloGUI");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setPreferredSize(new Dimension(300,300));

		panel.add(exit);
		panel.add(enter);

		//add label and button
		jframe.add(counter,BorderLayout.NORTH);
		//jframe.add(enter);
		jframe.add(panel,BorderLayout.CENTER);
		jframe.setVisible(true);
		jframe.pack();
	}

	@Override
	public void update(int x) {

		int newx = counter.getCurrent()+ x;
		if(newx >=0 && newx <=10){
			counter.setText("The number is"+newx);
			counter.setCurrent(newx);
		}

	}
}
