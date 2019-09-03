package CPU;

import java.util.logging.Logger;


public class ProgramCounter extends Register{
    public ProgramCounter(int value) {
        super(value,12,"PC");
    }

    public void incrementOne() {
        value++;
    }
}
