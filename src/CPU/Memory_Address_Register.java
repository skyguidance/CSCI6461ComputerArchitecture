package CPU;

import java.util.logging.Logger;

public class Memory_Address_Register {

    String MAR;
    final Logger logging = Logger.getLogger("CPU.Memory_Address_Register");

    public Memory_Address_Register(String value) {
        this.MAR = value;
    }

    public void set(String value) {
        if (value.length() == 16) {
            this.MAR = value;
            logging.info("MAR=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else if (value.length() < 16) {
            this.MAR = String.format("%016d", Integer.valueOf(value));
            logging.info("MAR=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else {
            logging.severe("INVALID: MAR=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");
            return;
        }
    }

    public String get() {
        return this.MAR;
    }

}
