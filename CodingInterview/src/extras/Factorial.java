/**
 * 
 */
package extras;

/**
 * @author Pritam Sonavne
 *
 */
public class Factorial {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long n=10;
		if(n==0) {
			System.out.println("Result::" + n);
		}
		else if(n==1 || n==2) {
			System.out.println("Result: "+ n);
		}
		else if(n>2) {
			System.out.println("Result:" + factorialUsingRecursion(n));
			System.out.println("Result:" + factorialUsingWhile(n));
		}
	}
	
	static long factorialUsingRecursion(long n) {
		long result=1;
		if(n>=2) {
			result= result*n*factorialUsingRecursion(n-1);
		}
		
		return result;
	}

	static long factorialUsingWhile(long n) {
		long result=1;
		while(n>=2) {
			result=result*n;
			n--;
		}
		return result;
	}
}
