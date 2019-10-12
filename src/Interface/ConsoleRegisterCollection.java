package Interface;

import CPU.Register;

import javax.swing.*;
import java.util.logging.Logger;

/*
this is consoleRegisters
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

    public int getRegisterValue(int index) {
        return Collections[index].getValue();
    }

    public void setRegisterValue(int index) {
        Collections[index].setConsoleRegisterFromGUI();
    }

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

    public void setConsoleRegisterFromGUI() {
        JFrame frame = new JFrame("Input Required!");
        String input = JOptionPane.showInputDialog(frame, "Please type in your input for ConsoleRegister(Only One Char)");
        setValue((int)input.charAt(0));
    }
}