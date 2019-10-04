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
    private JRadioButton CC0Button;
    private JRadioButton CC1Button;
    private JRadioButton CC2Button;
    private JRadioButton CC3Button;
    private JTextArea Console;
    private JPanel IOOutputPanel;
    private JButton LoadMEMButton;
    private JButton HALTButton;
    private JCheckBox ToDECcheckbox;
    private JCheckBox EXPANDMEMCheckbox;
    private JCheckBox GodViewButton;
    private JButton CacheButton;
    private JButton PrintConsoleRegisterButton;
    private JLabel CR;
    private Simulator simulator;
    private String IOString;

    /**
     * Set the GUI input to Decimal mode.
     * When this function is called. The all GUI inputs are limited to digits from 0~9.
     * And the input length could not be over the register/memory capability.
     */
    private void SetInputLimiterDEC() {
        //PCInput
        LimitedDocument PCInputLimiter = new LimitedDocument(4);
        PCInputLimiter.setAllowChar("1234567890");
        PCInput.setDocument(PCInputLimiter);
        //IRInput
        LimitedDocument IRInputLimiter = new LimitedDocument(5);
        IRInputLimiter.setAllowChar("1234567890");
        IRInput.setDocument(IRInputLimiter);
        //MARInput
        LimitedDocument MARInputLimiter = new LimitedDocument(5);
        MARInputLimiter.setAllowChar("1234567890");
        MARInput.setDocument(MARInputLimiter);
        //MBRInput
        LimitedDocument MBRInputLimiter = new LimitedDocument(5);
        MBRInputLimiter.setAllowChar("1234567890");
        MBRInput.setDocument(MBRInputLimiter);
        //MFRInput
        LimitedDocument MFRInputLimiter = new LimitedDocument(5);
        MFRInputLimiter.setAllowChar("1234567890");
        MFRInput.setDocument(MFRInputLimiter);
        //R0Input
        LimitedDocument R0InputLimiter = new LimitedDocument(5);
        R0InputLimiter.setAllowChar("1234567890");
        R0Input.setDocument(R0InputLimiter);
        //R1Input
        LimitedDocument R1InputLimiter = new LimitedDocument(5);
        R1InputLimiter.setAllowChar("1234567890");
        R1Input.setDocument(R1InputLimiter);
        //R2Input
        LimitedDocument R2InputLimiter = new LimitedDocument(5);
        R2InputLimiter.setAllowChar("1234567890");
        R2Input.setDocument(R2InputLimiter);
        //R3Input
        LimitedDocument R3InputLimiter = new LimitedDocument(5);
        R3InputLimiter.setAllowChar("1234567890");
        R3Input.setDocument(R3InputLimiter);
        //IX1Input
        LimitedDocument IX1InputLimiter = new LimitedDocument(5);
        IX1InputLimiter.setAllowChar("1234567890");
        IX1Input.setDocument(IX1InputLimiter);
        //IX2Input
        LimitedDocument IX2InputLimiter = new LimitedDocument(5);
        IX2InputLimiter.setAllowChar("1234567890");
        IX2Input.setDocument(IX2InputLimiter);
        //IX3Input
        LimitedDocument IX3InputLimiter = new LimitedDocument(5);
        IX3InputLimiter.setAllowChar("1234567890");
        IX3Input.setDocument(IX3InputLimiter);
        //DMValueInput
        LimitedDocument DMValueInputLimiter = new LimitedDocument(5);
        DMValueInputLimiter.setAllowChar("1234567890");
        DMValueInput.setDocument(DMValueInputLimiter);
        //DM-Address
        LimitedDocument DMAddLimiter = new LimitedDocument(5);
        DMAddLimiter.setAllowChar("1234567890");
        DMAddressInput.setDocument(DMAddLimiter);
        //Flush GUI
        flushDataDEC(simulator.componets);
        DMValueInput.setText("0000000000000000");
        DMAddressInput.setText("000000000000");
    }

    /**
     * Set the GUI input to BINARY mode.
     * When this function is called. The all GUI inputs are limited to digits 0 and 1.
     * And the input length could not be over the register/memory capability.
     */
    private void SetInputLimiter() {
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

    /**
     * GUI central method.
     * Listen to the input, mouse clicking on the button, etc.
     */
    public GUI() {

        //Load MEM FileChooser.. (LOAD MEM BUTTION, pop up a window to choose file from your computer.)
        JFileChooser MEMFileChooser = new JFileChooser();

        simulator = new Simulator();

        // Default set to Binary Input Limit mode.
        SetInputLimiter();

        // Redirect all system output stream to the GUI Console display.
        redirectSystemStreams();


        IOString = "";

        // When the PC Input is changed and enter is pressed...
        PCInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == PCInput) {
                    // Set the PC value.
                    simulator.componets.PC.setValue(PCInput.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "PC=>" + PCInput.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the IR Input is changed and enter is pressed...
        IRInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == IRInput) {
                    // Set the IR value.
                    simulator.componets.IR.setValue(IRInput.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "IR=>" + IRInput.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the MAR Input is changed and enter is pressed...
        MARInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == MARInput) {
                    // Set the MAR value.
                    simulator.componets.MAR.setValue(MARInput.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "MAR=>" + MARInput.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the MBR Input is changed and enter is pressed...
        MBRInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == MBRInput) {
                    // Set the MBR value.
                    simulator.componets.MBR.setValue(MBRInput.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "MBR=>" + MBRInput.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the MFR Input is changed and enter is pressed...
        MFRInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == MFRInput) {
                    // Set the MFR value.
                    simulator.componets.MFR.setValue(MFRInput.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "MFR=>" + MFRInput.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the GPR 0 Input is changed and enter is pressed...
        R0Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == R0Input) {
                    // Set the R0 value.
                    simulator.componets.R0.setValue(R0Input.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "R0=>" + R0Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the GPR 1 Input is changed and enter is pressed...
        R1Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == R1Input) {
                    // Set the R1 value.
                    simulator.componets.R1.setValue(R1Input.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "R1=>" + R1Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the GPR 2 Input is changed and enter is pressed...
        R2Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == R2Input) {
                    // Set the R2 value.
                    simulator.componets.R2.setValue(R2Input.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "R2=>" + R2Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the GPR 3 Input is changed and enter is pressed...
        R3Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == R3Input) {
                    // Set the R3 value.
                    simulator.componets.R3.setValue(R3Input.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "R3=>" + R3Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the IX 1 Input is changed and enter is pressed...
        IX1Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == IX1Input) {
                    // Set the IX1 value.
                    simulator.componets.IX1.setValue(IX1Input.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "IX1=>" + IX1Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the IX 2 Input is changed and enter is pressed...
        IX2Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == IX2Input) {
                    // Set the IX2 value.
                    simulator.componets.IX2.setValue(IX2Input.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "IX2=>" + IX2Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the IX 3 Input is changed and enter is pressed...
        IX3Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == IX3Input) {
                    // Set the IX3 value.
                    simulator.componets.IX3.setValue(IX3Input.getText());
                    // Flush GUI Interface.
                    IOString = IOString + "\n" + "IX3=>" + IX3Input.getText();
                    flushData(simulator.componets);
                }
            }
        });

        // When the Data Memory READ button clicked...
        DMReadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == DMReadButton) {
                    // Check if the input is in Decimal mode.
                    if (ToDECcheckbox.isSelected()) {
                        // Read the Memory. MEM[DMAddressInput].
                        int Memdata = simulator.DataMemory.get(Integer.valueOf(DMAddressInput.getText()));
                        // Update the data to the GUI.
                        DMValueInput.setText(String.valueOf(Memdata));
                    }
                    // the input is in Binary Mode.
                    else {
                        // Read the Memory. MEM[DMAddressInput].
                        int Memdata = simulator.DataMemory.get(DMAddressInput.getText());
                        // Update the data to the GUI.
                        DMValueInput.setText(simulator.DataMemory.ToBinaryString(Memdata));
                    }
                    // Flush GUI Interface.
                    flushData(simulator.componets);
                }

            }
        });

        // When the Data Memory WRITE button clicked...
        DMwriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == DMwriteButton) {
                    // Check if the input is in Decimal mode.
                    if (ToDECcheckbox.isSelected()) {
                        // Write the Memory. (Security check is performed in the backend.)
                        simulator.DataMemory.UserSet(DMAddressInput.getText(), DMValueInput.getText(), false);
                        IOString = IOString + "\n" + "MEM[" + DMAddressInput.getText() + "]=>" + DMValueInput.getText();
                    }
                    // the input is in Binary Mode.
                    else {
                        // Write the Memory. (Security check is performed in the backend.)
                        simulator.DataMemory.UserSet(DMAddressInput.getText(), DMValueInput.getText(), true);
                        IOString = IOString + "\n" + "MEM[" + DMAddressInput.getText() + "]=>" + DMValueInput.getText();
                    }
                    // Flush GUI Interface.
                    flushData(simulator.componets);
                }

            }
        });

        // When the IPL button clicked...
        IPLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == IPLButton) {
                    // Create a new simulator instance. (New Computer)
                    simulator = new Simulator();
                    // Flush GUI Interface.
                    IOString = "";
                    flushData(simulator.componets);
                    DMValueInput.setText("0000000000000000");
                    DMAddressInput.setText("000000000000");
                }

            }
        });

        // When the RUN button clicked...
        RUNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == RUNButton) {
                    // Call the simulator RUN. (Execute the whole program. Break when it meets HALT.)
                    simulator.BUS.run();
                    // Flush GUI Interface.
                    flushData(simulator.componets);
                }

            }
        });

        // When the HALT button clicked...
        HALTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == HALTButton) {
                    // Call the simulator to set HALT.
                    simulator.BUS.setHalt();
                    // Flush GUI Interface.
                    flushData(simulator.componets);
                }

            }
        });

        // When the SINGLE-STEP button clicked...
        SINGLESTEPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == SINGLESTEPButton) {
                    // Call simulator to tik (execute one instr.)
                    simulator.BUS.tik();
                    // Flush GUI Interface.
                    flushData(simulator.componets);
                }

            }
        });

        // When the LoadMEM button clicked...
        LoadMEMButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == LoadMEMButton) {
                    // Pop-up a window to select the Memory mapping file.
                    int select = MEMFileChooser.showOpenDialog(null);
                    if (select == MEMFileChooser.APPROVE_OPTION) {
                        // We get the file!
                        File MEMFile = MEMFileChooser.getSelectedFile();
                        System.out.println("[MEMLOAD]MAPPING MEM FROM FILE:" + MEMFile.getName());
                        // Call the MEM load function.
                        simulator.loadMEMfromFile(MEMFile);
                    }
                }
            }
        }));

        // When the Print Cache button clicked...
        CacheButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == CacheButton) {
                    //Print All the Cache Info to the console.
                    simulator.DataMemory.PrintCache();
                }
            }
        }));

        // When the To Decimal clickbox clicked... (BETA ONLY)
        ToDECcheckbox.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ToDECcheckbox) {
                    if (ToDECcheckbox.isSelected()) {
                        // Set to decimal.
                        SetInputLimiterDEC();
                        flushDataDEC(simulator.componets);

                    } else {
                        // Set to binary.
                        SetInputLimiter();
                        flushDataBIN(simulator.componets);
                    }
                }
            }
        }));

        // When the GodViewButton clickbox clicked...
        GodViewButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == GodViewButton) {
                    if (GodViewButton.isSelected()) {
                        // Preform to GOD mode. Could do set to any register and BETA feature.
                        ToDECcheckbox.setEnabled(true);
                        IRInput.setEditable(true);
                        CC0Button.setEnabled(true);
                        CC1Button.setEnabled(true);
                        CC2Button.setEnabled(true);
                        CC3Button.setEnabled(true);
                        MFRInput.setEditable(true);
                        System.out.println("GOD VIEW:DUMPING CURRENT MEM INFO...");
                        simulator.DataMemory.PrintHashMap();
                    } else {
                        // Change to normal mode.
                        ToDECcheckbox.setSelected(false);
                        ToDECcheckbox.setEnabled(false);
                        IRInput.setEditable(false);
                        CC0Button.setEnabled(false);
                        CC1Button.setEnabled(false);
                        CC2Button.setEnabled(false);
                        CC3Button.setEnabled(false);
                        MFRInput.setEditable(false);
                    }
                }
            }
        }));

        //When the EXPAND-MEM clickbox clicked...
        EXPANDMEMCheckbox.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == EXPANDMEMCheckbox) {
                    if (EXPANDMEMCheckbox.isSelected()) {
                        // Expand the memory to 4096.
                        simulator.DataMemory.expandMEM();
                    } else {
                        // Shrink to 2048.
                        simulator.DataMemory.shrinkMEM();
                    }
                }
            }
        }));

        PrintConsoleRegisterButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == PrintConsoleRegisterButton) {
                    //Print All the Cache Info to the console.
                    simulator.BUS.ConsoleRegisterCollection.printCollection();
                }
            }
        }));
    }

    /**
     * MAIN Method.
     *
     * @param args
     */
    public static void main(String args[]) {
        JFrame jFrame = new JFrame("CSCI6461 Computer Simulator");
        GUI gui = new GUI();
        jFrame.setContentPane(gui.panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    /**
     * Flush the GUI User Interface.
     *
     * @param data
     */
    public void flushData(Componets data) {
        //  Check whether is Decimal of Binary Display mode.
        if (ToDECcheckbox.isSelected()) {
            //DEC MODE
            flushDataDEC(data);
        } else {
            //BIN MODE
            flushDataBIN(data);
        }
    }

    /**
     * Flush The GUI Interface to Binary.
     *
     * @param data
     */
    public void flushDataBIN(Componets data) {
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
        CC0Button.setSelected(data.CC0.get());
        CC1Button.setSelected(data.CC1.get());
        CC2Button.setSelected(data.CC2.get());
        CC3Button.setSelected(data.CC3.get());
    }

    /**
     * Flush The GUI Interface to Decimal.
     *
     * @param data
     */
    public void flushDataDEC(Componets data) {
        redirectSystemStreams();
        PCInput.setText("" + data.PC.getValue());
        IRInput.setText("" + data.IR.getValue());
        MARInput.setText("" + data.MAR.getValue());
        MBRInput.setText("" + data.MBR.getValue());
        MFRInput.setText("" + data.MFR.getValue());
        R0Input.setText("" + data.R0.getValue());
        R1Input.setText("" + data.R1.getValue());
        R2Input.setText("" + data.R2.getValue());
        R3Input.setText("" + data.R3.getValue());
        IX1Input.setText("" + data.IX1.getValue());
        IX2Input.setText("" + data.IX2.getValue());
        IX3Input.setText("" + data.IX3.getValue());
        if (DMAddressInput.getText().equals("")) {
            DMAddressInput.setText("0");
        } else {
            DMAddressInput.setText("" + Integer.valueOf(DMAddressInput.getText(), 2));
        }
        if (DMValueInput.getText().equals("")) {
            DMValueInput.setText("0");
        } else {
            DMValueInput.setText("" + Integer.valueOf(DMValueInput.getText(), 2));
        }
        CC0Button.setSelected(data.CC0.get());
        CC1Button.setSelected(data.CC1.get());
        CC2Button.setSelected(data.CC2.get());
        CC3Button.setSelected(data.CC3.get());
    }


    /**
     * Used by Console IO redirect.
     * (Redirect all System Output to GUI)
     * This function is to flush the GUI output.
     *
     * @param text the updated text.
     */
    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Console.append(text);
            }
        });
    }

    /**
     * Used by Console IO redirect.
     * (Redirect all System Output to GUI)
     * This function is to preform the redirect.
     */
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
        //DEBUG ONLY:Redirect the Error Info to GUI-IO.
        System.setErr(new PrintStream(out, true));
    }

}

