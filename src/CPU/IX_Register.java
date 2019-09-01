package CPU;

import java.util.logging.Logger;

public class IX_Register {
    String IX;
    final Logger logging = Logger.getLogger("CPU.IX_Register");

    public IX_Register(String value) {
        this.IX = value;
    }

    public void set(String value) {
        if (value.length() == 16) {
            this.IX = value;
            logging.info("IX=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else if (value.length() < 16) {
            this.IX = String.format("%016d", Integer.valueOf(value));
            logging.info("IX=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else {
            logging.severe("INVALID: IX=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");
            return;
        }
    }

    public String get() {
        return this.IX;
    }

}
