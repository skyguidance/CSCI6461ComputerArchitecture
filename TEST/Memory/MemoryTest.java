package Memory;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTest {
    private Memory test = new Memory ();



    @Test
    void expandMEM() {

        HashMap <Integer, Integer> M;
        M = new HashMap<>();

        test.expandMEM();

        M = test.memory;

        int size = M.size() ;
        System.out.println(M);





    }

    @Test
    void shrinkMEM() {

    }

    @Test
    void set() {
    }

    @Test
    void testSet() {
    }

    @Test
    void userSet() {
    }

    @Test
    void get() {
    }

    @Test
    void testGet() {
    }

    @Test
    void toBinaryString() {
    }

    @Test
    void printHashMap() {
    }
}