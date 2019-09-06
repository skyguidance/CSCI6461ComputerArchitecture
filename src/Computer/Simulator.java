package Computer;
import CPU.ALU;
import CPU.Componets;
import Memory.Memory;

import java.util.logging.Logger;

public class Simulator {

    private Componets componets;
    private Memory DataMemory;
    private ALU ALU;

    final Logger logging = Logger.getLogger("CPU.Simulator");

    public Simulator() {
        initialize();

    }
    public void initialize(){
        componets=new Componets();
        DataMemory=new Memory();
        ALU=new ALU(componets,DataMemory);
	}







}

