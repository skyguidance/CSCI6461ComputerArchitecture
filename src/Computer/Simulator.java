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


        //TESTING AUTORUN...
        System.out.println("Testing Autotest...");
        simulator.DataMemory.set(2000,"0000011100111111");
        simulator.DataMemory.set(2001,"0000011000111111");
        simulator.DataMemory.set(2002,"0000010100111111");
        simulator.DataMemory.set(2003,"0000010000111111");
        simulator.componets.PC.setValue(2000);
        int i=0;
        for (i=0;i<4;i++){
            simulator.BUS.tik();
        }



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
        simulator.DataMemory.set(30,789);
        simulator.BUS.evaulateInstruction(Integer.valueOf("1010010001011110", 2));
        //STX
        simulator.componets.IX1.setValue(987);
        simulator.BUS.evaulateInstruction(Integer.valueOf("1010100001011110", 2));
        //NOP
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000000000000000", 2));
    }


}

