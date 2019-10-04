package Interface;

import CPU.Register;

import javax.swing.*;

public class ConsoleRegisterCollection {
    public ConsoleRegister[] Collections = new ConsoleRegister[29];

    public int getRegisterValue(int index){
        return Collections[index].getValue();
    }

    public void setRegisterValue(int index){
        Collections[index].setConsoleRegisterFromGUI();
    }


}

class ConsoleRegister extends Register{

    public ConsoleRegister(int value){
        super(value, 16, "ConsoleRegister");
    }

    public void setConsoleRegisterFromGUI(){
        JFrame frame = new JFrame("Input Required!");
        String input = JOptionPane.showInputDialog(frame, "Please type in your input");
        setValue(input);
    }
}