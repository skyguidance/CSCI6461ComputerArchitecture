package CPU;

import java.util.logging.Logger;

public class ALU {
    int output;
    final Logger logging = Logger.getLogger("SimulatorLOG");

    public ALU(){
        output = -1;
        return;
    }

    public int execute(int Opcode,int GPR,int IX,int EA){
        logging.info("ALU=>"+Opcode+"|"+GPR+"|"+IX+"|"+EA+"|Executing...");
        switch (Opcode){
            case 01:
                output = EA;
                break;
            case 02:
                output = EA;
                break;
            case 03:
                output = EA;
                break;
            case 41:
                output = EA;
                break;
            default:
                break;
        }
        logging.info("ALU=>"+output);
        return output;
    }
}
