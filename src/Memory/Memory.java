package Memory;


import java.util.HashMap;
import java.util.logging.Logger;

public class Memory {
    HashMap<Integer, Integer> memory;
    final Logger logging = Logger.getLogger("CPU.Memory");

    public Memory() {
        memory = new HashMap<>();
    }

    public void set(String address, String value) {
        int addressInt = Integer.valueOf(address, 2);
        if (addressInt < 4096 && value.length() <= 16) {
            memory.put(addressInt, Integer.valueOf(value,2));
            logging.info("MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
        } else {
            logging.severe("INVALID:MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");

        }
    }

    public void set(int address, String Value) {
        String addressString = ToBinaryString(address);
        set(addressString,Value);
    }

    public void set(int address, int value){
        String addressString = ToBinaryString(address);
        String valueString = ToBinaryString(value);
        set(addressString,valueString);
    }

    public int get(String address){
        int IntAddress = Integer.valueOf(address,2);
        return get(IntAddress);
    }

    public int get(int address) {

        if (memory.containsKey(address)) {
            return memory.get(address);
        } else {
            logging.severe("INVALID:MEM[" + address + "(" + address + ")" + "],NO DATA IN THIS ADDRESS!");
            return -1;
        }
    }

    public String ToBinaryString(int value) {
        String a=Integer.toBinaryString(value);// Change to BinaryString
        String Stringlength=""+16;
        String format="%0numberd".replace("number", Stringlength);
        return String.format(format,Long.valueOf(a));//
    }
}
