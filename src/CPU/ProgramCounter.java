package CPU;

import java.util.logging.Logger;

/**
 * PC register
 */

public class ProgramCounter extends Register {
    public ProgramCounter(int value) {
        super(value, 12, "PC");
    }

    // PC++
    public void incrementOne() {
        int value = getValue() + 1;
        this.setValue(value);
    }
}
