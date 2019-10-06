package CPU;

import Interface.ConsoleRegisterCollection;
import Interface.IOBuffer;
import Memory.Memory;

import javax.smartcardio.Card;
import java.util.logging.Logger;

/**
 * The DataBus Class.The entire data path is implemented here.
 */
public class Bus {
    private final Logger logging = Logger.getLogger("Bus");
    private Componets componets;
    private Memory dataMemory;
    private boolean isHalt = false;
    private IOBuffer KeyboardBuffer = new IOBuffer("Keyboard");
    private IOBuffer CardReaderBuffer = new IOBuffer("Card Reader");
    public ConsoleRegisterCollection ConsoleRegisterCollection = new ConsoleRegisterCollection();

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
                // AMR. Add Memory To Register.
                componets.ALU.Calc(componets.getCU().getOpcode(), componets.getGPRRegister().getValue(), dataMemory.get(ea));
                componets.CC0.set(componets.ALU.CC0);
                componets.getGPRRegister().setValue(componets.ALU.output, componets.ALU.CC0);
                break;
            }
            case 5: {
                // SMR. Subtract Memory From Register.
                componets.ALU.Calc(componets.getCU().getOpcode(), componets.getGPRRegister().getValue(), dataMemory.get(ea));
                componets.CC1.set(componets.ALU.CC1);
                componets.getGPRRegister().setValue(componets.ALU.output);
                break;
            }
            case 6: {
                // AIR. Add Immediate to Register.
                componets.ALU.Calc(componets.getCU().getOpcode(), componets.getGPRRegister().getValue(), componets.getCU().getAddress());
                componets.CC0.set(componets.ALU.CC0);
                componets.getGPRRegister().setValue(componets.ALU.output, componets.ALU.CC0);
                break;
            }
            case 7: {
                // SIR. Subtract  Immediate  from Register.
                componets.ALU.Calc(componets.getCU().getOpcode(), componets.getGPRRegister().getValue(), componets.getCU().getAddress());
                componets.CC1.set(componets.ALU.CC1);
                componets.getGPRRegister().setValue(componets.ALU.output);
                break;
            }
            case 10: {
                //JZ.  jump to effective address if register is 0
                if (componets.getGPRRegister().getValue() == 0) {
                    componets.getPC().setValue(ea);
                } else {
                    componets.getPC().incrementOne();
                }

                break;
            }
            case 11: {
                //JNE.  jump to effective address if register is not 0
                if (componets.getGPRRegister().getValue() != 0) {
                    componets.getPC().setValue(ea);
                } else {
                    componets.getPC().incrementOne();
                }

                break;
            }
            case 12: {
                //JNE.  jump to effective address if CC is 1
                if (componets.getCC().getValue() == 1) {
                    componets.getPC().setValue(ea);
                } else {
                    componets.getPC().incrementOne();
                }

                break;
            }
            case 13: {
                //JMA.  jump to effective address if CC is 1

                componets.getPC().setValue(ea);
                break;
            }
            case 14: {
                //JSR. TODO unknown for R0
                componets.R3.setValue(componets.getPC().getValue() + 1);
                componets.getPC().setValue(ea);

                break;
            }
            case 15: {
                //RFS Return From Subroutine w/
                // return code as Immed portion (optional) stored in the instruction’s address field.
                componets.R0.setValue(componets.getCU().getAddress());

                componets.getPC().setValue(componets.R3.getValue());
                break;
            }
            case 16: {
                //SOB
                componets.getGPRRegister().setValue(componets.getGPRRegister().getValue() - 1);

                if (componets.getGPRRegister().getValue() > 0) {
                    componets.getPC().setValue(ea);
                } else {
                    componets.getPC().incrementOne();
                }


                break;
            }
            case 17: {
                //JGE
                if (componets.getGPRRegister().getValue() >= 0) {
                    componets.getPC().setValue(ea);
                } else {
                    componets.getPC().incrementOne();
                }

                break;
            }
            case 20: {
                // MLT: Multiply Register by Register.
                // Get Rx and Ry value.
                int Rx = componets.getRxRegister(true).getValue();
                int Ry = componets.getRyRegister(true).getValue();
                // Call ALU to do the Multi calculation.
                componets.ALU.Calc(componets.getCU().getOpcode(), Rx, Ry);
                Boolean OVERFLOW = (componets.ALU.output != 0);
                // Get Rx Register Index (is 0 or 2).
                int RxIndex = componets.getCU().getRx();
                if (RxIndex == 0) {
                    //Set the value to R0 and R1.
                    componets.R0.setValue(componets.ALU.HIResult);
                    componets.R1.setValue(componets.ALU.LOResult);
                    componets.CC0.set(OVERFLOW);
                } else if (RxIndex == 2) {
                    //Set the value to R2 and R3.
                    componets.R2.setValue(componets.ALU.HIResult);
                    componets.R3.setValue(componets.ALU.LOResult);
                    componets.CC0.set(OVERFLOW);
                } else {
                    // Rx Index is neither 0 nor 2. raise an error.
                    logging.severe("Request Other than R0 or R2 in Case 20.");
                    System.out.println("Request Other than R0 or R2 in Case 20.");
                }
                break;
            }
            case 21: {
                // DVD: Divide Register by Register.
                // Get Rx and Ry value.
                int Rx = componets.getRxRegister(true).getValue();
                int Ry = componets.getRyRegister(true).getValue();
                // Call ALU to do the Divide calculation.
                componets.ALU.Calc(componets.getCU().getOpcode(), Rx, Ry);
                Boolean DIVZERO = (componets.ALU.output != 0);
                // Get Rx Register Index (is 0 or 2).
                int RxIndex = componets.getCU().getRx();
                if (RxIndex == 0) {
                    //Set the value to R0 and R1.
                    componets.R0.setValue(componets.ALU.HIResult);
                    componets.R1.setValue(componets.ALU.LOResult);
                    componets.CC2.set(DIVZERO);
                } else if (RxIndex == 2) {
                    //Set the value to R2 and R3.
                    componets.R2.setValue(componets.ALU.HIResult);
                    componets.R3.setValue(componets.ALU.LOResult);
                    componets.CC2.set(DIVZERO);
                } else {
                    // Rx Index is neither 0 nor 2. raise an error.
                    logging.severe("Request Other than R0 or R2 in Case 20.");
                    System.out.println("Request Other than R0 or R2 in Case 20.");
                }
                break;
            }
            case 22: {
                // TRR. Test the Equality of Register and Register.
                int Rx = componets.getRxRegister(false).getValue();
                int Ry = componets.getRyRegister(false).getValue();
                componets.CC3.set((Rx == Ry) ? true : false);
                break;
            }
            case 23: {
                // AND. Logical And of Register and Register.
                int Rx = componets.getRxRegister(false).getValue();
                int Ry = componets.getRyRegister(false).getValue();
                componets.ALU.Calc(componets.getCU().getOpcode(), Rx, Ry);
                componets.getRxRegister(false).setValue(componets.ALU.output);
                break;
            }
            case 24: {
                // ORR. Logical Or of Register and Register.
                int Rx = componets.getRxRegister(false).getValue();
                int Ry = componets.getRyRegister(false).getValue();
                componets.ALU.Calc(componets.getCU().getOpcode(), Rx, Ry);
                componets.getRxRegister(false).setValue(componets.ALU.output);
                break;
            }
            case 25: {
                // NOT. Logical Not of Register To Register
                int Rx = componets.getRxRegister(false).getValue();
                componets.ALU.Calc(componets.getCU().getOpcode(), Rx, 0);
                componets.getRxRegister(false).setValue(componets.ALU.output);
                break;
            }
            case 31: {
                // SRC. Shift Register by Count.
                int Value = componets.getGPRRegister().getValue();
                int Count = componets.getCU().getCount();
                if (componets.getCU().getAL() == 0 && componets.getCU().getLR() == 0) {
                    //Shift right arithmetically.
                    Value = Value >> Count;
                }
                if (componets.getCU().getAL() == 1 && componets.getCU().getLR() == 0) {
                    //Shift right logically.
                    Value = Value >>> Count;
                }
                if (componets.getCU().getAL() == 0 && componets.getCU().getLR() == 1) {
                    //Shift left arithmetically.
                    Value = Value << Count;
                }
                if (componets.getCU().getAL() == 1 && componets.getCU().getLR() == 1) {
                    //Shift left logically.
                    //TODO:Do we have logical shift left in Java?
                    Value = Value << Count;
                }
                componets.getGPRRegister().setValue(Value);
                break;
            }
            case 32: {
                // RRC. Rotate Register by Count.
                int Value = componets.getGPRRegister().getValue();
                int Count = componets.getCU().getCount();
                if (componets.getCU().getLR() == 1) {
                    //Rotate Left
                    Value = (Value << Count) | (Value >> (16 - Count));
                }
                if (componets.getCU().getLR() == 0) {
                    //Rotate Right
                    Value = (Value >> Count) | (Value << (16 - Count));
                }
                componets.getGPRRegister().setValue(Value);
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
                // IN. Input Character To Register from Device.
                int DevID = componets.getCU().getAddress();
                char input = '\n';
                if (DevID == 0) {
                    //Read from console keyboard.
                    while (KeyboardBuffer.isEmpty()) {
                        KeyboardBuffer.setBufferFromGUI();
                    }
                    input = KeyboardBuffer.getOneDigit();
                }
                if (DevID == 1) {
                    // Read from the console printer(illegal)
                    logging.severe("IN Instr:can not read from printer.DEVID=1");
                    System.out.println("IN Instr:can not read from printer.DEVID=1");
                }
                if (DevID == 2) {
                    //Read from console card-reader.
                    while (CardReaderBuffer.isEmpty()) {
                        CardReaderBuffer.setBufferFromGUI();
                    }
                    input = CardReaderBuffer.getOneDigit();
                }
                if (DevID > 2 && DevID < 32) {
                    //Read from console Register.
                    ConsoleRegisterCollection.setRegisterValue(DevID - 2);
                    try {
                        input = (char) ConsoleRegisterCollection.getRegisterValue(DevID - 2);
                    } catch (Exception e) {
                        logging.severe("IN:Cast to char failed.");
                        System.out.println("IN:Cast to char failed.");
                    }
                } else {
                    logging.severe("IN Instr:Invalid DEVID.DEVID>32");
                    System.out.println("IN Instr:Invalid DEVID.DEVID>32");
                }
                componets.getGPRRegister().setValue((int) input);
                break;
            }
            case 62: {
                // OUT. Output Character to Device from Register.
                int DevID = componets.getCU().getAddress();
                // Get the output from the register and cast to char.
                char output = '\n';
                try {
                    output = (char) componets.getGPRRegister().getValue();
                } catch (Exception e) {
                    logging.severe("OUT:Cast to char failed.");
                    System.out.println("OUT:Cast to char failed.");
                }

                if (DevID == 0) {
                    // Out to console keyboard.(illegal)
                    logging.severe("OUT Instr:can not write from keyboard.DEVID=0");
                    System.out.println("OUT Instr:can not write from keyboard.DEVID=0");
                }
                if (DevID == 1) {
                    // Out to the console printer.
                    System.out.print(output);
                }
                if (DevID == 2) {
                    //Out to the console card-reader.
                    System.out.print(output);
                }
                if (DevID > 2 && DevID < 32) {
                    //Out to console Register.
                    System.out.print(output);
                } else {
                    logging.severe("OUT Instr:Invalid DEVID.DEVID>32");
                    System.out.println("OUT Instr:Invalid DEVID.DEVID>32");
                }
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
