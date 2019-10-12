package Memory;

import java.util.Vector;
import java.util.logging.Logger;
/*
this a Memory with cache
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


    public static int getWordLength() {
        return WORD_LENGTH;
    }

    public int getMemoryLength() {
        return MEMORY_LENGTH;
    }


    // this add element to cache and remove the extra
    public void addElementtoCache(MemoryData newData) {
        cache.add(0, newData);
        cache.setSize(16);
    }

    public int get(String address) {
        int IntAddress = Integer.valueOf(address, 2);
        return get(IntAddress);
    }
/*
get will check cache first if not exist check remaining memory;
 */
    public int get(int address) {
        //check cache
        for (int i = 0; i < cache.size(); i++) {
            MemoryData current = cache.elementAt(i);
            if (current.address == address) {
                return current.value;
            }
        }


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

/*
   set method by address value
   @parameter userOrNot true for user
   @parameter DecOrBinary true for Decimal
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
/*
 set with int input
 @parameter UserOrNot true for user
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
/*
print every thing in cache
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
    /*
        print everything in memory
     */

    public void PrintHashMap() {
        for (int i = 0; i < MEMORY_LENGTH; i++) {
            if (Memory[i] == null) {
                continue;
            }
            System.out.println("DUMP:MEM[" + ToBinaryString(Memory[i].address) + "(" + Memory[i].address + ")" + "]=>" + ToBinaryString(Memory[i].value) + "(" + Memory[i].value + ")");
            logging.info("DUMP:MEM[" + ToBinaryString(Memory[i].address) + "(" + Memory[i].address + ")" + "]=>" + ToBinaryString(Memory[i].value) + "(" + Memory[i].value + ")");
        }

    }
}
