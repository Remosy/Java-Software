import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Dial extends JComponent implements MouseMotionListener, MouseListener {
    abstract void drawdial(Graphics2D g, double v);
    abstract void drawtick(Graphics2D g, double v, double s, double e);
    abstract void drawbackground(Graphics2D g);
    abstract void setGUI(DialGUI dialGUI);// Used in RGB's ColourDial
    abstract double value();
}