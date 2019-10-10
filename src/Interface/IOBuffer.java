package Interface;

import javax.swing.*;

public class IOBuffer {
    private String Buffer;
    private String DeviceName;

    public IOBuffer(String DeviceName) {
        this.DeviceName = DeviceName;
        this.Buffer = "";
    }

    public void setBuffer(String input) {
        this.Buffer = input;
    }

    public void cleanBuffer() {
        Buffer = "";
    }

    public char getOneDigit() {
        char ret = Buffer.charAt(0);
        Buffer = Buffer.substring(1);
        return ret;
    }

    public int getLength() {
        return Buffer.length();
    }

    public boolean isEmpty() {
        return Buffer.length() == 0;
    }

    public void setBufferFromGUI() {
        JFrame frame = new JFrame(DeviceName + "Input Required!");
        String input = JOptionPane.showInputDialog(frame, "Please type in your input for " + DeviceName);
        input = input + "\n";
        setBuffer(input);
    }

}
