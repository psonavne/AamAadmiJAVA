package com.pri.algorithms.heap;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr[] = { 45, 25, 55, 15, 32, 54, 63 };

		
		  //Heap hp=new Heap(10);
		  
			/*
			 * hp.insert(45); hp.insert(25); hp.insert(55); hp.insert(15); hp.insert(32);
			 * hp.insert(54); hp.insert(63); hp.insert(52); hp.print(); hp.delete(1);
			 * hp.print();
			 */
		  //[80, 75, 60, 68, 55, 40, 52, 67, 0, 0]
		  /*			80
		   * 		75			60	
		   * 	68		55	40		52
		   * 67
		   * */
		  
		/*
		 * hp.insert(80); hp.insert(75); hp.insert(60); hp.insert(68); hp.insert(55);
		 * hp.insert(40); hp.insert(52); hp.insert(67); hp.print(); hp.delete(0);
		 * hp.print();
		 */
			
			  HeapSort hp=new HeapSort(10);
			  
				/*
				 * hp.insert(80); hp.insert(75); hp.insert(60); hp.insert(68); hp.insert(55);
				 * hp.insert(40); hp.insert(52); hp.insert(67);
				 */
			  
				
				  hp.insert(45); hp.insert(25); hp.insert(55);  hp.insert(32);
				  hp.insert(54); hp.insert(63); hp.insert(52);  hp.insert(15);
				 
			hp.print();
			hp.heapSort();
			System.out.println("");
			hp.print();
		
	}
	/*
	 * 63 52 54 55 15 25 32 45
	 * 
	 * 63 32 55 15 25 45 54
	 * 
	 * 63 32 55 15 25 45 54
	 */
}
