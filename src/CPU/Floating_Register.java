package CPU;

import java.nio.Buffer;
import java.util.logging.Logger;

public class Floating_Register {
    public String Value;

    final Logger logging = Logger.getLogger("CPU.FloatingPointRegister");

    public Floating_Register() {
        Value = "0000000000000000";
    }

    public Floating_Register(String Value) {
        if (this.Value.length() != 16) {
            logging.severe("Floating Point Parser Initialize Error.Length!=16");
            System.out.println("Floating Point Parser Initialize Error.Length!=16");
            return;
        }
        Value = this.Value;
    }

    public Floating_Register(int intValue) {
        String StringValue = ToBinaryString(intValue);
        if (StringValue.length() != 16) {
            logging.severe("Floating Point Parser Initialize Error.Length!=16");
            System.out.println("Floating Point Parser Initialize Error.Length!=16");
            return;
        }
        Value = StringValue;
    }

    public int getMantissaSignAsInt() {
        int sign = Character.getNumericValue(Value.charAt(0));
        if (sign == 1) {
            return -1;
        } else {
            return 1;
        }
    }

    public String getMantissaSignAsString() {
        return String.valueOf(Value.charAt(0));
    }


    public int getExponentAsInt() {
        return Integer.parseInt(Value.substring(1, 8), 2);
    }

    public String getExponentAsString() {
        return Value.substring(1, 8);
    }

    public int getMantissaAsInt() {
        return Integer.parseInt(Value.substring(8, 16), 2);
    }

    public String getMantissaAsString() {
        return Value.substring(8, 16);
    }

    public String ToBinaryString(int value) {
        int length = 16;
        String a = Integer.toBinaryString(value);// Change to BinaryString
        if (a.length() == 32 && a.substring(0, 1).equals("1")) {
            // It is a negative number!
            return a;
        }
        String Stringlength = "" + length;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));//
    }

    public void setValue(String Sign, String Exponent, String Mantissa) {
        if (Sign.length() != 1) {
            logging.severe("Floating Point Sign Bit Length Error.");
            System.out.println("Floating Point Sign Bit Length Error.");
            return;
        }
        if (Exponent.length() != 7) {
            logging.severe("Floating Point Exponent Bit Length Error.");
            System.out.println("Floating Point Exponent Bit Length Error.");
            return;
        }
        if (Mantissa.length() != 8) {
            logging.severe("Floating Point Mantissa Bit Length Error.");
            System.out.println("Floating Point Mantissa Bit Length Error.");
            return;
        }
        Value = Sign + Exponent + Mantissa;
        return;
    }

    public float toFloatingPoint() {
        double result = 0;
        String Mantissa = getMantissaAsString();
        int MantissaSign = getMantissaSignAsInt();
        int Expondent = getExponentAsInt();
        int bias = 63;
        double realExpondent = Math.pow(2.0, new Double(Expondent - bias));
        double half = 0.5;
        double MantissaResult = 1;
        for (int i = 0; i < Mantissa.length(); i++) {
            int bit = Integer.valueOf(Mantissa.substring(i,i+1));
            MantissaResult += half * bit;
            half = half / 2;
        }
        result = MantissaSign * realExpondent * MantissaResult;
        return (float) result;
    }

    public void setFloatingPoint(float value) {
        // Get Sign Bit.
        String SignBit;
        if (value >= 0) {
            SignBit = "0";
        } else {
            SignBit = "1";
        }
        // Get Expondent
        int exponet = findexponetnumber(value) + 63;
        if (exponet > (63 + 64)) {
            System.out.println("error for exponet exceed max");
            return;
        }
        String exponentBinary = ToBinaryString(exponet, 7);
        String Mantissa = calMantissa(value, exponet - 63);
        setValue(SignBit, exponentBinary, Mantissa);
    }

    private String calMantissa(float value, int exponetInt) {
        value = Math.abs(value);
        Double exponetDouble = Math.pow(2, exponetInt);
        double a = value / exponetDouble - 1;
        double half = 0.5;
        String result = "";
        while (result.length() != 8) {
            if (a > half) {
                result += "1";
                a -= half;
            } else {
                result += "0";
            }
            half = half / 2;
        }
        return result;
    }

    private String ToBinaryString(int value, int length) {
        String a = Integer.toBinaryString(value);// Change to BinaryString
        if (a.length() == 32 && a.substring(0, 1).equals("1")) {
            // It is a negative number!
            return a;
        }
        String Stringlength = "" + length;
        String format = "%0numberd".replace("number", Stringlength);
        return String.format(format, Long.valueOf(a));//
    }


    private int findexponetnumber(float value) {
        int testnumber = 1;
        int i = 0;
        while (value > testnumber) {
            testnumber = testnumber * 2;
            i = i + 1;
        }
        return i - 1;
    }

    public static void main(String[] args) {
        Floating_Register FL = new Floating_Register();
        FL.setFloatingPoint((float) 1.23456);
        System.out.println(FL.toFloatingPoint());
        System.out.println(FL.Value);
    }
}