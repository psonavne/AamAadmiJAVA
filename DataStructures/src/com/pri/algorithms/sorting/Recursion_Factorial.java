/**
 * 
 */
package com.pri.algorithms.sorting;

/**
 * @author prita
 *
 */
public class Recursion_Factorial {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(iterativeFactorial(4));
		System.out.println(recursiveFact(4));
	}

	
	public static int iterativeFactorial(int num) {
		
		if(num==0) {
			return 1;
		}
		int factorial=1;
		
		for(int i=1;i<=num;i++) {
			factorial=factorial*i;
		}
		
		
		return factorial;
	}
	
	
	public static int recursiveFact(int num) {
		
		
		if(num==0) {
			return 1;
		}
		int factorial=1;
		
		factorial=num*recursiveFact(num-1);
		
		return factorial;
	}
	
}
