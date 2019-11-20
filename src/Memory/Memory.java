package Memory;

import java.util.Vector;
import java.util.logging.Logger;

/**
 * This is the Memory and the cache class.
 */
public class Memory {
    private final static int WORD_LENGTH = 16;
    private int MEMORY_LENGTH = 2048;
    public MemoryData[] Memory;
    public static Vector<MemoryData> cache = new Vector<MemoryData>(16);
    final Logger logging = Logger.getLogger("Memory");

    public Memory() {
        Memory = new MemoryData[MEMORY_LENGTH];
    }

    /**
     * Expand the MEM to 4096.
     */
    public void expandMEM() {
        MEMORY_LENGTH = 4096;
        Memory = new MemoryData[MEMORY_LENGTH];
    }

    /**
     * Shrink the MEM to 2048.
     */
    public void shrinkMEM() {
        MEMORY_LENGTH = 2048;
        Memory = new MemoryData[MEMORY_LENGTH];
    }


    /**
     * Get the current word length.
     *
     * @return the length of the WORD.
     */
    public static int getWordLength() {
        return WORD_LENGTH;
    }

    /**
     * Get the current memory length.
     *
     * @return the current memory capacity.
     */
    public int getMemoryLength() {
        return MEMORY_LENGTH;
    }


    /**
     * add element to cache and remove the extra (The FIFO Algorithm)
     * The logic is to adding to the first place and set to only accept the first 16 value.
     *
     * @param newData the new data you try to add.
     */
    public void addElementtoCache(MemoryData newData) {
        cache.add(0, newData);
        cache.setSize(16);
    }

    /**
     * Get a value from the cache first, if not exits, find the value from the memory.
     *
     * @param address the address you want to visit.
     * @return The value.
     */
    public int get(String address) {
        int IntAddress = Integer.valueOf(address, 2);
        return get(IntAddress);
    }

    /**
     * Get a value from the cache first, if not exits, find the value from the memory.
     *
     * @param address the address you want to visit.
     * @return The value.
     */
    public int get(int address) {
        //check cache
        for (int i = 0; i < cache.size(); i++) {
            MemoryData current = cache.elementAt(i);
            if (current.address == address) {
                logging.info("Cache HIT!");
                System.out.println("Cache HIT!");
                return current.value;
            }
        }

        logging.info("Cache Miss!");
        System.out.println("Cache Miss!");
        //check memory
        for (int i = 0; i < Memory.length; i++) {
            if (Memory[i] == null) {
                continue;
            }
            MemoryData current = Memory[i];
            if (current.address == address) {

                return current.value;
            }

        }
        logging.severe("Did not find info on the address, check if you have valid address");
        return 0;
    }


    /**
     * set the memory. and the cache.
     *
     * @param address     the address.
     * @param value       the value you try to set.
     * @param UserOrNot   true for userset.(Writing to protected location will be prohibited.)
     * @param DecOrBinary true for Binary.
     */
    public void set(String address, String value, boolean UserOrNot, boolean DecOrBinary) {
        int IntAddress = Integer.valueOf(address, 2);
        int IntValue = Integer.valueOf(value, 2);
        if (DecOrBinary) {
            IntAddress = Integer.valueOf(address);
            IntValue = Integer.valueOf(value);
        }

        if (UserOrNot && IntAddress < 6) {
            logging.severe("User try to set protected Memory.");
            System.out.println("User try to set protected Memory.");
        } else {
            set(IntAddress, IntValue);
        }
    }

    /**
     * Do a UserSet.
     *
     * @param address     the address.
     * @param value       the value you try to set.
     * @param DecOrBinary true for Binary.
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
        if (addressInt < 6) {
            logging.severe("User try to set protected Memory.");
            System.out.println("User try to set protected Memory.");
        } else {
            set(addressInt, valueInt);
        }
    }

    /**
     * Set the value.
     *
     * @param address   the address.
     * @param value     the value you try to set.
     * @param UserOrNot true for userset.(Writing to protected location will be prohibited.)
     */
    public void set(int address, int value, boolean UserOrNot) {
        if (UserOrNot && address < 6) {
            logging.severe("User try to set protected Memory.");
            System.out.println("User try to set protected Memory.");
        } else {
            set(address, value);
        }
    }


    public void set(int address, int value) {
        MemoryData current = new MemoryData(address, value);
        if (address > Memory.length || value > 65536) {
            logging.severe("INVALID:MEM[" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");
            System.out.println("INVALID:MEM[" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");
        } else {
            //set cache
            addElementtoCache(current);
            //set memory
            Memory[address] = current;
            logging.info("MEM[" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");
            System.out.println("MEM[" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");
        }
    }

    public void set(String address, String value) {
        int IntAddress = Integer.valueOf(address, 2);
        int IntValue = Integer.valueOf(value, 2);
        MemoryData current = new MemoryData(IntAddress, IntValue);
        if (IntAddress > Memory.length || IntValue > 65536) {
            logging.severe("INVALID:MEM[" + ToBinaryString(IntAddress) + "(" + IntAddress + ")" + "]=>" + ToBinaryString(IntValue) + "(" + IntValue + ")");
            System.out.println("INVALID:MEM[" + ToBinaryString(IntAddress) + "(" + IntAddress + ")" + "]=>" + ToBinaryString(IntValue) + "(" + IntValue + ")");
        } else {
            //set cache
            addElementtoCache(current);
            //set memory
            Memory[IntAddress] = current;
            logging.info("MEM[" + ToBinaryString(IntAddress) + "(" + IntAddress + ")" + "]=>" + ToBinaryString(IntValue) + "(" + IntValue + ")");
            System.out.println("MEM[" + ToBinaryString(IntAddress) + "(" + IntAddress + ")" + "]=>" + ToBinaryString(IntValue) + "(" + IntValue + ")");
        }
    }

    public String ToBinaryString(int value) {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        String Stringlength = "" + 16;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));//
    }

    /**
     * Dump the cache.
     * Print each value of the cache.
     */
    public void PrintCache() {
        int address;
        int value;
        System.out.println("CACHE DUMP ...");
        logging.info("CACHE DUMP ...");
        for (int i = 0; i < cache.size(); i++) {
            address = cache.get(i).address;
            value = cache.get(i).value;
            System.out.println("DUMP:CACHE #" + i + " [" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");
            logging.info("DUMP:CACHE #" + i + " [" + ToBinaryString(address) + "(" + address + ")" + "]=>" + ToBinaryString(value) + "(" + value + ")");
        }
        System.out.println("TOTAL DUMPED " + cache.size() + " CACHE LOGS.");
        logging.info("TOTAL DUMPED " + cache.size() + " CACHE LOGS.");
    }

    /**
     * Dump memory.
     * Print Each value of the memory (if it is not empty.)
     */
    public void PrintHashMap() {
        for (int i = 0; i < MEMORY_LENGTH; i++) {
            if (Memory[i] == null) {
                continue;
            }
            if(Memory[i].value <= 127){
                char castToChar = (char)Memory[i].value;
                try {
                    System.out.println("DUMP:MEM[" + ToBinaryString(Memory[i].address) + "(" + Memory[i].address + ")" + "]=>" + ToBinaryString(Memory[i].value) + "(" + Memory[i].value + ")" + "Cast:" + String.valueOf(castToChar));
                    logging.info("DUMP:MEM[" + ToBinaryString(Memory[i].address) + "(" + Memory[i].address + ")" + "]=>" + ToBinaryString(Memory[i].value) + "(" + Memory[i].value + ")" + "Cast:" + String.valueOf(castToChar));
                } catch (Exception e){
                    System.out.println("DUMP:MEM[" + ToBinaryString(Memory[i].address) + "(" + Memory[i].address + ")" + "]=>" + ToBinaryString(Memory[i].value) + "(" + Memory[i].value + ")");
                    logging.info("DUMP:MEM[" + ToBinaryString(Memory[i].address) + "(" + Memory[i].address + ")" + "]=>" + ToBinaryString(Memory[i].value) + "(" + Memory[i].value + ")");
                }
            }
            else {
                System.out.println("DUMP:MEM[" + ToBinaryString(Memory[i].address) + "(" + Memory[i].address + ")" + "]=>" + ToBinaryString(Memory[i].value) + "(" + Memory[i].value + ")");
                logging.info("DUMP:MEM[" + ToBinaryString(Memory[i].address) + "(" + Memory[i].address + ")" + "]=>" + ToBinaryString(Memory[i].value) + "(" + Memory[i].value + ")");
            }

        }

    }

    /**
     * Dump memory.
     * Redirect Each value of the memory as a string (if it is not empty.)
     */
    public String DumpMemoryAsString() {
        String result = "";
        for (int i = 0; i < MEMORY_LENGTH; i++) {
            if (Memory[i] == null) {
                continue;
            }
            if(Memory[i].value <= 127){
                char castToChar = (char)Memory[i].value;
                try {
                    result = result + "DUMP:MEM[" + ToBinaryString(Memory[i].address) + "(" + Memory[i].address + ")" + "]=>" + ToBinaryString(Memory[i].value) + "(" + Memory[i].value + ")" + "Cast:" + String.valueOf(castToChar) + "\n";

                } catch (Exception e){
                    result = result + "DUMP:MEM[" + ToBinaryString(Memory[i].address) + "(" + Memory[i].address + ")" + "]=>" + ToBinaryString(Memory[i].value) + "(" + Memory[i].value + ")"+ "\n";
                }
            }
            else {
                result = result + "DUMP:MEM[" + ToBinaryString(Memory[i].address) + "(" + Memory[i].address + ")" + "]=>" + ToBinaryString(Memory[i].value) + "(" + Memory[i].value + ")"+ "\n";
            }

        }
        return result;
    }
}
