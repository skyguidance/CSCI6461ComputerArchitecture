package Memory;


import CPU.Instruction_Register;
import com.sun.tools.javac.comp.Todo;

import javax.swing.plaf.multi.MultiSeparatorUI;
import java.util.HashMap;
import java.util.logging.Logger;


public class Memory {
    HashMap<Integer, Integer> memory;
    private int DMADDRESSMAX = 2048;

    final Logger logging = Logger.getLogger("Memory");

    public Memory() {
        memory = new HashMap<>();
    }
/*
 size of mem
 */
    public void expandMEM(){
        DMADDRESSMAX = 4096;
    }
/*
size of mem
 */
    public void shrinkMEM(){
        DMADDRESSMAX = 2048;
    }

    /*
       set up address and value of memory
     */
   // TODO make sure if user edit on same address with different value
    public void set(String address, String value) {
        int addressInt = Integer.valueOf(address, 2);
        if (addressInt < DMADDRESSMAX && value.length() <= 16) {
            memory.put(addressInt, Integer.valueOf(value,2));
            logging.info("MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Integer.valueOf(value, 2).toString() + ")");
        } else {
            logging.severe("INVALID:MEM[" + address + "(" + addressInt + ")" + "]=>" + value + "(" + Long.valueOf(value, 2).toString() + ")");
        }
    }

    public void UserSet(String address, String value,boolean DecOrBinary ){
        // This set function protect the reserved memory area.
        int addressInt ;
        int valueInt;
        if(DecOrBinary){
       addressInt = Integer.valueOf(address, 2);
       valueInt= Integer.valueOf(value, 2);
        }else {
            addressInt=Integer.valueOf(address);
            valueInt= Integer.valueOf(value);
        }
        set(addressInt,valueInt,true);
    }

        public void set(int address,int value,boolean UserOrNot ){
        if(UserOrNot&& address>5&& address<DMADDRESSMAX&& value<=65536){
            memory.put(address, value);
            logging.info("MEM[" + address + "(" + address + ")" + "]=>" + value + "(" + value + ")");
        }
    else if (!UserOrNot&&address < DMADDRESSMAX  && value <= 6553){
            memory.put(address, value);
            logging.info("MEM[" + address + "(" + address + ")" + "]=>" + value + "(" + value + ")");

    } else {
        logging.severe("INVALID:MEM[" + address + "(" + address + ")" + "]=>" + value + "(" + value + ")");

    }
}




    public int get(String address){
        int IntAddress = Integer.valueOf(address,2);
        return get(IntAddress);
    }

    public int get(int address) {

        if (memory.containsKey(address)) {
            return memory.get(address);
        } else {
            logging.severe("INVALID:MEM[" + ToBinaryString(address) + "(" + address + ")" + "],NO DATA IN THIS ADDRESS!");
            System.out.println("INVALID:MEM[" + ToBinaryString(address) + "(" + address + ")" + "],NO DATA IN THIS ADDRESS!");
            return 0;
        }
    }


    public String ToBinaryString(int value) {
        String a=Integer.toBinaryString(value);// Change to BinaryString
        String Stringlength=""+16;
        String format="%0numberd".replace("number", Stringlength);
        return String.format(format,Long.valueOf(a));//
    }

    public void PrintHashMap(){
        for (Integer key : memory.keySet()) {
            System.out.println("DUMP:MEM[" + ToBinaryString(key) + "(" + key + ")" + "]=>" + ToBinaryString(memory.get(key)) + "(" + memory.get(key) + ")");
        }
    }

}
