package CPU;

import java.util.logging.Logger;

/**
 * This is the ControlUnit Class.
 * This is place we decode our instructions and store the result temporary.
 */
public class ControlUnit {

    private int opcode, I, R, IX, address;
    private int Rx, Ry;
    private int AL, LR, Count;
    private int TrapCode;
    final Logger logging = Logger.getLogger("CPU.ControlUnit");

    public ControlUnit(String BinaryString) {
        opcode = Integer.valueOf(BinaryString.substring(0, 6), 2);
        R = Integer.valueOf(BinaryString.substring(6, 8), 2);
        IX = Integer.valueOf(BinaryString.substring(8, 10), 2);
        I = Integer.valueOf(BinaryString.substring(10, 11), 2);
        address = Integer.valueOf(BinaryString.substring(11, 16), 2);
    }

    /**
     * Decode The Instruction.
     *
     * @param IR The Instruction.(int)
     */
    public void decodeInstruction(int IR) {
        // Change the IR to Binary String for easy split.
        String BinaryString = ToBinaryString(IR);
        // Decode
        opcode = Integer.valueOf(BinaryString.substring(0, 6), 2);
        R = Integer.valueOf(BinaryString.substring(6, 8), 2);
        IX = Integer.valueOf(BinaryString.substring(8, 10), 2);
        I = Integer.valueOf(BinaryString.substring(10, 11), 2);
        address = Integer.valueOf(BinaryString.substring(11, 16), 2);
        // Extra Decode for MULTI/DIV Instr.
        Rx = Integer.valueOf(BinaryString.substring(6, 8), 2);
        Ry = Integer.valueOf(BinaryString.substring(8, 10), 2);
        // Rotate Indicator.
        AL = Integer.valueOf(BinaryString.substring(8, 9), 2);
        LR = Integer.valueOf(BinaryString.substring(9, 10), 2);
        Count = Integer.valueOf(BinaryString.substring(12, 16), 2);
        // Trap Code.
        TrapCode = Integer.valueOf(BinaryString.substring(12, 16), 2);
        // Print the result for easy Debug.
        logging.info("CTRL DECODE:OPCODE=>" + opcode + "\tGPR=>" + R + "\tIX=>" + IX + "\tI=>" + I + "\tAddress=>" + address);
        System.out.println("CTRL DECODE:OPCODE=>" + opcode + "\tGPR=>" + R + "\tIX=>" + IX + "\tI=>" + I + "\tAddress=>" + address);
    }


    public int getOpcode() {
        return opcode;
    }

    public int getAddress() {
        return address;
    }

    public int getIX() {
        return IX;
    }

    public int getRx() {
        return Rx;
    }

    public int getRy() {
        return Ry;
    }

    public int getAL() {
        return AL;
    }

    public int getLR() {
        return LR;
    }

    public int getCount() {
        return Count;
    }

    public int getR() {
        return R;
    }

    public int getTrapCode() {
        return TrapCode;
    }

    public int getI() {
        return I;
    }

    /**
     * Convert The input value to 16bit binary String.
     *
     * @param value The int value to be converted.
     * @return A 16 bit String.
     */
    private String ToBinaryString(int value) {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        String Stringlength = "" + 16;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));
    }

}
