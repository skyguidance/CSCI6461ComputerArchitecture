package CPU;

public class ControlUnit {
    int opcode;
    int R;
    int IX;
    int I;
    int address;

    public ControlUnit(String BinaryString){
        opcode=Integer.valueOf(BinaryString.substring(0,5),2);
        R=Integer.valueOf(BinaryString.substring(6,7),2);
        IX=Integer.valueOf(BinaryString.substring(8,9),2);
        I=Integer.valueOf(BinaryString.substring(10),2);
        address=Integer.valueOf(BinaryString.substring(11,15),2);

    }
    public void setInstruction(int IR){
        String BinaryString=Integer.toBinaryString(IR);
        opcode=Integer.valueOf(BinaryString.substring(0,5),2);
        R=Integer.valueOf(BinaryString.substring(6,7),2);
        IX=Integer.valueOf(BinaryString.substring(8,9),2);
        I=Integer.valueOf(BinaryString.substring(10),2);
        address=Integer.valueOf(BinaryString.substring(11,15),2);
    }

    public int getOpcode(){
        return opcode;
    }

    public int getAddress() {
        return address;
    }

    public int getIX() {
        return IX;
    }

    public int getR() {
        return R;
    }

    public int getI() {
        return I;
    }

}
