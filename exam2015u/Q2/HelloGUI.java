import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class HelloGUI implements Runnable {

	/**
	 * HelloGUI - a simple "hello world" swing GUI. Eric McCreath 2015 -
	 */

	JFrame jframe;

	public HelloGUI() {
		SwingUtilities.invokeLater(this);
	}

	public static void main(String[] args) {
		new HelloGUI();
	}

	public void run() {
		jframe = new JFrame("HelloGUI");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jframe.getContentPane().add(new JLabel("Hello World"));

		jframe.setVisible(true);
		jframe.pack();
	}
}
