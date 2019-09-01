package CPU;

import java.util.logging.Logger;

public class General_Purpose_Registers {
    String GPR;
    final Logger logging = Logger.getLogger("CPU.General_Purpose_Register");

    public General_Purpose_Registers(String value) {
        this.GPR = value;
    }

    public void set(String value) {
        if (value.length() == 16) {
            this.GPR = value;
            logging.info("GPR=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else if (value.length() < 16) {
            this.GPR = String.format("%016d", Integer.valueOf(value));
            logging.info("GPR=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else {
            logging.severe("INVALID: GPR=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");
            return;
        }
    }

    public String get() {
        return this.GPR;
    }

}
