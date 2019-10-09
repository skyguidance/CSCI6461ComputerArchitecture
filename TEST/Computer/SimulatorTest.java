package Computer;

import CPU.ALU;
import CPU.Bus;
import CPU.Componets;
import Memory.Memory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorTest {
 private Simulator test = new Simulator ();


   public Componets componets = new Componets();;
   public Memory DataMemory = new Memory();
   public CPU.ALU ALU = new ALU(componets, DataMemory);
   public Bus BUS = new Bus(componets, DataMemory);




    void initialize() {
       componets = new Componets();
       DataMemory = new Memory();
       ALU = new ALU(componets, DataMemory);
       BUS = new Bus(componets, DataMemory);

    }


}