import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static java.awt.Font.BOLD;
class Pair{
    int init;
    int end;
    Pair(int a, int b){
        init = a;
        end = b;
    }
}
public class Gui extends JFrame{

    static ArrayList<Pair> position = new ArrayList<Pair>();
    boolean isBold = false;

    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                create();
            }
        });
    }

    public static void create(){
        JFrame jframe = new JFrame("Simple Wordprocessor");
        jframe.validate();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(900,700);
        jframe.setVisible(true);
        //---------------------Menu--------------------
        JMenu jmenu_file = new JMenu("File");
        JMenu jmenu_tool = new JMenu("Tool");
        JMenuBar jmenubar = new JMenuBar();

        jframe.setJMenuBar(jmenubar);
        jmenubar.add(jmenu_file);
        jmenubar.add(jmenu_tool);

        JMenuItem jmenuitem_exist = new JMenuItem("Exist");
        jmenu_file.add(jmenuitem_exist);
        JMenuItem jMenuItem_count = new JMenuItem("Count Words");
        jmenu_tool.add(jMenuItem_count);

        //----------------------BOLD---------------------
        JPanel jPanel_top = new JPanel();
        jPanel_top.setLayout(new BorderLayout());
        JCheckBox check_bold = new JCheckBox("Bold");
        check_bold.setBackground(Color.lightGray);
        jPanel_top.add(check_bold, BorderLayout.PAGE_START);


        //---------------------BOARD--------------------
        JTextPane jtextpane = new JTextPane();
        jtextpane.setFont(new Font("Monospaced", Font.PLAIN, 16));
        jPanel_top.add(jtextpane,BorderLayout.CENTER);
        jframe.add(jPanel_top);


        jmenuitem_exist.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
            }
        });
        jMenuItem_count.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = jtextpane.getText();
                String[] strings  = str.split("\\W+");
                JOptionPane.showMessageDialog(null, "world count: "+strings.length);
            }
        });


        jtextpane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character k = e.getKeyChar();
                SimpleAttributeSet aset = new SimpleAttributeSet();
                StyleConstants.setFontSize(aset, 16);
                int start_len = jtextpane.getText().length();
                if(check_bold.isSelected()) {
                    StyleConstants.setBold(aset, true);
                }else{

                    StyleConstants.setBold(aset, false);
                }
                jtextpane.setCaretPosition(start_len);
                jtextpane.setCharacterAttributes(aset, false);
                jtextpane.replaceSelection(String.valueOf(k));
            }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
    }
}
