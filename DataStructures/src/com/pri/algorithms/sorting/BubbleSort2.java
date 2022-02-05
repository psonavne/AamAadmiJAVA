/**
 * 
 */
package com.pri.algorithms.sorting;

import java.util.Arrays;

/**
 * @author prita
 *
 */
public class BubbleSort2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int arr [] = {20,35,-15,7,-15,1,-22};
		
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		
		
		for(int j=arr.length-1;j>0;j--)
			for(int k=0;k<j;k++) {
				if(arr[k]>arr[k+1]) {
					int temp=arr[k+1];
					arr[k+1]=arr[k];
					arr[k]=temp;

				}
			}
		
		System.out.println(Arrays.toString(arr));
		
	}

}
