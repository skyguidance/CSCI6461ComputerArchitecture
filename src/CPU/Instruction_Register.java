package CPU;

import java.util.logging.Logger;

public class Instruction_Register extends Register{

    final Logger logging = Logger.getLogger("CPU.Instruction_Register");

    public Instruction_Register(int value) {
      super(value,16,"IR");
    }


}
