package CPU;

import org.junit.jupiter.api.Test;

import java.awt.desktop.SystemEventListener;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {



    Register PC = new Register (30, 12 , "PC");
    Register IR = new Register (30, 16 , "IR");
    Register MAR = new Register (30, 16 , "MAR");
    Register MBR = new Register (300, 16 , "MBR");
    Register MFR = new Register (30, 16 , "MFR");
    Register R0 = new Register (30, 16 , "R0");
    Register R1 = new Register (30, 16 , "R1");
    Register R2 = new Register (30, 16 , "R2");
    Register R3 = new Register (30, 16 , "R3");
    Register IX1 = new Register (30, 16 , "IX1");
    Register IX2 = new Register (30, 16 , "IX2");
    Register IX3 = new Register (30, 16 , "IX3");




    int size ;
    int C;
    void TBS(Register V) {


        for (size =0 ; size< IR.ToBinaryString().length(); size++) {

            C = IR.ToBinaryString().indexOf(size);
            if (C == 0) {
                assertEquals(0,C);
                break;
            }
            if (C == 1){
                assertEquals(1,C);
                break;

            }

        }
        assertEquals(16 , size);

    }

    @Test
    void BinaryString() {
        TBS(IR);TBS(MAR);TBS(MBR);TBS(MFR);
        TBS(R0);TBS(R1);TBS(R2);TBS(R3);
        TBS(IX1);TBS(IX2);TBS(IX3);

        /// PC

        for (size =0 ; size< PC.ToBinaryString().length(); size++) {

            C = PC.ToBinaryString().indexOf(size);
            if (C == 0) {
                assertEquals(0,C);
                break;
            }
            if (C == 1){
                assertEquals(1, C, "This is a problem with converting to binary");
                break;

            }

        }
        assertEquals(12, size, "PC length is not 12 bits");



    }

}