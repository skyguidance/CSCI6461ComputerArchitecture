package CPU;

import java.util.logging.Logger;

/**
 * The super class of register.
 * All register are extended from this class
 */
public class Register {
    private int value, length;
    private String name;
    final Logger logging = Logger.getLogger("CPU.Register");

    public Register(int value, int length, String name) {
        this.length = length;
        this.name = name;
        setValue(value);
    }

    /**
     * Convert value to binary string
     *
     * @return The binary String
     */
    public String ToBinaryString() {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        String Stringlength = "" + length;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));//
    }

    /**
     * Get the Register value.
     *
     * @return The value of the register.
     */
    public int getValue() {
        return value;
    }

    /**
     * Do the security check (if overflow) and set the value to the register.
     * If the value is not legit, log an INVALID operation.
     *
     * @param Value the int value to be set to the register.
     */
    public void setValue(int Value) {
        if (Math.pow(2, length) > Value && Value >= 0) {
            this.value = Value;
            logging.info(name + "=>" + ToBinaryString() + "(" + value + ")");
            System.out.println(name + "=>" + ToBinaryString() + "(" + value + ")");
        } else {
            logging.info("INVALID " + name + "=>" + ToBinaryString() + "(" + Value + ")");
            System.out.println("INVALID " + name + "=>" + ToBinaryString() + "(" + Value + ")");
        }
    }

    /**
     * Do the security check (if overflow) and set the value to the register.
     * If the value is not legit, log an INVALID operation.
     *
     * @param Value the String value to be set to the register.
     */
    public void setValue(String Value) {
        int IntValue = Integer.valueOf(Value, 2);
        setValue(IntValue);
    }

}
