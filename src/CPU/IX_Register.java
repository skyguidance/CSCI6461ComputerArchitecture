package CPU;

public class IX_Register {
	String IX;
	
	public IX_Register(String a) {
		IX=a;
	}
	public void set(String a) {
		if(a.length()==16) {
		 IX=a;
		}
		if(a.length()<16) {
			//TODO Efficiency
			IX="0".repeat(12-a.length())+a;
		}
		else {
			System.out.println("INVALID PC SET UP");
		}
	}
	public String get() {
		return IX;
	}
	public void increment() {
		int a=Integer.parseInt(IX);
		a++;
		set(Integer.toBinaryString(a));
	}
}
