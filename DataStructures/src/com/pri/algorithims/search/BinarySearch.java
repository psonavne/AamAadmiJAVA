/**
 * 
 */
package com.pri.algorithims.search;

import java.util.Arrays;

/**
 * @author prita
 *
 */
public class BinarySearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int arr [] = {20,35,-15,7,15,1,-22};
		
		insertionSort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(iterativeBinary(arr,150));
		System.out.println(binarySearch(arr, 0, arr.length, 150));
		
	}

	private static int iterativeBinary(int [] arr, int search) {
		
		int start=0;
		int end=arr.length;
		
		while (start<end) {
			int mid=(start+end)/2;
			if(arr[mid]==search) {
				return mid;
				
			}else if (arr[mid]>search) {
				end=mid;
			}else {
				start=mid+1;
			}
			
			
			
		}
		
		
		return -1;
	}
	
	private static int binarySearch(int[] arr, int start, int end, int search) {
		int found=-1;
		if(end-start<1) {
			return -1;
		}
		int mid=(start+end)/2;
		if(arr[mid]==search) {
			return mid;
		}
		if(search<arr[mid])
			return binarySearch(arr,start,mid,search);
		else
			return binarySearch(arr,mid+1,end,search);
		//checkValue(arr,start,mid,end,search);
		
	}

	private static void checkValue(int [] arr,int start,int mid,int end,int search) {
		
		
		
	}
	private static void insertionSort(int[] arr) {
		
		for(int unpartionedIndex=1; unpartionedIndex<arr.length;unpartionedIndex++) {
			
			int newElement=arr[unpartionedIndex];
			int i;
			for( i=unpartionedIndex;i>0 && newElement<arr[i-1];i--) {
					arr[i]=arr[i-1];
			
			}
			arr[i]=newElement;
		}
		
		
		
	}

	
}
