/**
 * 
 */
package com.pri.algorithms.sorting;

import java.util.Arrays;

/**
 * @author prita
 *
 */
public class InsertionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int arr [] = {20,35,-15,7,55,1,-22};
		
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		
		for(int unpartionedIndex=1;unpartionedIndex<arr.length;unpartionedIndex++) {
			
			int newElement=arr[unpartionedIndex];
			
			int i;
			//boolean found=true;
			for(i=unpartionedIndex;i>0 && arr[i-1]>newElement;i--) {
				
					arr[i]=arr[i-1];
				
			}
			
			arr[i]=newElement;
		}
		
		
		System.out.println(Arrays.toString(arr));
		
	}

}
