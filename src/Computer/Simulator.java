package Computer;

import CPU.ALU;
import CPU.Componets;
import Memory.Memory;
import CPU.Bus;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;


public class Simulator {

    public Componets componets;
    public Memory DataMemory;
    public ALU ALU;
    public Bus BUS;
    private ArrayList UserProgram = new ArrayList();
    public Integer UserProgramLength;


    final Logger logging = Logger.getLogger("CPU.Simulator");

    public Simulator() {
        initialize();

    }

    public void initialize() {
        componets = new Componets();
        DataMemory = new Memory();
        ALU = new ALU(componets, DataMemory);
        BUS = new Bus(componets, DataMemory);
    }


    public void loadMEMfromFile(File file) {
        try {
            int i = 0;
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] buff = line.split(",");
                //buff[0]=address;buff[1]=data;
                DataMemory.UserSet(buff[0], buff[1], true);
                i++;
            }
            System.out.println("[MEMLOAD]SET " + i + " MEMORY DATA TOTAL.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    //// user should have right to chose where their file come from
//    public void bootLoader() {
//        String txtPath = "C:\\Users\\QiTianYi\\Desktop\\UserProgram.txt";
//        loadUserProgramFromTxt(txtPath);
//        System.out.println("DEBUG: WE SET THE USER PROGRAM LOCATION TO 40(FIRST INSTR).");
//        int i= 0;
//        for(i=0;i<UserProgram.size();i++){
//            DataMemory.set(40+i, (String) UserProgram.get(i));
//        }
//        componets.getPC().setValue(40);
//        UserProgramLength = i;
//        System.out.println("DEBUG: THE LENGTH OF THE USER RROGRAM:"+UserProgramLength);
//
//    }
//
//    private void loadUserProgramFromTxt(String txtPath){
//        try {
//            int i = 0;
//            File file = new File(txtPath);
//            InputStreamReader reader = new InputStreamReader(
//                    new FileInputStream(file));
//            BufferedReader br = new BufferedReader(reader);
//            String line = null;
//            while((line = br.readLine()) != null){
//                if (line.length()>16){
//                    line = line.substring(0,16);
//                }
//                UserProgram.add(line);
//                i++;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void RunUserProgram(){
//        for(int i=0;i<UserProgramLength;i++){
//            BUS.tik();
//        }
//    }
//
//    public void SingleStepUserProgram(){
//        BUS.tik();
//    }

/*
    //DEBUG Use only...
    public static void main(String[] args) {


        Simulator simulator = new Simulator();
        simulator.DataMemory.set(31, 666);
        simulator.DataMemory.set(666, 123);


        //TESTING AUTORUN...
        System.out.println("Testing Autotest...");
        simulator.DataMemory.set(2000, "0000011100111111");
        simulator.DataMemory.set(2001, "0000011000111111");
        simulator.DataMemory.set(2002, "0000010100111111");
        simulator.DataMemory.set(2003, "0000010000111111");
        simulator.componets.PC.setValue(2000);
        int i = 0;
        for (i = 0; i < 4; i++) {
            simulator.BUS.tik();
        }


        //LDR
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000011100111111", 2));
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000011000111111", 2));
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000010100111111", 2));
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000010000111111", 2));
        //STR
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000101100011111", 2));
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000101000011111", 2));
        //LDA
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000111100011111", 2));
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000111000111111", 2));
        //LDX
        simulator.DataMemory.set(30, 789);
        simulator.BUS.evaulateInstruction(Integer.valueOf("1010010001011110", 2));
        //STX
        simulator.componets.IX1.setValue(987);
        simulator.BUS.evaulateInstruction(Integer.valueOf("1010100001011110", 2));
        //NOP
        simulator.BUS.evaulateInstruction(Integer.valueOf("0000000000000000", 2));
    }

*/
//
//    public static void main(String[] args) {
//        Simulator simulator = new Simulator();
//        simulator.bootLoader();
//    }
//}

