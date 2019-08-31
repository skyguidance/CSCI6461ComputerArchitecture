package CPU;

public class General_Purpose_Registers {
String GPR;
	
	public General_Purpose_Registers(String a) {
		GPR=a;
	}
	public void set(String a) {
		if(a.length()==16) {
		GPR=a;
		}
		if(a.length()<16) {
			//TODO Efficiency
			GPR="0".repeat(12-a.length())+a;
		}
		else {
			System.out.println("INVALID PC SET UP");
		}
	}
	public String get() {
		return GPR;
	}
	public void increment() {
		int a=Integer.parseInt(GPR);
		a++;
		set(Integer.toBinaryString(a));
	}
}
