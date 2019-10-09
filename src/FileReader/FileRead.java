package FileReader;

import java.io.*;

public class FileRead {
    private Reader fileRead;

    public FileRead(Reader fileRead) {
        this.fileRead = fileRead;
    }


    public void Read() {
        int InstrLocation = 6;
        try (BufferedReader br = new BufferedReader(fileRead)) {
            String line;
            while ((line = br.readLine()) != null) {
                String convertString;
                if (line.startsWith("#") || line.equals("")) {
                    continue;
                } else if (line.startsWith("MEM")) {
                    convertString = ReadMEMtype(line);
                    appendStrToFile(convertString);
                    continue;

                } else {
                    convertString = ReadandConvertOneLine(line);
                    convertString = ToBinaryStringAddress(InstrLocation) + "," + convertString;
                    appendStrToFile(convertString);
                    InstrLocation++;
                    continue;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String ReadMEMtype(String line) {
        String linepart[] = line.split(" ");
        String secondpart[] = linepart[1].split("->");
        String binarycode = ToBinaryStringAddress(Integer.valueOf(secondpart[0])) + "," + ToBinaryStringValue(Integer.valueOf(secondpart[1])) + "\n";

        return binarycode;
    }

    private String ReadandConvertOneLine(String line) {
        System.out.println(line);
        String linepart[] = line.split(" ");
        if (linepart[0].equals("HALT")){
            return "0000000000000000\n";

        }
        String opcode = "";
        String R = "";
        String IX = "";
        String I = "";
        String Address = "";
        String binaryLine;
        String secondpart[] = linepart[1].split(",");
        Address = ToBinaryString(Integer.valueOf(secondpart[secondpart.length - 1].split("\\[")[0]), 5);
        I = secondpart[secondpart.length - 1].split("\\[")[1].substring(0, 1);
        if (secondpart.length == 3) {
            R = ToBinaryString(Integer.valueOf(secondpart[0]), 2);
            IX = ToBinaryString(Integer.valueOf(secondpart[1]), 2);
        }
        if (secondpart.length == 2) {
            R = "00";
            IX = ToBinaryString(Integer.valueOf(secondpart[0]), 2);
        }

        switch (linepart[0]) {
            case "HALT":
                opcode = "000000";
            case "LDR":
                opcode = "000001";
                break;
            case "STR":
                opcode = "000010";
                break;
            case "LDA":
                opcode = "000011";
                break;
            case "AMR":
                opcode = "000100";
                break;
            case "SMR":
                opcode = "000101";
                break;
            case "AIR":
                opcode = "000110";
                break;
            case "SIR":
                opcode = "000111";
                break;

            case "JZ":
                opcode = "001010";
                break;
            case "JNE":
                opcode = "001011";
            case "JCC":
                opcode = "001100";
                break;
            case "JMA":
                opcode = "001101";
                break;
            case "JSR":
                opcode = "001110";
                break;
            case "RFS":
                opcode = "001111";
                break;
            case "SOB":
                opcode = "010000";
                break;
            case "JGE":
                opcode = "010001";
                break;
            case "MLT":
                opcode = "010100";
                break;
            case "DVD":
                opcode = "010101";
                break;
            case "TRR":
                opcode = "010110";
                break;
            case "AND":
                opcode = "010111";
                break;
            case "ORR":
                opcode = "011000";
                break;
            case "NOT":
                opcode = "011001";
                break;
            case "SRC":
                opcode = "011111";
                break;
            case "RRC":
                opcode = "100000";
                break;
            case "FADD":
                opcode = "100001";
                break;
            case "FSUB":
                opcode = "100010";
                break;
            case "VADD":
                opcode = "100011";
                break;
            case "VSUB":
                opcode = "100100";
                break;
            case "CNVRT":
                opcode = "100101";
                break;
            case "LDFR":
                opcode = "110010";
                break;
            case "STFR":
                opcode = "110011";
                break;

            case "LDX":
                opcode = "101001";
                break;
            case "STX":
                opcode = "101010";
                break;
            case "IN":
                opcode = "111101";
                break;
            case "OUT":
                opcode = "111110";
                break;
            case "CHK":
                opcode = "111111";
                break;



            default:
                break;
        }
        binaryLine = opcode + R + IX + I + Address;
        return binaryLine + "\n";
    }

    private static void appendStrToFile(String str) {
        try {
            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter("Instruction.csv", true));
            out.write(str);
            out.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }

    public String ToBinaryStringAddress(int value) {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        String Stringlength = "" + 12;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));//
    }

    public String ToBinaryStringValue(int value) {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        String Stringlength = "" + 16;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));//
    }

    public String ToBinaryString(int value, int digitnumber) {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        String Stringlength = "" + digitnumber;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));//
    }


    public static void main(String[] args) {
        try {
            String txtPath = "C:\\Users\\QiTianYi\\Desktop\\1.txt";
            File file = new File(txtPath);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            FileRead converter = new FileRead(reader);
            converter.Read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

