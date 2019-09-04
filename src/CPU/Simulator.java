package CPU;


import java.util.logging.Logger;

public class Simulator {
    Condition_Code CC1, CC2, CC3, CC4;
    General_Purpose_Registers R0, R1, R2, R3;
    Instruction_Register IR;
    IX_Register IX3, IX1, IX2;
    Machine_Fault_Register MFR;
    Memory_Address_Register MAR;
    Memory_Buffer_Register MBR;
    Memory DataMemory;
    ProgramCounter PC;
    ControlUnit CU;
    ALU ALU;
    int opcode;
    int IX;
    int R;
    int EA;
    int ALUresult;
    final Logger logging = Logger.getLogger("CPU.Simulator");

    public Simulator() {
        CC1 = new Condition_Code(false);
        CC2 = new Condition_Code(false);
        CC3 = new Condition_Code(false);
        CC4 = new Condition_Code(false);
        R0 = new General_Purpose_Registers(0);
        R1 = new General_Purpose_Registers(0);
        R2 = new General_Purpose_Registers(0);
        R3 = new General_Purpose_Registers(0);
        IR = new Instruction_Register(0);
        IX1 = new IX_Register(0);
        IX2 = new IX_Register(0);
        IX3 = new IX_Register(0);
        MFR = new Machine_Fault_Register(0); //TODO: Build Constructor.
        MAR = new Memory_Address_Register(0);
        MBR = new Memory_Buffer_Register(0);
        DataMemory = new Memory();
        ALU = new ALU();
        PC = new ProgramCounter(0);
        CU = new ControlUnit(IR.ToBinaryString());//16 0's
    }

    public void operator() {
        // MAR <- PC
        MAR.setValue(PC.getValue());
        // MBR <- MEM[MAR]
        MBR.setValue(DataMemory.get(MAR.getValue()));
        // IR <- MBR
        IR.setValue(MAR.getValue());
        // CTRL-DECODE
        CU.setInstruction(IR.getValue());
        opcode = CU.getOpcode();
        switch (CU.getIX()) {
            case 0:
                break;
            case 1:
                IX = IX1.getValue();
                break;
            case 2:
                IX = IX2.getValue();
                break;
            case 3:
                IX = IX3.getValue();
                break;
            default:
                logging.severe("IX is trying to index 3 but not exist");
                break;
        }
        switch (CU.getR()) {
            case 0:
                R = R0.getValue();
                break;
            case 1:
                R = R1.getValue();
                break;
            case 2:
                R = R2.getValue();
                break;
            case 3:
                R = R3.getValue();
                break;
            default:
                logging.severe("R is not in 0 to 3  ");
                break;
        }
        // ALU Process
        CalculateEA();
        ALUresult = ALU.execute(opcode, R, IX, EA);
        // DM(Data Memory) and WB(Write Back) Process.
        PostALUOperation();
        PC.incrementOne();
    }

    public void CalculateEA() {
        if (CU.getI() == 0) {
            if (CU.getIX() == 0) {
                EA = CU.getAddress();
            } else {
                EA = CU.getAddress() + IX;
            }
        } else if (CU.getI() == 1) {
            if (CU.getI() == 0) {
                EA = DataMemory.get(CU.getAddress());
            } else {
                EA = DataMemory.get(CU.getAddress() + IX);
            }
        } else {
            logging.severe("I has trouble");
        }
    }

    public void PostALUOperation() {
        switch (opcode) {
            case 01: {
                // Write the MEM[EA] data into the specific GPR.(Trigger GPRWriteEnable.)
                switch (CU.getR()) {
                    case 0:
                        R0.setValue(DataMemory.get(EA));
                        break;
                    case 1:
                        R1.setValue(DataMemory.get(EA));
                        break;
                    case 2:
                        R2.setValue(DataMemory.get(EA));
                        break;
                    case 3:
                        R3.setValue(DataMemory.get(EA));
                        break;
                    default:
                        logging.severe("R is not in 0 to 3  ");
                        break;
                }
                break;
            }
            case 02:{
                // Store the GPR to MEM[EA].(Trigger DMWriteEnable.)
                DataMemory.set(EA,R);
                break;
            }
            case 03:{
                // Write the EA address value into the specific GPR.
                switch (CU.getR()) {
                    case 0:
                        R0.setValue(EA);
                        break;
                    case 1:
                        R1.setValue(EA);
                        break;
                    case 2:
                        R2.setValue(EA);
                        break;
                    case 3:
                        R3.setValue(EA);
                        break;
                    default:
                        logging.severe("R is not in 0 to 3  ");
                        break;
                }
                break;
            }
            case 41:{
                // Write the IX register with MEM[EA].
                switch (CU.getIX()) {
                    case 0:
                        logging.severe("Write IX Index cannot be zero!");
                        break;
                    case 1:
                        IX1.setValue(DataMemory.get(EA));
                        break;
                    case 2:
                        IX2.setValue(DataMemory.get(EA));
                        break;
                    case 3:
                        IX3.setValue(DataMemory.get(EA));
                        break;
                    default:
                        logging.severe("IX is trying to index 3 but not exist");
                        break;
                }
                break;
            }
            case 42:{
                // Store the IX to MEM[EA].
                DataMemory.set(EA,IX);
                break;
            }
            default:
                break;
        }
        return;
    }
}

