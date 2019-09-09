package Computer;

import CPU.ALU;
import CPU.Componets;
import Memory.Memory;
import CPU.Bus;

import java.util.logging.Logger;

public class Simulator {

    public Componets componets;
    public Memory DataMemory;
    public ALU ALU;
    public Bus BUS;

    final Logger logging = Logger.getLogger("CPU.Simulator");

    public Simulator() {
        initialize();

    }

    public void initialize() {
        componets = new Componets();
        DataMemory = new Memory();
        ALU = new ALU(componets, DataMemory);
        BUS = new Bus(componets, DataMemory);
    }

    public void bootLoader() {

    }

    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        simulator.DataMemory.set(31,666);
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000011100011111", 2));
    }


}

