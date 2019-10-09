package CPU;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComponetsTest {
    Componets test = new Componets ();
    RegisterTest test2 = new RegisterTest();

   /* public boolean CC1, CC2, CC3, CC4;
    public General_Purpose_Registers R0, R1, R2, R3;
    public Instruction_Register IR;
    public IX_Register IX3, IX1, IX2;
    public Machine_Fault_Register MFR;
    public Memory_Address_Register MAR;
    public Memory_Buffer_Register MBR;
    public Address_Register EA;
    public ProgramCounter PC;
    public ControlUnit CU;
*/
    @Test
    void initialize() {
        test.initialize();
        assertFalse(test.CC1.get());
        assertFalse(test.CC2.get());
        assertFalse(test.CC3.get());
        assertFalse(test.CC4.get());
        assertEquals(test.R0.value,0);
        assertEquals(test.R1.value,0);
        assertEquals(test.R2.value,0);
        assertEquals(test.R3.value,0);
        assertEquals(test.IX1.value,0);
        assertEquals(test.IX2.value,0);
        assertEquals(test.IX3.value,0);
        assertEquals(test.MFR.value,0);
        assertEquals(test.MAR.value,0);
        assertEquals(test.MBR.value,0);
        assertEquals(test.EA.value,0);
        assertEquals(test.PC.value,6);




    }
/*
    @Test

    void getGPRRegister() {
        int index=test.CU.getR();
        System.out.println(index);




    }

    @Test
    void getIXRegister() {

    }

    @Test
    void getAddressRegister() {
    }

    @Test
    void getMAR() {
    }

    @Test
    void getMBR() {
    }

    @Test
    void getIR() {
    }

    @Test
    void getCU() {
    }

    @Test
    void getPC() {
    }
    *
 */
}