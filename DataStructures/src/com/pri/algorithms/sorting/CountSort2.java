package com.pri.algorithms.sorting;

import java.util.Arrays;

public class CountSort2 {

	public static void main(String[] args) {
		int [] arr = {4,5,2,1,6,4,3,1,2};
		
		countSort(arr, 1, 6);
	}
	// arr = {4,5,2,1,6,4,3,1,2}
	public static void countSort(int[] arr, int min, int max) {
		
		int [] count=new int[max+1];
		
		for(int i=0;i<arr.length;i++) {
			count[arr[i]]=count[arr[i]]+1;
		}
		
		System.out.println("Count Array" + Arrays.toString(count));
		
		for(int i=1;i<count.length;i++) {
			count[i]=count[i]+ count[i-1];
		}
		
		System.out.println("Count Array" + Arrays.toString(count));
		int [] tempArray=new int [count[count.length-1]];
		for(int i=arr.length-1;i>=0;i--) {
			count[arr[i]]--;
			tempArray[count[arr[i]]]=arr[i];
		}
		System.out.println("Unsorted Array" + Arrays.toString(arr));
		System.out.println("temp Array"+ Arrays.toString(tempArray));
	}
	
}
