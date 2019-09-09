package Interface;

import javax.swing.*;

public class GUI3 extends JFrame{
    private JButton IPLButton;
    private JPanel panelMain;
    private JButton button1;
    private JButton RUNButton;
    private JTextField TextField;
    private JTextField TextField1;
    private JTextField a0000000000000000TextField;
    private JTextField a0000000000000000TextField3;
    private JTextField a0000000000000000TextField5;
    private JTextField a0000000000000000TextField4;
    private JTextField a0000000000000000TextField6;
    private JLabel PC;
    private JLabel IR;
    private JLabel MAR;
    private JLabel R0;
    private JLabel R1;
    private JLabel R2;
    private JTextField a0000000000000000TextField7;
    private JTextField a0000000000000000TextField8;
    private JTextField a0000000000000000TextField9;
    private JLabel R3;
    private JLabel IX1;
    private JLabel IX2;
    private JTextField a0000000000000000TextField10;
    private JLabel IX3;
    private JTextField a0000000000000000TextField1;
    private JTextField a0000000000000000TextField2;
    private JLabel CC;
    private JLabel MFR;
    private JLabel MBR;
    private JTextField a0TextField;
    private JTextField a0TextField1;
    private JButton ReadButton;
    private JButton writeButton;
    private JLabel Address;
    private JLabel Value;
    private JTextField textField16;
   

    public static void main(String args[]) {
      JFrame jFrame=new JFrame("Computer");
      jFrame.setContentPane(new GUI3().panelMain);
      jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jFrame.pack();
      jFrame.setVisible(true);

    }
}
