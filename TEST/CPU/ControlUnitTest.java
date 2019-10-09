package CPU;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlUnitTest {
    int opcode,I,R,IX,address;
    String V = "0101010101101001";
    ControlUnit test = new ControlUnit(V);


    @Test
    void decodeInstruction() {
        int result=Integer.parseInt(V,2);
        test.decodeInstruction(result);

    }

    @Test
    void getOpcode() {
        assertTrue(test.getOpcode() <= 63);
        System.out.println(test.getOpcode());

    }

    @Test
    void getAddress() {
        assertTrue(test.getAddress() <= 31);
        System.out.println(test.getAddress());

    }

    @Test
    void getIX() {
        assertTrue(test.getIX() <= 63);
        System.out.println(test.getIX());

    }

    @Test
    void getR() {
        assertTrue(test.getR() <= 3);
        System.out.println(test.getR());
    }

    @Test
    void getI() {
        assertTrue(test.getI() <= 1);
        System.out.println(test.getI());
    }
}