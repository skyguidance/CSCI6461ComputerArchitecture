package CPU;

import java.util.logging.Logger;

public class Memory_Buffer_Register {
    String MBR;
    final Logger logging = Logger.getLogger("CPU.Memory_Buffer_Register");

    public Memory_Buffer_Register(String value) {
        this.MBR = value;
    }

    public void set(String value) {
        if (value.length() == 16) {
            this.MBR = value;
            logging.info("MBR=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else if (value.length() < 16) {
            this.MBR = String.format("%016d", Integer.valueOf(value));
            logging.info("MBR=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else {
            logging.severe("INVALID: MBR=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");
            return;
        }
    }

    public String get() {
        return this.MBR;
    }
}
