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
    int opcode;
    int IX;
    int R;
    int EA;
    final Logger logging = Logger.getLogger("SimulatorLOG");

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


}

