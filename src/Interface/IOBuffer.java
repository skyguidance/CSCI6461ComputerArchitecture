package Interface;

import javax.swing.*;

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

}
