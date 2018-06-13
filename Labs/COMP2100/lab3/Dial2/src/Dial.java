import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

/*
 * Dial - a grey nob for controlling a value that ranges between 0 and 1.
 * The dials value is changed as you drag the mouse horizontally.
 * Eric McCreath
 */


public abstract class Dial {
	abstract void drawdial(Graphics2D g, double v);
	abstract void drawtick(Graphics2D g, double v, double s, double e);
	abstract void drawbackground(Graphics2D g);
	abstract void setGUI(DialGUI dialGUI);
	abstract double value();

}
