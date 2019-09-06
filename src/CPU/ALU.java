package CPU;

import Memory.Memory;

import java.util.logging.Logger;

public class ALU {
    private int output;
    private final Logger logging = Logger.getLogger("SimulatorLOG");
    //int opcode;
    private Componets componets;
   private Memory DataMemory;

    public ALU(Componets componets,Memory DataMemory){
        output = -1;
       // Simulator sim=new Simulator();
        this.componets=componets;
        this.DataMemory=DataMemory;

    }
    public void operator() {
        // MAR <- PC
        componets.MAR.setValue(componets.PC.getValue());
        // MBR <- MEM[MAR]
        componets.MBR.setValue(DataMemory.get(componets.MAR.getValue()));
        // IR <- MBR
        componets.IR.setValue(componets.MAR.getValue());
        // CTRL-DECODE
        componets.CU.decodeInstruction(componets.IR.getValue());
        // ALU Process
        executeInstruction(CalculateEA());
        componets.PC.incrementOne();
    }



    private int CalculateEA() {
        int Address=componets.CU.getAddress();

        if (componets.CU.getI() == 0) {
            if (componets.CU.getIX() == 0) {
                return Address;
            } else {
                return Address + componets.CU.getIX();
            }
        } else if (componets.CU.getI() == 1) {
            if (componets.CU.getIX() == 0) {
               return DataMemory.get(Address);
            } else {
               return DataMemory.get(Address + componets.CU.getIX());
            }
        } else {

            logging.severe("I has trouble");
            return -1;
        }
    }

    private void executeInstruction(int EA) {
        switch (componets.CU.getOpcode()) {
            case 1: {
                componets.getGPRRegister().setValue(DataMemory.get(EA));
            }
            case 2:{
                // Store the GPR to MEM[EA].(Trigger DMWriteEnable.)
                DataMemory.set(EA,componets.getGPRRegister().getValue());
                break;
            }
            case 3:{
                // Write the EA address value into the specific GPR.
                componets.getGPRRegister().setValue(EA);
                break;
            }
            case 41:{
                // Write the IX register with MEM[EA].

                componets.getIXRegister().setValue(DataMemory.get(EA));
                break;
            }
            case 42:{
                // Store the IX to MEM[EA].
                DataMemory.set(EA,componets.getIXRegister().getValue());
                break;
            }
            default:
                break;
        }
    }
}
