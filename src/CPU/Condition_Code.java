package CPU;

import java.util.logging.Logger;

/**
 * This is the Conditional Code Register Class.
 */
public class Condition_Code {

    private boolean cc;
    private final Logger logging = Logger.getLogger("CPU.Condition_Code");

    public Condition_Code(boolean value) {
        cc = value;
    }

    public void set(boolean value) {
        this.cc = value;
        logging.info("CC=>" + value);
        System.out.println("CC=>" + value);
    }

    public boolean get() {
        return this.cc;
    }

}
