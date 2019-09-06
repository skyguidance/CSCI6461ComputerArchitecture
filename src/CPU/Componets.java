package CPU;


import java.util.logging.Logger;

public class Componets {
    public Condition_Code CC1, CC2, CC3, CC4;
    public General_Purpose_Registers R0, R1, R2, R3;
    public Instruction_Register IR;
    public IX_Register IX3, IX1, IX2;
    public Machine_Fault_Register MFR;
    public Memory_Address_Register MAR;
    public Memory_Buffer_Register MBR;
    final Logger logging= Logger.getLogger("CPU.RegistersCollection");

    //Memory DataMemory;
    public ProgramCounter PC;
    public ControlUnit CU;
    public Componets(){
        initialize();
    }
    public void initialize(){
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
       // DataMemory = new Memory();
        PC = new ProgramCounter(0);
        CU = new ControlUnit(IR.ToBinaryString());//16 0's
    }
    public Register getGPRRegister(){
        int index=CU.getR();
       if(index==0){return R0;}
        if(index==1){return R1;}
        if(index==2){return R2;}
       else{ return R3;}
    }
    public Register getIXRegister(){
        int index=CU.getIX();
        if(index==1){return IX1;}
        if(index==2){return IX2;}
        if(index==3){return IX3;}
        else {
            logging.severe("outPut 0 return a invalid IX0 Register");
            return new Register(0,0,"");
        }

    }

}
