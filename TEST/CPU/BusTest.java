package CPU;

import Memory.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusTest {
    public Componets componets;

    public Memory dataMemory;

    public Bus test = new Bus(componets,dataMemory);

    @Test
    public void getHaltStatus()  {

        assertFalse(test.getHaltStatus());
    }

    @Test
    void setHalt() {
        test.setHalt();

    }


    void tik() {
       // MAR <- PC,PC++;

   }


    void run() {
        while(true){
            test.tik();
    assertFalse(test.isHalt);
                break;
            }
        }



    @Test
    void evaulateInstruction() {



    }
}