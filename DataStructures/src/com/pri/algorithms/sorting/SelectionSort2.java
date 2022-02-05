/**
 * 
 */
package com.pri.algorithms.sorting;

import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.SWAP;

/**
 * @author prita
 *
 */
public class SelectionSort2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int arr [] = {20,35,-15,7,55,1,-22};
		
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		
		for(int j=arr.length-1;j>0;j--) {
			int largest=0;
			for(int i=1;i<=j;i++) {
				
				if(arr[i]>arr[largest]) {
					largest=i;
				}
			}
			
			  int temp=arr[j]; arr[j]=arr[largest]; arr[largest]=temp;
			 
			//swap(arr, largest, j);
			
		}
		System.out.println(Arrays.toString(arr));
	}
	
	public static void swap (int [] arr,int i,int j) {
		
		int temp =arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
		
	}
}
