import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DialGUI implements Runnable, Observer {

	private static final Dimension COLORDIM = new Dimension(
			DialRGB.dim.height * 3, DialRGB.dim.height * 3);
	/**
	 * DialGUI - this application enables the users to change the color of a
	 * rectangle by altering 3 dials. The dial alter the RGB components of the
	 * color. Eric McCreath 2015,2017 - GPL
	 */

	JFrame jframe;
	JComponent colorComp;
	DialRGB dialr;
	DialRGB dialg;
	DialRGB dialb;
	

	public DialGUI() {
		SwingUtilities.invokeLater(this);
	}

	public static void main(String[] args) {
		new DialGUI();
	}

	// start up the JFrame add the dials and components 
	public void run() {
		jframe = new JFrame("DialGUI");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		dialr = new DialRGB(Color.RED);
		dialr.registredObserber(this);

		dialg = new DialRGB(Color.GREEN);
		dialg.registredObserber(this);

		dialb = new DialRGB(Color.BLUE);
		dialb.registredObserber(this);

		colorComp = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.setColor(this.getForeground());
				g.fillRect(0, 0, COLORDIM.width, COLORDIM.height);
			}
		};
		colorComp.setPreferredSize(COLORDIM);
		JPanel dialpanel = new JPanel();
		dialpanel.setLayout(new GridLayout(3, 1));
		dialpanel.add(dialr);
		dialpanel.add(dialg);
		dialpanel.add(dialb);

		jframe.getContentPane().add(dialpanel, BorderLayout.EAST);
		jframe.getContentPane().add(colorComp, BorderLayout.CENTER);

		update();
		jframe.setVisible(true);
		jframe.pack();
	}

	// update - get the values from the dials and set the color of 
	//          the panel based on these values.
	@Override
	public void update() {
		float r = (float) dialr.value();
		float g = (float) dialg.value();
		float b = (float) dialb.value();

		colorComp.setForeground(new Color(r, g, b));
		colorComp.repaint();
	}

}
