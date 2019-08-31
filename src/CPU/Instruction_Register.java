package CPU;

public class Instruction_Register {

String IR;
	
	public Instruction_Register(String a) {
		IR=a;
	}
	public void set(String a) {
		if(a.length()==16) {
		 IR=a;
		}
		if(a.length()<16) {
			//TODO Efficiency
			IR="0".repeat(12-a.length())+a;
		}
		else {
			System.out.println("INVALID PC SET UP");
		}
	}
	public String get() {
		return IR;
	}
	public void increment() {
		int a=Integer.parseInt(IR);
		a++;
		set(Integer.toBinaryString(a));
	}
}
