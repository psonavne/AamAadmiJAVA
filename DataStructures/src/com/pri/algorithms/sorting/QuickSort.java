/**
 * 
 */
package com.pri.algorithms.sorting;

import java.util.Arrays;

/**
 * @author prita
 *
 */
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = { 20, 35, -15, 7, 55, 1, -22 };

		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		quickSort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}

	public static void quickSort(int[] arr, int start, int end) {

		if (end - start < 2) {
			return;
		}

		int pivotIndex = partition(arr, start, end);

		quickSort(arr, start, pivotIndex);
		quickSort(arr, pivotIndex + 1, end);

	}

	public static void swap(int [] arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	public static int partition(int[] arr, int start, int end) {

		int i = start;
		int j = end-1;
		// {20,35,-15,7,55,1,-22}
		int pivot = arr[start];
		
		while(i<j) {
			
			while(arr[i]<=pivot && i<j) {
				i++;
			}
			while(arr[j]>pivot && i<j) {
				j--;
			}
			if(i<j) {
				swap(arr,i,j);
			}
			
		}
		//arr[j]=pivot;
		swap(arr, start, j);
		
		return j;
		
		
		
		
		
		
		
		
		
		
		/*
		 * while(i<j) {
		 * 
		 * 
		 * while(i<j && arr[--j]>=pivot) { arr[i]=arr[j]; } while(i<j &&
		 * arr[++i]<=pivot) { arr[j]=arr[i]; }
		 * 
		 * 
		 * if(arr[j-1]<=pivot && jflag==true ) { arr[i]=arr[j-1]; i++; iflag=true;
		 * jflag=false; } else { j--; } if(arr[i]>=pivot && iflag==true) {
		 * arr[j-1]=arr[i]; j--; jflag=true; iflag=false; }else { i++; }
		 * 
		 * } arr[i]=pivot; return i;
		 */

		/*
		 * i=0, j=6 if(arr[j] <pivot) arr[i]=arr[j] i++ {-22,35,-15,7,55,1,-22} i=1,j=6
		 * if(arr[i]>pivot) arr[j]=arr[i] j-- {-22,35,-15,7,55,1,35} i=1,j=5
		 * arr[j]<pivot 1<20 arr[i]=arr[j] i++ {-22,1,-15,7,55,1,35} i=4,j=5 55>20
		 * arr[j]=arr[i] {-22,1,-15,7,55,55,35} i++
		 * 
		 */

	}
}
