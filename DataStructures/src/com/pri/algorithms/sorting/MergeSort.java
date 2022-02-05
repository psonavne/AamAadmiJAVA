/**
 * 
 */
package com.pri.algorithms.sorting;

import java.util.Arrays;

/**
 * @author prita
 *
 */
public class MergeSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr [] = {20,35,-15,7,55,1,-22};
		
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		int mid=(0+arr.length)/2;
		mergeSort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));

	}

	
	public static void mergeSort(int [] arr,int start, int end) {
		
		if(end-start<2) {
			return;
		}
		
		int mid=(start+end)/2;
		mergeSort(arr, start, mid);
		mergeSort(arr, mid, end);
		merge(arr,start,mid,end);
		
		
		
	}
	
	public static void merge(int arr[],int start,int mid, int end) {
		
		int tempArr []=new int [end-start];
		
		int tempIndex=0;
		int i=start;
		int j=mid;
		while(j<end && i<mid) {
			if(arr[i] <=arr[j]) {
			tempArr[tempIndex]=arr[i];
			tempIndex++;
			i++;
			}else {
				tempArr[tempIndex]=arr[j];
				j++;
				tempIndex++;
			}
		}
		
		/*
		 * if(i<mid) { tempArr[tempIndex]=arr[i]; }
		 */
		// i=1 j=2
		System.arraycopy(arr, i, arr, start+tempIndex, mid-i);
		System.arraycopy(tempArr, 0, arr, start, tempIndex);
		
	}
}
