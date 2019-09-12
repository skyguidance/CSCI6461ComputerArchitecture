package Interface;

import CPU.Componets;
import Computer.Simulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;


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
    private JButton LoadMEMButton;
    private Simulator simulator;
    private String IOString;


    private void SetInputLimiter(){
        //PCInput
        LimitedDocument PCInputLimiter = new LimitedDocument(12);
        PCInputLimiter.setAllowChar("01");
        PCInput.setDocument(PCInputLimiter);
        //IRInput
        LimitedDocument IRInputLimiter = new LimitedDocument(16);
        IRInputLimiter.setAllowChar("01");
        IRInput.setDocument(IRInputLimiter);
        //MARInput
        LimitedDocument MARInputLimiter = new LimitedDocument(16);
        MARInputLimiter.setAllowChar("01");
        MARInput.setDocument(MARInputLimiter);
        //MBRInput
        LimitedDocument MBRInputLimiter = new LimitedDocument(16);
        MBRInputLimiter.setAllowChar("01");
        MBRInput.setDocument(MBRInputLimiter);
        //MFRInput
        LimitedDocument MFRInputLimiter = new LimitedDocument(16);
        MFRInputLimiter.setAllowChar("01");
        MFRInput.setDocument(MFRInputLimiter);
        //R0Input
        LimitedDocument R0InputLimiter = new LimitedDocument(16);
        R0InputLimiter.setAllowChar("01");
        R0Input.setDocument(R0InputLimiter);
        //R1Input
        LimitedDocument R1InputLimiter = new LimitedDocument(16);
        R1InputLimiter.setAllowChar("01");
        R1Input.setDocument(R1InputLimiter);
        //R2Input
        LimitedDocument R2InputLimiter = new LimitedDocument(16);
        R2InputLimiter.setAllowChar("01");
        R2Input.setDocument(R2InputLimiter);
        //R3Input
        LimitedDocument R3InputLimiter = new LimitedDocument(16);
        R3InputLimiter.setAllowChar("01");
        R3Input.setDocument(R3InputLimiter);
        //IX1Input
        LimitedDocument IX1InputLimiter = new LimitedDocument(16);
        IX1InputLimiter.setAllowChar("01");
        IX1Input.setDocument(IX1InputLimiter);
        //IX2Input
        LimitedDocument IX2InputLimiter = new LimitedDocument(16);
        IX2InputLimiter.setAllowChar("01");
        IX2Input.setDocument(IX2InputLimiter);
        //IX3Input
        LimitedDocument IX3InputLimiter = new LimitedDocument(16);
        IX3InputLimiter.setAllowChar("01");
        IX3Input.setDocument(IX3InputLimiter);
        //DMValueInput
        LimitedDocument DMValueInputLimiter = new LimitedDocument(16);
        DMValueInputLimiter.setAllowChar("01");
        DMValueInput.setDocument(DMValueInputLimiter);
        //DM-Address
        LimitedDocument DMAddLimiter = new LimitedDocument(12);
        DMAddLimiter.setAllowChar("01");
        DMAddressInput.setDocument(DMAddLimiter);
        //Flush GUI
        flushData(simulator.componets);
        DMValueInput.setText("0000000000000000");
        DMAddressInput.setText("000000000000");
    }

    public GUI() {
        //Load MEM FileChooser..
        JFileChooser MEMFileChooser = new JFileChooser();


        simulator = new Simulator();
        SetInputLimiter();
        redirectSystemStreams();

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
                    simulator.DataMemory.UserSet(DMAddressInput.getText(),DMValueInput.getText());
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

        LoadMEMButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == LoadMEMButton){
                    int select = MEMFileChooser.showOpenDialog(null);
                    if(select == MEMFileChooser.APPROVE_OPTION){
                        File MEMFile = MEMFileChooser.getSelectedFile();
                        System.out.println("[MEMLOAD]MAPPING MEM FROM FILE:"+MEMFile.getName());
                        simulator.loadMEMfromFile(MEMFile);
                    }
                }
            }
        }));



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
        //DEBUG ONLY:Redirect the Error to GUI-IO.
        System.setErr(new PrintStream(out, true));
    }

}

