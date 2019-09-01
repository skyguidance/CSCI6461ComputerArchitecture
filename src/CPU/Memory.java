package CPU;

import java.util.HashMap;
import java.util.logging.Logger;

public class Memory {
    HashMap<Integer, String> memory;
    final Logger logging = Logger.getLogger("CPU.Memory");

    public Memory() {
        memory = new HashMap<Integer, String>();
    }

    public void set(String address, String value) {
        int addressInt = Integer.valueOf(address, 2);
        if (addressInt < 4096 && value.length() <= 16) {
            memory.put(addressInt, String.format("%016d", Integer.valueOf(value)));
            logging.info("MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
            return;
        } else {
            logging.severe("INVALID:MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");
            return;
        }
    }

    public String get(String address) {
        int addressInt = Integer.valueOf(address, 2);
        if (memory.containsKey(addressInt)) {
            return memory.get(addressInt);
        } else {
            logging.severe("INVALID:MEM[" + address + "(" + addressInt + ")" + "],NO DATA IN THIS ADDRESS!");
            return "";
        }
    }
}
