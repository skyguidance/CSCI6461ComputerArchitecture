package CPU;

import Memory.Memory;

import java.util.logging.Logger;

/**
 * The DataBus Class.The entire data path is implemented here.
 */
public class Bus {
    private final Logger logging = Logger.getLogger("Bus");
    private Componets componets;
    private Memory dataMemory;
    private boolean isHalt = false;

    public Bus(Componets componets, Memory dataMemory) {
        this.componets = componets;
        this.dataMemory = dataMemory;
    }

    /**
     * Get the Bus Status. Return True if HALT flag(isHalt) is set.
     *
     * @return true if is halt.
     */
    public boolean getHaltStatus() {
        return isHalt;
    }

    /**
     * Manually set the Halt Status.
     * When this method is called. The HALT flag is set to True.
     */
    public void setHalt() {
        isHalt = true;
    }

    /**
     * Execute one instruction.
     */
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
        // Execute Process
        executeInstruction(calculateEA());
    }

    /**
     * Execute the whole program. Break when it meets HALT.
     * *TIPS* When it comes to empty Memory, the DataMemory Components will return 16'b0. This is HALT itself!
     * Stop Criteria: HALT(Both HALT Instr and Manually set from GUI), Program Drop out of the bottom.
     */
    public void run() {
        while (true) {
            tik();
            if (isHalt) {
                break;
            }
        }
    }

    /***
     * !!! DEBUG USE. !!!
     * This method is to set the specific Instr right to IR and do the rest.
     * Just for DEBUG only.
     * This fuction will by-pass PC.
     */
    public void evaulateInstruction(int instruction) {
        componets.getIR().setValue(instruction);
        componets.getCU().decodeInstruction(componets.getIR().getValue());
        executeInstruction(calculateEA());
    }


    /**
     * Effective Address (EA) Calculator
     * After CTRL Module decode the Instr to CTRL signal, Call this method to calucate EA.
     *
     * @return The Effective Address.(Int)
     */
    private int calculateEA() {
        // Get Address (Immediate Value from Instr), the last 5 digits.
        int address = componets.getCU().getAddress();
        // NOT Indirect Addressing.
        if (componets.getCU().getI() == 0) {
            // Check IX.
            if (componets.getCU().getIX() == 0) {
                return address;
            } else {
                return address + componets.getIXRegister().getValue();
            }
        }
        // Indirect Addressing.
        else if (componets.getCU().getI() == 1) {
            // Check IX.
            if (componets.getCU().getIX() == 0) {
                return dataMemory.get(address);
            } else {
                return dataMemory.get(address + componets.getIXRegister().getValue());
            }
        }
        // Else... Return Severe Error Accidents.
        else {
            logging.severe("I has trouble");
            System.out.println("I has trouble");
            return -1;
        }
    }

    /**
     * Do the Rest work to execute one Instruction.
     *
     * @param ea The Effective Address.(int)
     */
    private void executeInstruction(int ea) {
        //GET OPCODE to distinguish Instruction.
        switch (componets.getCU().getOpcode()) {
            case 0: {
                //HALT
                isHalt = true;
                componets.PC.setValue(6); // Reset PC
                System.out.println("BUS:HALT");
                break;
            }
            case 1: {
                //Load the MEM[EA] to specific GPR.
                componets.getGPRRegister().setValue(dataMemory.get(ea));
                break;
            }
            case 2: {
                // Store the GPR to MEM[EA].
                dataMemory.set(ea, componets.getGPRRegister().getValue(), true);
                break;
            }
            case 3: {
                // Write the EA address value into the specific Address_Register.
                componets.getGPRRegister().setValue(ea);
                break;
            }
            case 4: {

                break;
            }
            case 5: {

                break;
            }
            case 6: {

                break;
            }
            case 7: {

                break;
            }
            case 10: {

                break;
            }
            case 11: {

                break;
            }
            case 12: {

                break;
            }
            case 13: {

                break;
            }
            case 14: {

                break;
            }
            case 15: {

                break;
            }
            case 16: {

                break;
            }
            case 17: {

                break;
            }
            case 20: {

                break;
            }
            case 21: {

                break;
            }
            case 22: {

                break;
            }
            case 23: {

                break;
            }
            case 24: {

                break;
            }
            case 25: {

                break;
            }
            case 31: {

                break;
            }
            case 32: {

                break;
            }
            case 33: {

                break;
            }
            case 34: {

                break;
            }
            case 35: {

                break;
            }
            case 36: {

                break;
            }
            case 37: {

                break;
            }
            case 41: {
                // Write the IX register with MEM[EA].
                componets.getIXRegister().setValue(dataMemory.get(ea));
                break;
            }
            case 42: {
                // Store the IX to MEM[EA].
                dataMemory.set(ea, componets.getIXRegister().getValue(), true);
                break;
            }
            case 50: {

                break;
            }
            case 51: {

                break;
            }
            case 61: {

                break;
            }
            case 62: {

                break;
            }
            case 63: {

                break;
            }
            default:
                break;
        }
    }

}
