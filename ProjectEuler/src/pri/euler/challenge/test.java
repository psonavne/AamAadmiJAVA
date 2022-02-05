package pri.euler.challenge;

import java.util.ArrayList;
import java.util.List;

public class test {

	public static void main(String [] args) {
		
		
		List<String> edges =new ArrayList<String>() ;
		
		edges.add("1 2");
		edges.add("1 3");
		
		for(String egde: edges) {
			String [] egdArr=egde.split("\\s");
			System.out.println(egdArr[0] + ":" + egdArr[1]);
		}
		
	}
	
}
