package CPU;

import Memory.Memory;

import java.util.logging.Logger;

/**
 * This is the ALU Class.
 * This module do mathematical or logical computation.
 */

public class ALU {

    private final Logger logging = Logger.getLogger("ALU");

    public int OPCode;
    public int A;
    public int B;
    public int output;
    public int HIResult;
    public int LOResult;
    public boolean CC0,CC1,CC2,CC3;
    public ALU() {
        OPCode = 0;
        A = 0;
        B = 0;
        output = 0;
        CC0 = false;
        CC1 = false;
        CC2 = false;
        CC3 = false;
    }

    public void Calc(int OPCode,int A,int B){
        this.A = A;
        this.B = B;
        this.OPCode = OPCode;
        CC0 = false;
        CC1 = false;
        CC2 = false;
        CC3 = false;
        switch (this.OPCode){
            case 4:{
                output = A + B;
                if (Integer.toBinaryString(output).length() > 16){
                    CC0 = true;
                }
                break;
            }
            case 5:{
                output = A - B;
                if (output<0){
                    CC1 = true;
                }
                break;
            }
            case 6:{
                output = A + B;
                if (Integer.toBinaryString(output).length() > 16){
                    CC0 = true;
                }
                break;
            }
            case 7:{
                output = A - B;
                if (output<0){
                    CC1 = true;
                }
                break;
            }
            case 20:{
                int MULResult = this.A * this.B;
                String MULRes = ToBinaryString(MULResult,32);
                String HI = MULRes.substring(0,16);
                String LO = MULRes.substring(16,32);
                HIResult = Integer.valueOf(HI,2);
                LOResult = Integer.valueOf(LO,2);
                // This is the OVERFLOW Flag(is True,set output =1). TODO:Why there is a overflow?
                CC0 = false;
                break;
            }
            case 21:{
                if (this.B==0){
                    CC2 = false;
                }
                try{
                    HIResult = this.A / this.B;
                    LOResult = this.A % this.B;
                }catch (Exception e) {
                    logging.severe("ALU Java DIV error.");
                    System.out.println("ALU Java DIV error.");
                }
                break;
            }
            case 23:{
                output = A & B;
                break;
            }
            case 24:{
                output = A | B;
                break;
            }
            case 25:{
                output = ~A;
                break;
            }

            default:
                break;
        }
    }

    public String ToBinaryString(int value,int length) {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        int LeftLength = length - a.length();
        for (int i=0;i<LeftLength;i++){
            a = "0" + a;
        }
        return a;
//        String Stringlength = "" + length;
//        String format = "%0numberd".replace("number", Stringlength);
//        return String.format(format, Long.valueOf(a));//
    }

}
