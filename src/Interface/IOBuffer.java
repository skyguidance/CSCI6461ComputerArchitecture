package Interface;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * This is the Input/Output Buffer for simulator to handle I/O instr.
 */
public class IOBuffer {
    private String Buffer;
    private String DeviceName;

    public IOBuffer(String DeviceName) {
        this.DeviceName = DeviceName;
        this.Buffer = "";
    }

    /**
     * Set the current buffer with the String <input>
     *
     * @param input the input
     */
    public void setBuffer(String input) {
        this.Buffer = input;
    }

    /**
     * flush the buffer
     */
    public void cleanBuffer() {
        Buffer = "";
    }

    /**
     * Get one character of the Buffer.
     * IN instr will call this method to require a character.
     * Each time it will require the first character of the Buffer.
     *
     * @return the first character of the Buffer.
     */
    public char getOneDigit() {
        char ret = Buffer.charAt(0);
        Buffer = Buffer.substring(1);
        return ret;
    }

    /**
     * Get the Buffer length.
     *
     * @return the buffer length.
     */
    public int getLength() {
        return Buffer.length();
    }

    /**
     * Check if the buffer is currently empty.
     *
     * @return If is empty,return true, else false.
     */
    public boolean isEmpty() {
        return Buffer.length() == 0;
    }

    /**
     * Set the buffer from the GUI.
     * This will automatically add an "\n" to the end of the input for simulator to easy identification.
     */
    public void setBufferFromGUI() {
        JFrame frame = new JFrame(DeviceName + "Input Required!");
        String input = JOptionPane.showInputDialog(frame, "Please type in your input for " + DeviceName);
        input = input + "\n";
        setBuffer(input);
    }

    /**
     * Set the buffer from the File.
     * This function is usually called when IOBuffer class are initialized as a Card Reader.
     * (Which Card Reader is Simply a file IO)
     * !!!!! Please Note the File must contains 1 line of document and 1 line ONLY. !!!!!
     * This will automatically add an "\n" to the end of the input for the simulator to easy identification.
     */
    public void setBufferFromFile() {
        JFileChooser IoFileChooser = new JFileChooser();
        int select = IoFileChooser.showOpenDialog(null);
        if (select == IoFileChooser.APPROVE_OPTION) {
            // We get the file!
            File IOFile = IoFileChooser.getSelectedFile();
            System.out.println("[I//OLOAD]READING IO BUFFER FILE:" + IOFile.getName());
            // Read the file into the buffer.
            try {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(IOFile));
                BufferedReader br = new BufferedReader(reader);
                String line = null;
                line = br.readLine();
                // Check if the line end with "\n".
                // Sorry I don't understand the Java, So I don't know if readLine automatically add "\n" to the end.
                if (!line.endsWith("\n")) {
                    line = line + "\n";
                }
                setBuffer(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
