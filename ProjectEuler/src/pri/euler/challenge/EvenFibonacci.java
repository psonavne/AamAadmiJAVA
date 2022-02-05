/**
 * 
 */
package pri.euler.challenge;

/**
 * @author prita
 *
 */
public class EvenFibonacci {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(evenFibonacci(2, 8));
		/*
		 * int number=2; while(number<40) { number=number*(number+2); }
		 * System.out.println(number);
		 */
		
		long n = 400000;
	    long f1 = 0;
	    long f2 = 1;
	    long sum = 0;
	    while(f1 + f2 < n) {
	        if((f1 + f2) % 2 == 0) 
	        	sum += f1 + f2;
	        long temp = f2;
	        f2 = f1 + f2;
	        f1 = temp;
	    }
	    System.out.println(sum);
	}
	// 4
	// 
	
	public static int evenFibonacci(int a,int b) {
		int c=a+b;
		long number=0;
		System.out.print(a + "," + b+ "," );
		while(true) {
			c=4*a+b;
			if(c>=400000)
				return c;
			number=number+c;
			System.out.print(c+ ",");
			a=b;
			b=c;
		}
	}

}
