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
        simulator.DataMemory.set(666,123);
        //LDR
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000011100111111", 2));
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000011000111111", 2));
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000010100111111", 2));
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000010000111111", 2));
        //STR
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000101100011111", 2));
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000101000011111", 2));
        //LDA
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000111100011111", 2));
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000111000111111", 2));
        //LDX
        simulator.BUS.evaulateInstruction(Integer.valueOf("1010010001011111", 2));
        //NOP
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000000000000000", 2));
    }


}

