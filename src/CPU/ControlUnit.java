package CPU;

public class ControlUnit {

    private int opcode,I,R,IX,address;


    public ControlUnit(String BinaryString){
        opcode=Integer.valueOf(BinaryString.substring(0,6),2);
        R=Integer.valueOf(BinaryString.substring(6,8),2);
        IX=Integer.valueOf(BinaryString.substring(8,10),2);
        I=Integer.valueOf(BinaryString.substring(10,11),2);
        address=Integer.valueOf(BinaryString.substring(11,16),2);

    }

    public void decodeInstruction(int IR){
        String BinaryString=ToBinaryString(IR);
        opcode=Integer.valueOf(BinaryString.substring(0,6),2);
        R=Integer.valueOf(BinaryString.substring(6,8),2);
        IX=Integer.valueOf(BinaryString.substring(8,10),2);
        I=Integer.valueOf(BinaryString.substring(10,11),2);
        address=Integer.valueOf(BinaryString.substring(11,16),2);
    }

    int getOpcode(){
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

    private String ToBinaryString(int value) {
        String a=Integer.toBinaryString(value);// Change to BinaryString
        String Stringlength=""+16;
        String format="%0numberd".replace("number", Stringlength);
        return String.format(format,Long.valueOf(a));//
    }

}
