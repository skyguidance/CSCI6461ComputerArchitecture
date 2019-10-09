package FileReader;

import java.io.*;

public class FileRead {
    private  Reader fileRead;

    public FileRead(Reader fileRead) {
        this.fileRead=fileRead;
        }
    public void Read() {

        try (BufferedReader br = new BufferedReader(fileRead)) {
            String line;
            while ((line = br.readLine())!=null) {
                String convertString;
                if (line.startsWith("#")) {
                }
                if(line.startsWith("MEM")){
                 convertString= ReadMEMtype(line);
                  appendStrToFile(convertString);

              }
              else {
                convertString=ReadandConvertOneLine(line);
                  appendStrToFile(convertString);
              }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String ReadMEMtype(String line){
        String linepart[]=line.split(" ");
        String secondpart[]=linepart[1].split("->");
        String binarycode = Integer.toBinaryString(Integer.valueOf(secondpart[0]))+Integer.toBinaryString(Integer.valueOf(secondpart[1]));

        return binarycode;
    }

    private String ReadandConvertOneLine(String line){

        String linepart[]=line.split(" ");
        String opcode="";
        String R="";
        String IX="";
        String I="";
        String Address="";
        String binaryLine;
        String secondpart[]=linepart[1].split(",");
        Address=String.format("%05",Integer.valueOf(secondpart[secondpart.length-1].split("\\[")[0]));
        I=secondpart[secondpart.length-1].split("\\[")[1].substring(0);
        if(secondpart.length==3){
           R=String.format("%02",Integer.valueOf(secondpart[0]));
           IX=String.format("%02",Integer.valueOf(secondpart[1]));
        }

        switch (linepart[0]){
            case "LDR":
                opcode="000001";
                break;
            case "STR":
                opcode="000010";
                break;
            case "LDA":
                opcode="000011";
                break;
            case "LDX":
                opcode="101001";
                String.format("%02",Integer.valueOf(secondpart[0]));
                break;
            case "STX":
                opcode="101010";
                IX=String.format("%02",Integer.valueOf(secondpart[0]));
                break;
            case "JZ":
                opcode="001010";
                        break;
        }
        binaryLine=opcode+R+IX+I+Address;
        return  binaryLine+"/n";
    }
    private static void appendStrToFile(String str)
    {
        try {
            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter("Instruction.csv", true));
            out.write(str);
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
}
