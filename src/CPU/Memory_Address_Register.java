package CPU;

public class Memory_Address_Register {

	
String MAR;
	
	public Memory_Address_Register(String a) {
		MAR=a;
	}
	public void set(String a) {
		if(a.length()==16) {
		 MAR=a;
		}
		if(a.length()<16) {
			//TODO Efficiency
			MAR="0".repeat(12-a.length())+a;
		}
		else {
			System.out.println("INVALID PC SET UP");
		}
	}
	public String get() {
		return MAR;
	}
	public void increment() {
		int a=Integer.parseInt(MAR);
		a++;
		set(Integer.toBinaryString(a));
	}
}
