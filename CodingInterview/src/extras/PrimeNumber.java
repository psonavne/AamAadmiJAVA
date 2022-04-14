/**
 * 
 */
package extras;

/**
 * @author Pritam Sonavne
 *
 */
public class PrimeNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Prime ? "+ checkUsingRecursion(23));
		System.out.println("Prime ? "+ checkUsingWhile(23));
	}

	
	static boolean checkUsingRecursion(long n) {
		
		if(n%2==0) {
			return false;
		}
		else {
			checkUsingRecursion(n-1);
		}
		
		return true;
	}
	
	static boolean checkUsingWhile(long n) {
		
		if(n==2) {
			return true;
		}
		for(int i=2;i<n;i++) {
			if(n%i==0) {
				return false;
			}
		}
		
		return true;
	}
}
