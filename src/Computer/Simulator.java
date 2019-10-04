package Computer;

import CPU.ALU;
import CPU.Componets;
import Interface.IOBuffer;
import Memory.Memory;
import CPU.Bus;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

/**
 * The main simulator class.
 */

public class Simulator {

    public Componets componets;
    public Memory DataMemory;
    public Bus BUS;
    public IOBuffer IOBuffer;
    private ArrayList UserProgram = new ArrayList();
    public Integer UserProgramLength;


    final Logger logging = Logger.getLogger("CPU.Simulator");

    public Simulator() {
        initialize();

    }

    /**
     * initialize all components.
     */
    public void initialize() {
        componets = new Componets();
        DataMemory = new Memory();
        IOBuffer = new IOBuffer();
        BUS = new Bus(componets, DataMemory,IOBuffer);
    }

    /**
     * Load the memory form a file. (CSV file).
     * LOAD MEM expects a CSV file which contains formatted MEM data. It should have 2 fields, which are
     * <p>
     * <Memory Location(Binary String)>,<Data(Binary String)>.
     * <p>
     * Here is an example:
     * <p>
     * 000000000001,0000011100011111
     * 000000000010,0000011100011111
     * 000000000011,0000011100011111
     * 000000000100,0000011100011111
     * <p>
     * The example above load data "0000011100011111" to memory location 01,10,11,100.
     *
     * @param file the file of the memory log.
     */
    public void loadMEMfromFile(File file) {
        try {
            int i = 0;
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String line = null;
            while ((line = br.readLine()) != null) {
                // Split the line with ","
                String[] buff = line.split(",");
                // buff[0] is the address; buff[1] is the data;
                DataMemory.UserSet(buff[0], buff[1], true);
                i++;
            }
            System.out.println("[MEMLOAD]SET " + i + " MEMORY DATA TOTAL.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


