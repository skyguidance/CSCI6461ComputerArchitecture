package CPU;

import Memory.Memory;

import java.util.logging.Logger;

public class Bus {
    private final Logger logging = Logger.getLogger("Bus");
    private Componets componets;
    private Memory dataMemory;

    public Bus(Componets componets, Memory dataMemory) {
        this.componets = componets;
        this.dataMemory = dataMemory;
    }


    public void tik() {
        // MAR <- PC,PC++;
        componets.getMAR().setValue(componets.getPC().getValue());
        componets.getPC().incrementOne();
        // MBR <- MEM[MAR]
        componets.getMBR().setValue(dataMemory.get(componets.getMAR().getValue()));
        // IR <- MBR
        componets.getIR().setValue(componets.getMBR().getValue());
        // CTRL-DECODE
        componets.getCU().decodeInstruction(componets.getIR().getValue());
        // ALU Process
        executeInstruction(calculateEA());
    }


    public void evaulateInstruction(int instruction){
        /***
         * This fuction is to set the specific Instr right to IR and do the rest.
         * Just for DEBUG only.
         * This fuction will by-pass PC.
         */
        componets.getIR().setValue(instruction);
        componets.getCU().decodeInstruction(componets.getIR().getValue());
        executeInstruction(calculateEA());
    }


    private int calculateEA() {
        int address = componets.getCU().getAddress();

        if (componets.getCU().getI() == 0) {
            if (componets.getCU().getIX() == 0) {
                return address;
            } else {
                return address + componets.getIXRegister().getValue();
            }
        } else if (componets.getCU().getI() == 1) {
            if (componets.getCU().getIX() == 0) {
                return dataMemory.get(address);
            } else {
                return dataMemory.get(address + componets.getIXRegister().getValue());
            }
        } else {
            logging.severe("I has trouble");
            System.out.println("I has trouble");
            return -1;
        }
    }

    /**
     * ALU, DataMemory, WriteBack;
     *
     * @param EA
     */
    private void executeInstruction(int ea) {
        switch (componets.getCU().getOpcode()) {
            case 1: {
                //Load the MEM[EA] to specific GPR.
                componets.getGPRRegister().setValue(dataMemory.get(ea));
                break;
            }
            case 2: {
                // Store the GPR to MEM[EA].(Trigger DMWriteEnable.)
                dataMemory.set(ea, componets.getGPRRegister().getValue());
                break;
            }
            case 3: {
                // Write the EA address value into the specific Address_Register.
                componets.getGPRRegister().setValue(ea);
                break;
            }
            case 41: {
                // Write the IX register with MEM[EA].
                componets.getIXRegister().setValue(dataMemory.get(ea));
                break;
            }
            case 42: {
                // Store the IX to MEM[EA].
                dataMemory.set(ea, componets.getIXRegister().getValue());
                break;
            }
            default:
                break;
        }
    }

}
