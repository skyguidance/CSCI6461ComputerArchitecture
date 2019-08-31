package CPU;

public class Memory_Buffer_Register {

	
String MBR;
	
	public Memory_Buffer_Register(String a) {
		MBR=a;
	}
	public void set(String a) {
		if(a.length()==16) {
		 MBR=a;
		}
		if(a.length()<16) {
			//TODO Efficiency
			MBR="0".repeat(12-a.length())+a;
		}
		else {
			System.out.println("INVALID PC SET UP");
		}
	}
	public String get() {
		return MBR;
	}
	public void increment() {
		int a=Integer.parseInt(MBR);
		a++;
		set(Integer.toBinaryString(a));
	}
}
