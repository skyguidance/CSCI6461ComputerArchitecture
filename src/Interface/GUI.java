package Interface;

import CPU.Componets;
import Computer.Simulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.io.OutputStream;


public class GUI extends JFrame {
    private JButton IPLButton;
    private JPanel panelMain;
    private JButton SINGLESTEPButton;
    private JButton RUNButton;
    private JTextField PCInput;
    private JTextField IRInput;
    private JTextField MARInput;
    private JTextField R1Input;
    private JTextField R0Input;
    private JTextField R2Input;
    private JLabel PC;
    private JLabel IR;
    private JLabel MAR;
    private JLabel R0;
    private JLabel R1;
    private JLabel R2;
    private JTextField R3Input;
    private JTextField IX1Input;
    private JTextField IX2Input;
    private JLabel R3;
    private JLabel IX1;
    private JLabel IX2;
    private JTextField IX3Input;
    private JLabel IX3;
    private JTextField MBRInput;
    private JTextField MFRInput;
    private JLabel CC;
    private JLabel MFR;
    private JLabel MBR;
    private JTextField DMAddressInput;
    private JTextField DMValueInput;
    private JButton DMReadButton;
    private JButton DMwriteButton;
    private JLabel Address;
    private JLabel Value;
    private JRadioButton CC1Button;
    private JRadioButton CC2Button;
    private JRadioButton CC3Button;
    private JRadioButton CC4Button;
    private JTextArea Console;
    private JPanel IOOutputPanel;
    private Simulator simulator;
    private String IOString;

    public GUI() {

        simulator = new Simulator();
        IOString = "";

        PCInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == PCInput) {
                    simulator.componets.PC.setValue(PCInput.getText());
                    IOString = IOString+"\n"+ "PC=>"+PCInput.getText();
                    flushData(simulator.componets);
                }
            }
        });

        IRInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == IRInput) {
                    simulator.componets.IR.setValue(IRInput.getText());
                    IOString = IOString+"\n"+ "IR=>"+IRInput.getText();
                    flushData(simulator.componets);
                }
            }
        });

        MARInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == MARInput) {
                    simulator.componets.MAR.setValue(MARInput.getText());
                    IOString = IOString+"\n"+ "MAR=>"+MARInput.getText();
                    flushData(simulator.componets);
                }
            }
        });

        MBRInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == MBRInput) {
                    simulator.componets.MBR.setValue(MBRInput.getText());
                    IOString = IOString+"\n"+ "MBR=>"+MBRInput.getText();
                    flushData(simulator.componets);
                }
            }
        });

        MFRInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == MFRInput) {
                    simulator.componets.MFR.setValue(MFRInput.getText());
                    IOString = IOString+"\n"+ "MFR=>"+MFRInput.getText();
                    flushData(simulator.componets);
                }
            }
        });

        R0Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == R0Input) {
                    simulator.componets.R0.setValue(R0Input.getText());
                    IOString = IOString+"\n"+ "R0=>"+R0Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        R1Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == R1Input) {
                    simulator.componets.R1.setValue(R1Input.getText());
                    IOString = IOString+"\n"+ "R1=>"+R1Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        R2Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == R2Input) {
                    simulator.componets.R2.setValue(R2Input.getText());
                    IOString = IOString+"\n"+ "R2=>"+R2Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        R3Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == R3Input) {
                    simulator.componets.R3.setValue(R3Input.getText());
                    IOString = IOString+"\n"+ "R3=>"+R3Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        IX1Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == IX1Input) {
                    simulator.componets.IX1.setValue(IX1Input.getText());
                    IOString = IOString+"\n"+ "IX1=>"+IX1Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        IX2Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == IX2Input) {
                    simulator.componets.IX2.setValue(IX2Input.getText());
                    IOString = IOString+"\n"+ "IX2=>"+IX2Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        IX3Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == IX3Input) {
                    simulator.componets.IX3.setValue(IX3Input.getText());
                    IOString = IOString+"\n"+ "IX3=>"+IX3Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        DMReadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == DMReadButton) {
                    int Memdata = simulator.DataMemory.get(DMAddressInput.getText());
                    DMValueInput.setText(simulator.DataMemory.ToBinaryString(Memdata));
                    flushData(simulator.componets);
                }

            }
        });

        DMwriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == DMwriteButton) {
                    simulator.DataMemory.set(DMAddressInput.getText(),DMValueInput.getText());
                    IOString = IOString+"\n"+ "MEM["+DMAddressInput.getText()+"]=>"+DMValueInput.getText();
                    flushData(simulator.componets);
                }

            }
        });

        IPLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == IPLButton) {
                    simulator = new Simulator();
                    IOString = "";
                    flushData(simulator.componets);
                    DMValueInput.setText("0000000000000000");
                    DMAddressInput.setText("000000000000");
                }

            }
        });

        SINGLESTEPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == SINGLESTEPButton) {
                    simulator.BUS.tik();
                    flushData(simulator.componets);
                }

            }
        });

    }

    public static void main(String args[]) {
        JFrame jFrame = new JFrame("CSCI6461 Computer Simulator");
        GUI gui = new GUI();
        jFrame.setContentPane(gui.panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        // Simulator simulator = new Simulator();


    }

    public void flushData(Componets data) {
        redirectSystemStreams();
        PCInput.setText(data.PC.ToBinaryString());
        IRInput.setText(data.IR.ToBinaryString());
        MARInput.setText(data.MAR.ToBinaryString());
        MBRInput.setText(data.MBR.ToBinaryString());
        MFRInput.setText(data.MFR.ToBinaryString());
        R0Input.setText(data.R0.ToBinaryString());
        R1Input.setText(data.R1.ToBinaryString());
        R2Input.setText(data.R2.ToBinaryString());
        R3Input.setText(data.R3.ToBinaryString());
        IX1Input.setText(data.IX1.ToBinaryString());
        IX2Input.setText(data.IX2.ToBinaryString());
        IX3Input.setText(data.IX3.ToBinaryString());
        //Console.setText(IOString);
        CC1Button.setSelected(data.CC1.get());
        CC2Button.setSelected(data.CC2.get());
        CC3Button.setSelected(data.CC3.get());
        CC4Button.setSelected(data.CC4.get());
    }

    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Console.append(text);
            }
        });
    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        //DEBUG ONLY:Redirect the Error to IO.
        System.setErr(new PrintStream(out, true));
    }

}