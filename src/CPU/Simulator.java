package CPU;


public class Simulator {
Condition_Code CC1,CC2,CC3,CC4 ;
General_Purpose_Registers R0,R1,R2,R3 ;
Instruction_Register IR;
IX_Register IX0,IX1,IX2;
Machine_Fault_Register MFR;
Memory_Address_Register MAR;
Memory_Buffer_Register MBR;
Memory DataMemory;
ProgramCounter PC;

	public Simulator () {
		CC1 = new Condition_Code(false);
		CC2 = new Condition_Code(false);
		CC3 = new Condition_Code(false);
		CC4 = new Condition_Code(false);
		R0 = new General_Purpose_Registers("0000000000000000");
		R1 = new General_Purpose_Registers("0000000000000000");
		R2 = new General_Purpose_Registers("0000000000000000");
		R3 = new General_Purpose_Registers("0000000000000000");
		IR = new Instruction_Register("0000000000000000");
		IX0 = new IX_Register("0000000000000000");
		IX1 = new IX_Register("0000000000000000");
		IX2 = new IX_Register("0000000000000000");
		MFR = new Machine_Fault_Register(); //TODO: Build Constructor.
		MAR = new Memory_Address_Register("0000000000000000");
		MBR = new Memory_Buffer_Register("0000000000000000");
		DataMemory = new Memory();
		PC = new ProgramCounter("000000000000");
	}
}

