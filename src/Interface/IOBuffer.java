package Interface;

public class IOBuffer {
    private String Buffer;

    public void setBuffer(String input){
        this.Buffer = input;
    }

    public void cleanBuffer(){
        Buffer = "";
    }

    public char getOneDigit(){
        char ret = Buffer.charAt(0);
        Buffer = Buffer.substring(1);
        return ret;
    }

    public int getLength(){
        return Buffer.length();
    }

    public boolean isEmpty(){
        return Buffer.length()==0;
    }
}
