package Memory;


import java.util.HashMap;
import java.util.logging.Logger;

/**
 * The DataMemory Class (DM).
 */
public class Memory {
    // Use HashMap to save data.
    HashMap<Integer, Integer> memory;
    // The memory upper bound. (A.K.A Memory Size.)
    private int DMADDRESSMAX = 2048;

    final Logger logging = Logger.getLogger("Memory");

    public Memory() {
        memory = new HashMap<>();
    }

    /**
     * Expand the MEM to 4096.
     */
    public void expandMEM() {
        DMADDRESSMAX = 4096;
    }

    /**
     * Shrink the MEM to 2048.
     */
    public void shrinkMEM() {
        DMADDRESSMAX = 2048;
    }

    /**
     * Set the memory value.(Force Set)
     * <p>
     * !!! WARNING: THIS IS THE FORCE SET. NO MATTER THE MEM AREA IS PROTECTED OR NOT!
     * <p>
     * If the memory address and the value is legit, set the value.
     * Else print an INVALID operation log and do nothing.
     *
     * @param address The Binary String of the address (12 bit)
     * @param value   The Binary String of the value (16 bit)
     */
    public void set(String address, String value) {
        int addressInt = Integer.valueOf(address, 2);
        // if the address is legit, do the MEMWrite.
        if (addressInt < DMADDRESSMAX && value.length() <= 16) {
            memory.put(addressInt, Integer.valueOf(value, 2));
            logging.info("MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            System.out.println("MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
        }
        // The address is not legit.
        else {
            logging.severe("INVALID:MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");
            System.out.println("INVALID:MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");
        }
    }

    /**
     * Set the memory value.
     * If the memory address and the value is legit, set the value.
     * Else print an INVALID operation log and do nothing.
     * If the UserOrNot Flag is Setto True. Then Preform a User Set.(Set to protected area will be rejected.)
     * Else Do a force set.
     *
     * @param address   int address.
     * @param value     int value.
     * @param UserOrNot If true, preform a UserSet. Else force Set.
     */
    public void set(int address, int value, boolean UserOrNot) {
        // A UserSet, and the address is legit.
        if (UserOrNot && address > 5 && address < DMADDRESSMAX && value <= 65536) {
            memory.put(address, value);
            logging.info("MEM[" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");
            System.out.println("MEM[" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");
        }
        // A force set, and the address is legit.
        else if (!UserOrNot && address < DMADDRESSMAX && value <= 65536) {
            memory.put(address, value);
            logging.info("MEM[" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");
            System.out.println("MEM[" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");

        }
        // The address is not legit.
        else {
            logging.severe("INVALID:MEM[" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");
            System.out.println("INVALID:MEM[" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");

        }
    }

    /**
     * Do a UserSet of the Memory.
     *
     * @param address     The String of the address
     * @param value       The String of the value
     * @param DecOrBinary If true, than consider the input is a Binary String. Else consider as a Dec String.
     */
    public void UserSet(String address, String value, boolean DecOrBinary) {
        // This set function protect the reserved memory area.
        int addressInt;
        int valueInt;
        // The input is a binary String.
        if (DecOrBinary) {
            addressInt = Integer.valueOf(address, 2);
            valueInt = Integer.valueOf(value, 2);
        }
        // The input is a int value but stored as a String.
        else {
            addressInt = Integer.valueOf(address);
            valueInt = Integer.valueOf(value);
        }
        // Do the UserSet Function.
        set(addressInt, valueInt, true);
    }

    /**
     * Read the memory.
     *
     * @param address The Memory address (Binary String)
     * @return the int value of the data.(MEM[address])
     */
    public int get(String address) {
        int IntAddress = Integer.valueOf(address, 2);
        return get(IntAddress);
    }

    /**
     * Read the memory.
     * If the address is not legit, print an INVALID operation log and return all zero.
     *
     * @param address The Memory address (Int).
     * @return the int value of the data.(MEM[address])
     */
    public int get(int address) {
        // The Memory has value in it. Return the value.
        if (memory.containsKey(address)) {
            return memory.get(address);
        }
        // Else. No value or the address is not legit.
        else {
            logging.severe("INVALID:MEM[" + ToBinaryString(address) + "(" + address + ")" + "],NO DATA IN THIS ADDRESS!");
            System.out.println("INVALID:MEM[" + ToBinaryString(address) + "(" + address + ")" + "],NO DATA IN THIS ADDRESS!");
            return 0;
        }
    }

    /**
     * Convert The input value to 16bit binary String.
     *
     * @param value The int value to be converted.
     * @return A 16 bit String.
     */
    public String ToBinaryString(int value) {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        String Stringlength = "" + 16;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));//
    }

    /**
     * Dump the entire memory data.
     */
    public void PrintHashMap() {
        for (Integer key : memory.keySet()) {
            System.out.println("DUMP:MEM[" + ToBinaryString(key) + "(" + key + ")" + "]=>" + ToBinaryString(memory.get(key)) + "(" + memory.get(key) + ")");
        }
    }

}
