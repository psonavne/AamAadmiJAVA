/**
 * 
 */
package com.pri.algorithms.sorting;

import java.util.Arrays;

/**
 * @author prita
 *
 */
public class MergeSortDescending {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int [] arr= {22,7,35,-22,42,1,8,18,12,5};
		
		
		System.out.println(Arrays.toString(arr));
		mergeSort(arr,0,arr.length-1);
		
		System.out.println(Arrays.toString(arr));
	}

	public static void mergeSort(int[] arr, int start, int end) {
		
		if(end-start <1) {
			return;
		}
		int mid= (start+end)/2;
		
		mergeSort(arr, start, mid);
		mergeSort(arr, mid+1, end);
		merge(arr,start,mid,end);
		
	}

	private static void merge(int[] arr, int start, int mid, int end) {
		
		int i=start;
		int j=mid+1;
		int k=0;
		int [] temp=new int[end-start+1];
		while(i<=mid && j<=end) {
			
			if(arr[i]>arr[j]) {
				temp[k]=arr[i];
				i++;k++;
			}else {
				temp[k]=arr[j];
				j++;k++;
			}
			
		}
		if(i>mid) {
			
			while(j<=end) {
				temp[k]=arr[j];
				j++;k++;
			}
			
		}else {
			while(i<=mid) {
				temp[k]=arr[i];
				i++;k++;
			}
		}
		//System.arraycopy(temp, 0, arr, start, arr.length);
		
		  for(int l=end;l>=start;l--) 
		  { 
			  arr[l]=temp[--k]; 
		  }
		 
	}

}
