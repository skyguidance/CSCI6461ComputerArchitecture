package CPU;

import java.util.HashMap;
import java.util.logging.Logger;

public class Memory {
    HashMap<Integer, Integer> memory;
    final Logger logging = Logger.getLogger("CPU.Memory");

    public Memory() {
        memory = new HashMap<Integer, Integer>();
    }

    public void set(String address, String value) {
        int addressInt = Integer.valueOf(address, 2);
        if (addressInt < 4096 && value.length() <= 16) {
            memory.put(addressInt, Integer.valueOf(value));
            logging.info("MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else {
            logging.severe("INVALID:MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");
            return;
        }
    }

    public int get(int address) {

        if (memory.containsKey(address)) {
            return memory.get(address);
        } else {
            logging.severe("INVALID:MEM[" + address + "(" + address + ")" + "],NO DATA IN THIS ADDRESS!");
            return -1;
        }
    }
}
