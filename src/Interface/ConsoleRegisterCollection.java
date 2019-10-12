package Interface;

import CPU.Register;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * This is the Console Register Collection (For I/O Use.)
 */
public class ConsoleRegisterCollection {
    public ConsoleRegister[] Collections = new ConsoleRegister[29];
    final Logger logging = Logger.getLogger("ConsoleRegisterCollection");

    public ConsoleRegisterCollection() {
        // Init ConsoleRegisterCollection.
        for (int i = 0; i < 29; i++) {
            Collections[i] = new ConsoleRegister(0);
        }
    }

    /**
     * Get the specific Console Register Value.
     *
     * @param index The Console Register Index.
     * @return the value of the specific console Register.
     */
    public int getRegisterValue(int index) {
        return Collections[index].getValue();
    }

    /**
     * Set the specific Console Register Value from GUI.
     *
     * @param index the index of the console register.
     */
    public void setRegisterValue(int index) {
        Collections[index].setConsoleRegisterFromGUI();
    }

    /**
     * Dump the entire console register collection.
     * Print all the values inside it.
     */
    public void printCollection() {
        int value;
        for (int i = 0; i < 29; i++) {
            logging.info("DUMP:CR:" + i + "=>" + Collections[i].ToBinaryString() + "(" + Collections[i].getValue() + ")");
            System.out.println("DUMP:CR:" + i + "=>" + Collections[i].ToBinaryString() + "(" + Collections[i].getValue() + ")");
        }
    }

}

class ConsoleRegister extends Register {

    public ConsoleRegister(int value) {
        super(value, 16, "ConsoleRegister");
    }

    /**
     * Set the Console Register value from the GUI.
     */
    public void setConsoleRegisterFromGUI() {
        JFrame frame = new JFrame("Input Required!");
        String input = JOptionPane.showInputDialog(frame, "Please type in your input for ConsoleRegister(Only One Char)");
        setValue((int) input.charAt(0));
    }
}