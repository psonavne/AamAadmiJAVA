package com.pri.algorithms.sorting;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		
		int arr[] = {64, 25, 12, 22, 11};
		System.out.println(Arrays.toString(selectionSort(arr)));
	}

	static int [] selectionSort(int[] arr) {
		
		for(int i=0;i<arr.length-1;i++) {
			int min_index = i;
			for(int j=i+1;j<arr.length;j++) {
				if(arr[j]<arr[min_index]) {
					min_index=j;
				}	
			}
			int temp=arr[i];
			arr[i]=arr[min_index];
			arr[min_index]=temp;
		}
		
		return arr;
	}
	
	/*
	 * // 64 25 12 22 11 0 1 2 3 4 i=0 j=1 min=0 25<64 temp=arr[j] 25
	 * arr[j]=arr[min] 64 arr[min]= temp; 25
	 */
}
// 64, 25, 12, 22, 11
// 
//
