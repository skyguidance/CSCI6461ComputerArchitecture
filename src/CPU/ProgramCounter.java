package CPU;
public class ProgramCounter {

	String pc;
	
	public ProgramCounter(String a) {
		pc=a;
	}
	public void set(String a) {
		if(a.length()==12) {
		 pc=a;
		}
		if(a.length()<12) {
			//TODO Efficiency
			pc="0".repeat(12-a.length())+a;
		}
		else {
			System.out.println("INVALID PC SET UP");
		}
	}
	public String get() {
		return pc;
	}
	public void increment() {
		int a=Integer.parseInt(pc);
		a++;
		set(Integer.toBinaryString(a));
	}
}
