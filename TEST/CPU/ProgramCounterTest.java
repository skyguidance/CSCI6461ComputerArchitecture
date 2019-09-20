package CPU;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramCounterTest {
    ProgramCounter test = new ProgramCounter (30);

    int value = test.value;

    @Test
    void incrementOne() {
        test.incrementOne();
        assertEquals(test.getValue(), this.value+1);
    }

   }