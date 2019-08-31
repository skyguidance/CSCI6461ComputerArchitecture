package CPU;
import java.util.HashMap;

public class Memory {
 HashMap<String,String> memory;
	
	
	public Memory() {
		memory=new HashMap<String,String>();
	}
	public void add(String address,String value) {
		int addressInt=Integer.parseInt(address,2);
		if(addressInt<4096) {
		memory.put(address,value);}
		else {
			System.out.println("out of memory");
		}
	}
	public String get(String address) {
		if(memory.containsKey(address)) {
			return memory.get(address);
		}
		else {
		System.out.println("no exist value in such address");
		return "";
		}
	}
	
}
