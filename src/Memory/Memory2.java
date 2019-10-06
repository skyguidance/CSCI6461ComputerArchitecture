package Memory;

import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Logger;

public class Memory2 {
    private final static int WORD_LENGTH = 16;
    private  int MEMORY_LENGTH = 2048;
    public  MemoryData[] Memory ;
    public static Vector<MemoryData> cache = new Vector<MemoryData>(16);
    final Logger logging = Logger.getLogger("Memory");

    public Memory2() {
        Memory= new MemoryData[MEMORY_LENGTH];
    }
    /**
     * Expand the MEM to 4096.
     */
    public void expandMEM() {
        MEMORY_LENGTH = 4096;
        Memory= new MemoryData[MEMORY_LENGTH];
    }

    /**
     * Shrink the MEM to 2048.
     */
    public void shrinkMEM() {
        MEMORY_LENGTH = 2048;
        Memory= new MemoryData[MEMORY_LENGTH];
    }



    public static int getWordLength() {
        return WORD_LENGTH;
    }

    public  int getMemoryLength() {
        return MEMORY_LENGTH;
    }




    // this add element to cache and remove the extra
    public void addElementtoCache(MemoryData newData){
        cache.add(0,newData);
        cache.setSize(16);
    }
    public int get(String address) {
        int IntAddress = Integer.valueOf(address, 2);
        return get(IntAddress);
    }

    public int get(int address){
        //check cache
        for (int i = 0; i < cache.size(); i++){
            MemoryData current=cache.elementAt(i);
            if(current.address==address){
                return current.value;
            }
        }


        //check memory
        for (int i = 0; i < Memory.length; i++) {
            MemoryData current=Memory[i];
            if(current.address==address){
                return current.value;
            }

        }
        logging.info("Did not find info on the address, check if you have valid address");
        return -1;
    }




    public void set(String address,String value,boolean UserOrNot,boolean DecOrBinary) {
        int IntAddress = Integer.valueOf(address, 2);
        int IntValue = Integer.valueOf(value, 2);
        if(DecOrBinary){
            IntAddress=Integer.valueOf(address);
            IntValue = Integer.valueOf(value);
        }

        if(UserOrNot&&IntAddress<6){
        }
        else {
        set(IntAddress,IntValue);}
    }

    public void set(int address, int value){
        MemoryData current= new MemoryData(address,value);
       if(address>Memory.length||value>65536){

       }
       else {
           //set cache
           addElementtoCache(current);
           //set memory
           Memory[address] = current;
       }
    }
    public String ToBinaryString(int value) {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        String Stringlength = "" + 16;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));//
    }

    public void PrintCache() {
        cache.forEach((n) -> print(n));
    }
    public static void print(MemoryData n)
    {
        System.out.println("Object Details");
        System.out.println("address : " + n.address);
        System.out.println("value : " + n.value);
    }







}
