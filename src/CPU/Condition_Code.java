package CPU;

import java.util.logging.Logger;

/**
 * This is the Conditional Code Register Class.
 */
public class Condition_Code extends Register{

    private boolean cc;
    private final Logger logging = Logger.getLogger("CPU.Condition_Code");

    public Condition_Code(boolean value) {

        super(value? 1:0,2,"CC");
        cc=value;

    }
    public boolean getBoolean(){
        return cc;
    }

    public void set(boolean value) {
        this.cc = value;
        setValue(value? 1:0);
        logging.info("CC=>" + value);
        System.out.println("CC=>" + value);
    }

    public boolean get() {
        return this.cc;
    }

}
