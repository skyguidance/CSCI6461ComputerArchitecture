package CPU;
import java.util.logging.Logger;

public class Condition_Code {
 
	boolean cc;
	final Logger logging = Logger.getLogger("CPU.Condition_Code");

	public Condition_Code(boolean value) {
		cc=value;
	}

	public void set(boolean value) {
		this.cc = value;
		logging.info("CC=>" + value);
		return;
	}

	public boolean get() {
		return this.cc;
	}

}
