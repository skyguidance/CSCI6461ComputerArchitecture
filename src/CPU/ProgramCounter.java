package CPU;

import java.util.logging.Logger;


public class ProgramCounter {
    String pc;
    final Logger logging = Logger.getLogger("CPU.ProgramCounter");

    public ProgramCounter(String value) {
        this.pc = value;
    }

    public void set(String value) {
        if (value.length() == 12) {
            this.pc = value;
            logging.info("PC=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else if (value.length() < 12) {
            this.pc = String.format("%012d", Integer.valueOf(value));
            logging.info("PC=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else {
            logging.severe("INVALID: PC=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");
            return;
        }
    }

    public String get() {
        return this.pc;
    }

    public void increment() {
        int realPC = Integer.valueOf(this.pc, 2);
        realPC++;
        set(Integer.toBinaryString(realPC));
        return;
    }

}
