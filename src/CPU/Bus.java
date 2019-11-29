package CPU;

import Interface.ConsoleRegisterCollection;
import Interface.IOBuffer;
import Memory.Memory;

import javax.smartcardio.Card;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public boolean isMemoryExpanded;
    public String OutputString = "";
    public ArrayList<Integer> BreakPointList = new ArrayList<Integer>(100);

    public Bus(Componets componets, Memory dataMemory) {
        this.componets = componets;
        this.dataMemory = dataMemory;
        this.isMemoryExpanded = false;
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
     * Set the BreakPoint of the PC.
     *
     * @param BreakPointPC The BreakPoint PC Value.
     */
    public void setBreakPoint(int BreakPointPC) {
        BreakPointList.add(BreakPointPC);
    }

    /**
     * Check if the current PC is in the BreakPoint List.
     *
     * @param PC the current PC you'd like to check.
     * @return If the PC is in the Breakpoint list, return true. else return false.
     */
    public boolean BreakPointCheck(int PC) {
        for (int i = 0; i < BreakPointList.size(); i++) {
            if (BreakPointList.get(i) == PC) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove the specified PC from the BreakPoint List.
     * If the PC is not in the BreakPoint list, do nothing.
     *
     * @param PC The spcified breakpoint PC value you want to remove.
     */
    public void removeBreakPoint(int PC) {
        for (int i = 0; i < BreakPointList.size(); i++) {
            if (BreakPointList.get(i) == PC) {
                BreakPointList.remove(i);
                break;
            }
        }
    }

    /**
     * Return the entire BreakPointList as a list.
     * This is used for GUI Rendering(JList).
     *
     * @return the list of breakpoint.
     */
    public Object[] getBreakPointList() {
        return BreakPointList.toArray();
    }


    /**
     * Manually set the Halt Status.
     * When this method is called. The HALT flag is set to True.
     */
    public void setHalt() {
        isHalt = true;
    }


    /**
     * The above code is to: Execute one instruction.
     */

    /**
     * Pipeline Register Set.
     */
    int pip_IR = 0;
    int pip_MBR = 3;
    int pip_MAR = 3;
    int BubbleInPipeline = 3;

    /**
     * Insert Bubble into the pipeline to flushing out pre-fetched instr.
     */
    private void InsertBubbleInPipeline() {
        System.out.println("=====================================================================================");
        System.out.println("Pipeline=>Flushing(Bubble Insert)....................................................");
        System.out.println("=====================================================================================");
        pip_IR = 0;
        pip_MBR = 3;
        pip_MAR = 3;
        BubbleInPipeline = 3;
    }

    /**
     * Simulate the pipeline to execute one instr.
     * Notice the execution order is reversed to simulate the real pipeline.
     */
    public void tik() {
        System.out.println("=====================================================================================");
        System.out.println("Pipeline=>Stage_D(Parallel)..........................................................");
        System.out.println("=====================================================================================");
        componets.getIR().setValue(pip_IR);
        // 4. CTRL-DECODE
        componets.getCU().decodeInstruction(componets.getIR().getValue());
        // 5. Execute Process
        executeInstruction(calculateEA());


        System.out.println("=====================================================================================");
        System.out.println("Pipeline=>Stage_C(Parallel)..........................................................");
        System.out.println("=====================================================================================");
        // 3. IR <- MBR
        componets.getMBR().setValue(pip_MBR);
        pip_IR = componets.getMBR().getValue();


        System.out.println("=====================================================================================");
        System.out.println("Pipeline=>Stage_B(Parallel)..........................................................");
        System.out.println("=====================================================================================");
        componets.getMAR().setValue(pip_MAR);
        // 2. MBR <- MEM[MAR]
        if (componets.getMAR().getValue() >= 2048 && isMemoryExpanded == false) {
            //Machine Fault. User try to visit memory above 2K.
            componets.getMFR().setValue(8);
            dataMemory.set(4, componets.getPC().getValue());
            componets.getPC().setValue(dataMemory.get(1));
            return;
        }
        pip_MBR = dataMemory.get(componets.getMAR().getValue());


        System.out.println("=====================================================================================");
        System.out.println("Pipeline=>Stage_A(Parallel)..........................................................");
        System.out.println("=====================================================================================");
        // 1. MAR <- PC,PC++;
        pip_MAR = componets.getPC().getValue();
        componets.getPC().incrementOne();

        BubbleInPipeline -= 1;
    }

    /**
     * Execute the whole program. Break when it meets HALT.
     * *TIPS* When it comes to empty Memory, the DataMemory Components will return 16'b0. This is HALT itself!
     * Stop Criteria:
     * -> HALT(Both HALT Instr and Manually set from GUI), Program Drop out of the bottom.
     * -> BreakPoint Match.
     */
    public void run() {
        while (true) {
            tik();
            if (BreakPointCheck(componets.PC.getValue())) {
                break;
            }
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
                if ((address + componets.getIXRegister().getValue()) >= 2048 && isMemoryExpanded == false) {
                    //Machine Fault. User try to visit memory above 2K.
                    componets.getMFR().setValue(8);
                    dataMemory.set(4, componets.getPC().getValue());
                    componets.getPC().setValue(dataMemory.get(1));
                    return 0;
                }
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
     * Effective Address (EA) Calculator
     * After CTRL Module decode the Instr to CTRL signal, Call this method to calucate EA.
     *
     * @return The Effective Address.(Int)
     */
    private int calculateEA_LDX() {
        // Get Address (Immediate Value from Instr), the last 5 digits.
        int address = componets.getCU().getAddress();
        // NOT Indirect Addressing.
        if (componets.getCU().getI() == 0) {
            // Check IX.
            if (componets.getCU().getIX() == 0) {
                return address;
            } else {
                return address;
            }
        }
        // Indirect Addressing.
        else if (componets.getCU().getI() == 1) {
            // Check IX.
            if (componets.getCU().getIX() == 0) {
                return dataMemory.get(address);
            } else {
                return dataMemory.get(address);
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
                if (BubbleInPipeline <= 0) {
                    //HALT
                    isHalt = true;
                    componets.PC.setValue(6); // Reset PC
                    System.out.println("BUS:HALT");
                }
                break;
            }
            case 1: {
                //Load the MEM[EA] to specific GPR.
                if (ea >= 2048 && isMemoryExpanded == false) {
                    //Machine Fault. User try to visit memory above 2K.
                    componets.getMFR().setValue(8);
                    dataMemory.set(4, componets.getPC().getValue());
                    componets.getPC().setValue(dataMemory.get(1));
                    break;
                }
                componets.getGPRRegister().setValue(dataMemory.get(ea));
                break;
            }
            case 2: {
                // Store the GPR to MEM[EA].
                if (ea < 6) {
                    //Machine Fault. User Try to set protected memory.
                    componets.getMFR().setValue(1);
                    dataMemory.set(4, componets.getPC().getValue());
                    componets.getPC().setValue(dataMemory.get(1));
                    break;
                }
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
                if (ea >= 2048 && isMemoryExpanded == false) {
                    //Machine Fault. User try to visit memory above 2K.
                    componets.getMFR().setValue(8);
                    dataMemory.set(4, componets.getPC().getValue());
                    componets.getPC().setValue(dataMemory.get(1));
                    break;
                }
                componets.ALU.Calc(componets.getCU().getOpcode(), componets.getGPRRegister().getValue(), dataMemory.get(ea));
                componets.CC0.set(componets.ALU.CC0);
                componets.getGPRRegister().setValue(componets.ALU.output, componets.ALU.CC0);
                break;
            }
            case 5: {
                // SMR. Subtract Memory From Register.
                if (ea >= 2048 && isMemoryExpanded == false) {
                    //Machine Fault. User try to visit memory above 2K.
                    componets.getMFR().setValue(8);
                    dataMemory.set(4, componets.getPC().getValue());
                    componets.getPC().setValue(dataMemory.get(1));
                    break;
                }
                componets.ALU.Calc(componets.getCU().getOpcode(), componets.getGPRRegister().getValue(), dataMemory.get(ea));
                componets.CC1.set(componets.ALU.CC1);
                componets.getGPRRegister().setValue(componets.ALU.output, componets.ALU.CC1);
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
                componets.getGPRRegister().setValue(componets.ALU.output, componets.ALU.CC1);
                break;
            }
            case 10: {
                //JZ.  jump to effective address if register is 0
                if (componets.getGPRRegister().getValue() == 0) {
                    InsertBubbleInPipeline();
                    componets.getPC().setValue(ea);
                } else {
                    //componets.getPC().incrementOne();
                }

                break;
            }
            case 11: {
                //JNE.  jump to effective address if register is not 0
                if (componets.getGPRRegister().getValue() != 0) {
                    InsertBubbleInPipeline();
                    componets.getPC().setValue(ea);
                } else {
                    //componets.getPC().incrementOne();
                }

                break;
            }
            case 12: {
                //JNE.  jump to effective address if CC is 1
                if (componets.getCC().getValue() == 1) {
                    InsertBubbleInPipeline();
                    componets.getPC().setValue(ea);
                } else {
                    //componets.getPC().incrementOne();
                }

                break;
            }
            case 13: {
                //JMA.  jump to effective address if CC is 1
                InsertBubbleInPipeline();
                componets.getPC().setValue(ea);
                break;
            }
            case 14: {
                //JSR. TODO unknown for R0
                //When execute this part, the PC value is PC + 1.
                //This is the trick when implementing pipeline! Minus is required because the PC is ahead of game.
                componets.R3.setValue(componets.getPC().getValue() - 2);
                InsertBubbleInPipeline();
                componets.getPC().setValue(ea);

                break;
            }
            case 15: {
                //RFS Return From Subroutine w/
                // return code as Immed portion (optional) stored in the instruction’s address field.
                componets.R0.setValue(componets.getCU().getAddress());
                InsertBubbleInPipeline();
                componets.getPC().setValue(componets.R3.getValue());
                break;
            }
            case 16: {
                //SOB
                componets.getGPRRegister().setValue(componets.getGPRRegister().getValue() - 1);

                if (componets.getGPRRegister().getValue() > 0) {
                    componets.getPC().setValue(ea);
                } else {
                    //componets.getPC().incrementOne();
                }


                break;
            }
            case 17: {
                //JGE
                if (componets.getGPRRegister().getValue() >= 0 && componets.CC1.get() == false) {
                    InsertBubbleInPipeline();
                    componets.getPC().setValue(ea);
                } else {
                    //componets.getPC().incrementOne();
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
                Boolean OVERFLOW = componets.ALU.CC0;
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
                Boolean DIVZERO = componets.ALU.CC2;
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
                componets.getRxRegister(false).setValue(componets.ALU.output, true);
                break;
            }
            case 30: {
                // TRAP. Execute TRAP subroutine.
                int TrapCode = componets.getCU().getTrapCode();
                if (TrapCode > 15 || TrapCode < 0) {
                    // Machine Fault. This is not a valid Trap code.
                    componets.getMFR().setValue(2);
                    dataMemory.set(4, componets.getPC().getValue());
                    componets.getPC().setValue(dataMemory.get(1));
                    break;
                }
                // Step 1. Store PC+1.
                dataMemory.set(2, componets.getPC().getValue());
                // Step 2. Jump to MEM[0]+TrapCode(Used as a bias of the table entry.)
                componets.getPC().setValue(dataMemory.get(0) + TrapCode);
                break;
            }
            case 31: {
                // SRC. Shift Register by Count.
                int Value = componets.getGPRRegister().getValue();
                int Count = componets.getCU().getCount();
                if (componets.getCU().getAL() == 0 && componets.getCU().getLR() == 0) {
                    //Shift right arithmetically.
                    //TODO:Is the project description right?
                    String StringValue = ToBinaryString(Value, 16);
                    String SignBit = StringValue.substring(0, 1);
                    if (SignBit.equals("1")) {
                        componets.CC1.set(true);
                    }
                    for (int i = 0; i < Count; i++) {
                        StringValue = SignBit + StringValue;
                    }
                    StringValue = StringValue.substring(0, 16);
                    Value = Integer.valueOf(StringValue, 2);
                }
                if (componets.getCU().getAL() == 1 && componets.getCU().getLR() == 0) {
                    //Shift right logically.
                    String StringValue = ToBinaryString(Value, 16);
                    for (int i = 0; i < Count; i++) {
                        StringValue = "0" + StringValue;
                    }
                    StringValue = StringValue.substring(0, 16);
                    Value = Integer.valueOf(StringValue, 2);
                }
                if (componets.getCU().getAL() == 0 && componets.getCU().getLR() == 1) {
                    //Shift left arithmetically.
                    String StringValue = ToBinaryString(Value, 16);
                    String SignBit = StringValue.substring(0, 1);
                    for (int i = 0; i < Count; i++) {
                        StringValue = StringValue + "0";
                    }
                    StringValue = StringValue.substring(StringValue.length() - 15);
                    StringValue = SignBit + StringValue;
                    Value = Integer.valueOf(StringValue, 2);
                }
                if (componets.getCU().getAL() == 1 && componets.getCU().getLR() == 1) {
                    //Shift left logically.
                    String StringValue = ToBinaryString(Value, 16);
                    for (int i = 0; i < Count; i++) {
                        StringValue = StringValue + "0";
                    }
                    StringValue = StringValue.substring(StringValue.length() - 16);
                    Value = Integer.valueOf(StringValue, 2);
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
                    String StringValue = ToBinaryString(Value, 16);
                    String MovePart = StringValue.substring(0, Count);
                    StringValue = StringValue + MovePart;
                    StringValue = StringValue.substring(StringValue.length() - 16);
                    Value = Integer.valueOf(StringValue, 2);
                }
                if (componets.getCU().getLR() == 0) {
                    //Rotate Right
                    String StringValue = ToBinaryString(Value, 16);
                    String MovePart = StringValue.substring(StringValue.length() - Count);
                    StringValue = MovePart + StringValue;
                    StringValue = StringValue.substring(0, 16);
                    Value = Integer.valueOf(StringValue, 2);
                }
                componets.getGPRRegister().setValue(Value);
                break;
            }
            case 33: {
                // FADD. Floating Point Add.
                Floating_Register FloatingEA = new Floating_Register(dataMemory.get(ea));
                float result = FloatingEA.toFloatingPoint() + componets.getFRRegister().toFloatingPoint();
                componets.getFRRegister().setFloatingPoint(result);
                break;
            }
            case 34: {
                // FSUB. Floating Point SUB.
                Floating_Register FloatingEA = new Floating_Register(dataMemory.get(ea));
                float result = componets.getFRRegister().toFloatingPoint() - FloatingEA.toFloatingPoint();
                componets.getFRRegister().setFloatingPoint(result);
                break;
            }
            case 35: {
                //VADD. Vector Add.
                int VectorLength = Integer.valueOf(componets.getFRRegister().Value, 2);
                int Vector1BaseAdd = dataMemory.get(ea);
                int Vector2BaseAdd = dataMemory.get(ea + 1);
                for (int i = 0; i < VectorLength; i++) {
                    int Vector1CurrentValue = dataMemory.get(Vector1BaseAdd + i);
                    int Vector2CurrentValue = dataMemory.get(Vector2BaseAdd + i);
                    dataMemory.set(Vector1BaseAdd + i, Vector1CurrentValue + Vector2CurrentValue);
                }
                break;
            }
            case 36: {
                //VSUB. Vector Sub.
                int VectorLength = Integer.valueOf(componets.getFRRegister().Value, 2);
                int Vector1BaseAdd = dataMemory.get(ea);
                int Vector2BaseAdd = dataMemory.get(ea + 1);
                for (int i = 0; i < VectorLength; i++) {
                    int Vector1CurrentValue = dataMemory.get(Vector1BaseAdd + i);
                    int Vector2CurrentValue = dataMemory.get(Vector2BaseAdd + i);
                    dataMemory.set(Vector1BaseAdd + i, Vector1CurrentValue - Vector2CurrentValue);
                }
                break;
            }
            case 37: {
                //CNVRT. Convert to Fixed/Floating Point Number.
                int F = componets.getGPRRegister().getValue();
                if (F == 0) {
                    //convert(EA) to a fixed point number and store in r.
                    Floating_Register FloatingEA = new Floating_Register(dataMemory.get(ea));
                    int EAasInt = (int) FloatingEA.toFloatingPoint();
                    componets.getGPRRegister().setValue(EAasInt);
                }
                if (F == 1) {
                    //convert c(EA) to a floating number and stored in FR0.
                    componets.FR0.setFloatingPoint(dataMemory.get(ea));
                }
                break;
            }
            case 41: {
                // Write the IX register with MEM[EA].
                ea = calculateEA_LDX();
                if (ea >= 2048 && isMemoryExpanded == false) {
                    //Machine Fault. User try to visit memory above 2K.
                    componets.getMFR().setValue(8);
                    dataMemory.set(4, componets.getPC().getValue());
                    componets.getPC().setValue(dataMemory.get(1));
                    break;
                }
                componets.getIXRegister().setValue(dataMemory.get(ea));
                break;
            }
            case 42: {
                // Store the IX to MEM[EA].
                if (ea < 6) {
                    //Machine Fault. User Try to set protected memory.
                    componets.getMFR().setValue(1);
                    dataMemory.set(4, componets.getPC().getValue());
                    componets.getPC().setValue(dataMemory.get(1));
                    break;
                }
                dataMemory.set(ea, componets.getIXRegister().getValue(), true);
                break;
            }
            case 50: {
                // LDFR. Load Floating Register From Memory.
                componets.FR0.setBinary(dataMemory.ToBinaryString(dataMemory.get(ea)));
                componets.FR1.setBinary(dataMemory.ToBinaryString(dataMemory.get(ea)));
                break;
            }
            case 51: {
                //STFR. Store Floating Register To Memory.
                dataMemory.set(ea, Integer.valueOf(componets.FR0.Value, 2));
                dataMemory.set(ea + 1, Integer.valueOf(componets.FR1.Value, 2));
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
                } else if (DevID == 1) {
                    // Read from the console printer(illegal)
                    logging.severe("IN Instr:can not read from printer.DEVID=1");
                    System.out.println("IN Instr:can not read from printer.DEVID=1");
                } else if (DevID == 2) {
                    //Read from console card-reader.
                    while (CardReaderBuffer.isEmpty()) {
                        CardReaderBuffer.setBufferFromFile();
                    }
                    input = CardReaderBuffer.getOneDigit();
                } else if (DevID > 2 && DevID < 32) {
                    //Read from console Register.
                    ConsoleRegisterCollection.setRegisterValue(DevID - 3);
                    try {
                        input = (char) ConsoleRegisterCollection.getRegisterValue(DevID - 3);
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
                // OUT. Output Character to Device from the Register.
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
                } else if (DevID == 1) {
                    // Out to the console printer.
                    System.out.print(output);
                    OutputString = OutputString + output;
                } else if (DevID == 2) {
                    //Out to the console card-reader.
                    System.out.print(output);
                    OutputString = OutputString + output;
                } else if (DevID > 2 && DevID < 32) {
                    //Out to console Register.
                    System.out.print(output);
                    OutputString = OutputString + output;
                } else {
                    logging.severe("OUT Instr:Invalid DEVID.DEVID>32");
                    System.out.println("OUT Instr:Invalid DEVID.DEVID>32");
                }
                break;
            }
            case 63: {
                // CHK. Check device status.
                // This instr will always set the destination register to 1.
                // Since our Hardware is always online. :) .
                int DevID = componets.getCU().getAddress();
                if (DevID == 0) {
                    // Check Status of the Keyboard.
                    componets.getGPRRegister().setValue(1);
                } else if (DevID == 1) {
                    // Check Status of the Console Printer.
                    componets.getGPRRegister().setValue(1);
                } else if (DevID == 2) {
                    // Check Status of the Card Reader.
                    componets.getGPRRegister().setValue(1);
                } else if (DevID > 2 && DevID < 32) {
                    componets.getGPRRegister().setValue(1);
                } else {
                    logging.severe("CHK Instr:Invalid DEVID.DEVID>32");
                    System.out.println("CHK Instr:Invalid DEVID.DEVID>32");
                }
                break;
            }
            default: {
                // Machine Fault. Unsupported OpCode.
                componets.getMFR().setValue(4);
                dataMemory.set(4, componets.getPC().getValue());
                componets.getPC().setValue(dataMemory.get(1));
                break;
            }
        }
    }

    /**
     * Change the int value to BinaryString (Could Handle Negative Value)
     *
     * @param value  the value
     * @param length the length of the Binary String you want to get.
     * @return the Binary String.
     */
    public String ToBinaryString(int value, int length) {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        if (a.length() == 32 && a.substring(0, 1).equals("1")) {
            // It is a negative number!
            return a.substring(a.length() - length);
        }
        String Stringlength = "" + length;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));//
    }

}
