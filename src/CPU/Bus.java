package CPU;

import Memory.Memory;

import java.util.logging.Logger;

public class Bus {
    private final Logger logging = Logger.getLogger("Bus");
    private Componets componets;
    private Memory DataMemory;

    public Bus(Componets componets, Memory DataMemory) {
        this.componets = componets;
        this.DataMemory = DataMemory;
    }


    public void tik() {
        // MAR <- PC,PC++;
        componets.getMAR().setValue(componets.getPC().getValue());
        componets.getPC().incrementOne();
        // MBR <- MEM[MAR]
        componets.getMBR().setValue(DataMemory.get(componets.getMAR().getValue()));
        // IR <- MBR
        componets.getIR().setValue(componets.getMBR().getValue());
        // CTRL-DECODE
        componets.getCU().decodeInstruction(componets.getIR().getValue());
        // ALU Process
        executeInstruction(CalculateEA());
    }


    public void evaulateInstruction(int Instr){
        /***
         * This fuction is to set the specific Instr right to IR and do the rest.
         * Just for DEBUG only.
         * This fuction will by-pass PC.
         */
        componets.getIR().setValue(Instr);
        componets.getCU().decodeInstruction(componets.getIR().getValue());
        executeInstruction(CalculateEA());
    }


    private int CalculateEA() {
        int Address = componets.getCU().getAddress();

        if (componets.getCU().getI() == 0) {
            if (componets.getCU().getIX() == 0) {
                return Address;
            } else {
                return Address + componets.getIXRegister().getValue();
            }
        } else if (componets.getCU().getI() == 1) {
            if (componets.getCU().getIX() == 0) {
                return DataMemory.get(Address);
            } else {
                return DataMemory.get(Address + componets.getIXRegister().getValue());
            }
        } else {
            logging.severe("I has trouble");
            return -1;
        }
    }

    /**
     * ALU, DataMemory, WriteBack;
     *
     * @param EA
     */
    private void executeInstruction(int EA) {
        switch (componets.getCU().getOpcode()) {
            case 1: {
                //Load the MEM[EA] to specific GPR.
                componets.getGPRRegister().setValue(DataMemory.get(EA));
                break;
            }
            case 2: {
                // Store the GPR to MEM[EA].(Trigger DMWriteEnable.)
                DataMemory.set(EA, componets.getGPRRegister().getValue());
                break;
            }
            case 3: {
                // Write the EA address value into the specific Address_Register.
                componets.getGPRRegister().setValue(EA);
                break;
            }
            case 41: {
                // Write the IX register with MEM[EA].
                componets.getIXRegister().setValue(DataMemory.get(EA));
                break;
            }
            case 42: {
                // Store the IX to MEM[EA].
                DataMemory.set(EA, componets.getIXRegister().getValue());
                break;
            }
            default:
                break;
        }
    }

}
