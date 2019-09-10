package Interface;

import CPU.Componets;
import Computer.Simulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.io.Flushable;


public class GUI3 extends JFrame {
    private JButton IPLButton;
    private JPanel panelMain;
    private JButton SINGLESTEPButton;
    private JButton RUNButton;
    private JTextField PCInput;
    private JTextField IRInput;
    private JTextField MARInput;
    private JTextField CCInput;
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
    private JTextField Console;
    private Simulator simulator;
    private String IOString;

    public GUI3() {

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
        JFrame jFrame = new JFrame("Computer");
        GUI3 gui = new GUI3();
        jFrame.setContentPane(gui.panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        // Simulator simulator = new Simulator();


    }

    public void flushData(Componets data) {
        PCInput.setText(data.PC.ToBinaryString());
        IRInput.setText(data.IR.ToBinaryString());
        MARInput.setText(data.MAR.ToBinaryString());
        IX1Input.setText(data.IX1.ToBinaryString());
        IX2Input.setText(data.IX2.ToBinaryString());
        IX3Input.setText(data.IX3.ToBinaryString());
        MBRInput.setText(data.MBR.ToBinaryString());
        MFRInput.setText(data.MFR.ToBinaryString());
        Console.setText(IOString);
    }



}