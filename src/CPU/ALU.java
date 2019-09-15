package CPU;

import Memory.Memory;

import java.util.logging.Logger;

public class ALU {
    private int output;
    private final Logger logging = Logger.getLogger("SimulatorLOG");
    private Componets componets;
    private Memory dataMemory;

    public ALU(Componets componets,Memory dataMemory){
        output = -1;

        this.componets=componets;
        this.dataMemory=dataMemory;
    }


}
