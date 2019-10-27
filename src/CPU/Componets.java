package CPU;


import java.util.logging.Logger;

/**
 * This class is a collection of all Registers in CPU we initialized
 */
public class Componets {
    public Condition_Code CC0, CC1, CC2, CC3;
    public General_Purpose_Registers R0, R1, R2, R3;
    public Instruction_Register IR;
    public IX_Register IX3, IX1, IX2;
    public Machine_Fault_Register MFR;
    public Memory_Address_Register MAR;
    public Memory_Buffer_Register MBR;
    public Address_Register EA;
    public ProgramCounter PC;
    public ALU ALU;
    private ControlUnit CU;


    final Logger logging = Logger.getLogger("CPU.RegistersCollection");

    public Componets() {
        initialize();
    }

    /**
     * Initialize all components.
     * Set all to zero and PC is set to 6 due to we put the first instruction at MEM[6].
     */
    public void initialize() {
        //Conditional Code Indicator.
        CC0 = new Condition_Code(false);
        CC1 = new Condition_Code(false);
        CC2 = new Condition_Code(false);
        CC3 = new Condition_Code(false);
        // GPR R0-R3
        R0 = new General_Purpose_Registers(0);
        R1 = new General_Purpose_Registers(0);
        R2 = new General_Purpose_Registers(0);
        R3 = new General_Purpose_Registers(0);
        // Instruction Register
        IR = new Instruction_Register(0);
        // IX
        IX1 = new IX_Register(0);
        IX2 = new IX_Register(0);
        IX3 = new IX_Register(0);
        // Machine Fault Register
        MFR = new Machine_Fault_Register(0);
        // Memory Address Register
        MAR = new Memory_Address_Register(0);
        // Memory Buffer Register
        MBR = new Memory_Buffer_Register(0);
        // EA Register (NOT IN USE.)
        EA = new Address_Register(0);
        // PC
        PC = new ProgramCounter(6);
        // ALU
        ALU = new ALU();
        // Control Unit
        CU = new ControlUnit(IR.ToBinaryString());
    }

    /**
     * find the CC we are working on right now
     *
     * @return The CC Register this Instruction is in Use. (CC Object.)
     */
    public Register getCC() {
        int index = CU.getR();
        if (index == 0) {
            return CC0;
        }
        if (index == 1) {
            return CC1;
        }
        if (index == 2) {
            return CC2;
        } else {
            return CC3;
        }
    }

    /**
     * find the GPR we are working on right now
     *
     * @return The GPR Register this Instruction is in Use. (GPR Object.)
     */
    public Register getGPRRegister() {
        int index = CU.getR();
        if (index == 0) {
            return R0;
        }
        if (index == 1) {
            return R1;
        }
        if (index == 2) {
            return R2;
        } else {
            return R3;
        }
    }

    /**
     * find the IX Register we are working on right now
     *
     * @return The IX Register this Instruction is in Use. (IX Object.)
     */
    public Register getIXRegister() {
        int index = CU.getIX();
        if (index == 1) {
            return IX1;
        }
        if (index == 2) {
            return IX2;
        }
        if (index == 3) {
            return IX3;
        }
        //Request IX0, which is not actually exist.
        else {
            logging.severe("outPut 0 return a invalid IX0 Register");
            System.out.println("outPut 0 return a invalid IX0 Register");
            return new Register(0, 0, "");
        }

    }

    /**
     * find the GPR(Rx Field) we are working on right now
     * This function could only be called when meeting a MULTI/DIV Instr.
     * Otherwise, the output is useless.
     * If the boolean flag limited is set to TRUE, Rx could only index R0 and R2.
     * Requesting Other Register will be declined.
     *
     * @param limited Boolean. If is set to TRUE, Rx could only index R0 and R2.
     * @return The GPR Register that requested.
     */
    public Register getRxRegister(boolean limited) {
        int index = CU.getRx();
        if (index == 0) {
            return R0;
        } else if (index == 2) {
            return R2;
        } else if (index == 1 && !limited) {
            return R1;
        } else if (index == 3 && !limited) {
            return R3;
        } else {
            logging.severe("getRxRegister: LIMITED.Request Register other than R0 or R2.");
            System.out.println("getRxRegister: LIMITED.Request Register other than R0 or R2.");
            return new Register(0, 0, "");
        }
    }

    /**
     * find the GPR(Ry Field) we are working on right now
     * This function could only be called when meeting a MULTI/DIV Instr.
     * Otherwise, the output is useless.
     * If the boolean flag limited is set to TRUE, Ry could only index R0 and R2.
     * Requesting Other Register will be declined.
     *
     * @param limited Boolean. If is set to TRUE, Ry could only index R0 and R2.
     * @return The GPR Register that requested.
     */
    public Register getRyRegister(boolean limited) {
        int index = CU.getRy();
        if (index == 0) {
            return R0;
        } else if (index == 2) {
            return R2;
        } else if (index == 1 && !limited) {
            return R1;
        } else if (index == 3 && !limited) {
            return R3;
        } else {
            logging.severe("getRyRegister: LIMITED.Request Register other than R0 or R2.");
            System.out.println("getRyRegister: LIMITED.Request Register other than R0 or R2.");
            return new Register(0, 0, "");
        }
    }

    /**
     * Get Effective Address Register.
     *
     * @return The EA Register (Object.)
     */
    public Register getAddressRegister() {
        return EA;
    }

    /**
     * Get MAR Register.
     *
     * @return The MAR Register (Object.)
     */
    public Register getMAR() {
        return MAR;
    }

    /**
     * Get MFR Register.
     *
     * @return The MFR Register (Object.)
     */
    public Register getMFR() {
        return MFR;
    }


    /**
     * Get MBR Register.
     *
     * @return The MBR Register (Object.)
     */
    public Register getMBR() {
        return MBR;
    }

    /**
     * Get IR Register.
     *
     * @return The IR Register (Object.)
     */
    public Register getIR() {
        return IR;
    }

    /**
     * Get Control Unit.
     *
     * @return The Control Unit (Object.)
     */
    public ControlUnit getCU() {
        return CU;
    }

    /**
     * Get PC.
     *
     * @return The PC Register (Object.)
     */
    public ProgramCounter getPC() {
        return PC;
    }

}
