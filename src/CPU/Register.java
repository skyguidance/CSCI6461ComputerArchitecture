package CPU;
import java.util.logging.Logger;
public class Register {
	private int value,length;
	private  String name;
	final Logger logging=Logger.getLogger("CPU.Register");

	public Register(int value,int length,String name) {
		this.length=length;
		this.name=name;
		setValue(value);
	}
	public String ToBinaryString() {
		String a=Integer.toBinaryString(value);// Change to BinaryString
		String Stringlength=""+length;
		String format="%0numberd".replace("number", Stringlength);
		return String.format(format,Long.valueOf(a));//
	}

	public int getValue() {
		return value;
	}
	public void setValue(int Value) {
		if(Math.pow(2,length)>Value&&Value>=0) {
			this.value=Value;
			logging.info(name+"=>" + value + "(" + value + ")");
		}
		else {
			logging.info("INVALID "+name+"=>"+Value+"(" + Value+")" );
		}
	}
		
}
