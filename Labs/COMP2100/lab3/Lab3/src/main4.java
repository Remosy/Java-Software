import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main4 {
    public static void main(String[] args) throws ParseException {
        /*MathTokenizer mathTokenizer = new MathTokenizer("     ((130-10   )  /-   2)   ");
        while (mathTokenizer.hasNext()){
            mathTokenizer.next();
            if(mathTokenizer.current()!= null)
            System.out.println(mathTokenizer.current());
        }
        MathTokenizer mathTokenizer1 = new MathTokenizer("((1+1)+(1/1))");
        Expression exp1 = Parser.parse(mathTokenizer1);
        System.out.println(exp1.evaluate());
        System.out.println(exp1.show());*/
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                create();
            }
        });
    }

    public static void create() {
        Font f = new Font("Courier New", Font.ITALIC, 18);
        JFrame jframe = new JFrame("Calculator");
        jframe.validate();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(300, 300);
        jframe.setVisible(true);

        JPanel jPanel_top = new JPanel();
        jPanel_top.setLayout(new BorderLayout());
        JTextField jTextField = new JTextField();
        jTextField.setFont(f);
        jPanel_top.add(jTextField, BorderLayout.PAGE_START);
        JButton jButton = new JButton("=");
        Font f2 = new Font("Courier New", Font.ITALIC, 58);
        jButton.setFont(f2);
        jButton.setMaximumSize(new Dimension(300, 100));
        jPanel_top.add(jButton, BorderLayout.EAST);
        JTextArea jTextArea = new JTextArea();
        jTextArea.setFont(f);
        jPanel_top.add(jTextArea,BorderLayout.WEST);
        JLabel jLabel = new JLabel();
        jLabel.setFont(f);
        jLabel.setForeground(Color.RED);
        jLabel.setText("........");
        jLabel.setMinimumSize(new Dimension(300, 100));
        jPanel_top.add(jLabel,BorderLayout.SOUTH);
        jframe.add(jPanel_top);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = jTextField.getText();
                if (s.length() > 1) {
                    MathTokenizer mathTokenizer1 = new MathTokenizer(s);
                    try {
                        Expression exp1 = Parser.parse(mathTokenizer1);
                        jLabel.setText(exp1.evaluate() + "   "+exp1.show());
                       // for(String x:exp1.drawExpress()){
                           // System.out.println(x);
                       // }
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }else {
                    jLabel.setText("");
                }
            }
        });
    }
    }
