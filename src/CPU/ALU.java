package CPU;

import Memory.Memory;

import java.util.logging.Logger;

/**
 * This is the ALU Class.
 * This module do mathematical or logical computation.
 */

public class ALU {
    private int output;
    private final Logger logging = Logger.getLogger("ALU");
    private Componets componets;
    private Memory dataMemory;

    public ALU(Componets componets, Memory dataMemory) {
        output = -1;
        this.componets = componets;
        this.dataMemory = dataMemory;
    }


}
