package CPU;

import java.util.logging.Logger;

class ALU {
    private int output;
    private final Logger logging = Logger.getLogger("SimulatorLOG");

    ALU(){
        output = -1;
        Simulator sim=new Simulator();

    }

    int execute(int Opcode, int GPR, int IX, int EA){
        logging.info("ALU=>"+Opcode+"|"+GPR+"|"+IX+"|"+EA+"|Executing...");
        switch (Opcode){
            case 1:
                output = EA;
                break;
            case 2:
                output = EA;
                break;
            case 3:
                output = EA;
                break;
            case 41:
                output = EA;
                break;
            case 42:
                output = EA;
                break;
            default:
                break;
        }
        logging.info("ALU=>"+output);
        return output;
    }
}
