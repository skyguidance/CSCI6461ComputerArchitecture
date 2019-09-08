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
        componets.getMAR().setValue(componets.getPC().getValue());
        // MBR <- MEM[MAR]
        componets.getMBR().setValue(DataMemory.get(componets.getMAR().getValue()));
        // IR <- MBR
        componets.getIR().setValue(componets.getMAR().getValue());
        // CTRL-DECODE
        componets.getCU().decodeInstruction(componets.getIR().getValue());
        // ALU Process
        executeInstruction(CalculateEA());

    }



    private int CalculateEA() {
        int Address=componets.getCU().getAddress();

        if (componets.getCU().getI() == 0) {
            if (componets.getCU().getIX() == 0) {
                return Address;
            } else {
                return Address + componets.getCU().getIX();
            }
        } else if (componets.getCU().getI() == 1) {
            if (componets.getCU().getIX() == 0) {
               return DataMemory.get(Address);
            } else {
               return DataMemory.get(Address + componets.getCU().getIX());
            }
        } else {

            logging.severe("I has trouble");
            return -1;
        }
    }

    private void executeInstruction(int EA) {
        switch (componets.getCU().getOpcode()) {
            case 1: {
                componets.getGPRRegister().setValue(DataMemory.get(EA));
            }
            case 2:{
                // Store the GPR to MEM[EA].(Trigger DMWriteEnable.)
                DataMemory.set(EA,componets.getGPRRegister().getValue());
                break;
            }
            case 3:{
                // Write the EA address value into the specific Address_Register.
                componets.getAddressRegister().setValue(EA);
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
        componets.getPC().incrementOne();

    }
}
