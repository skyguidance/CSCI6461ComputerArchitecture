package CPU;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Condition_CodeTest {
    boolean value;
private Condition_Code test = new Condition_Code(value);



    @Test
    void set() {
        test.set(value);


    }

    @Test
    void get() {
       System.out.println(test.get());
          }



}