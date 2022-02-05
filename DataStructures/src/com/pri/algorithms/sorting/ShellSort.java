/**
 * 
 */
package com.pri.algorithms.sorting;

import java.util.Arrays;

/**
 * @author prita
 *
 */
public class ShellSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr [] = {20,35,-15,7,55,1,-22};
		
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		
		for(int gap=arr.length/2;gap>0;gap=gap/2) {
			for(int unSortedPartioned=gap;unSortedPartioned<arr.length;unSortedPartioned++) {
				
				int newElement=arr[unSortedPartioned];
				int i;
				for(i=unSortedPartioned;i>=gap && arr[i-gap]>newElement;i=i-gap) {
					arr[i]=arr[i-gap];
				}
				
				arr[i]=newElement;
				
			}
		
		}
		
		/*
		 * for(int gap=arr.length/2;gap>0;gap=gap/2) {
		 * 
		 * for(int i=gap;i<arr.length;i++) { int newElement=arr[i];
		 * 
		 * for(int j=i;j>=gap && arr[j-gap]>newElement;j=j-gap) { arr[j]=arr[j-gap]; }
		 * arr[i]=newElement;
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 */
		System.out.println(Arrays.toString(arr));
		
		
		

	}

}
