package CPU;

import java.util.logging.Logger;

public class Instruction_Register {

    String IR;
    final Logger logging = Logger.getLogger("CPU.Instruction_Register");

    public Instruction_Register(String value) {
        this.IR = value;
    }

    public void set(String value) {
        if (value.length() == 16) {
            this.IR = value;
            logging.info("IR=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else if (value.length() < 16) {
            this.IR = String.format("%016d", Integer.valueOf(value));
            logging.info("IR=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else {
            logging.severe("INVALID: IR=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");
            return;
        }
    }

    public String get() {
        return this.IR;
    }

}
