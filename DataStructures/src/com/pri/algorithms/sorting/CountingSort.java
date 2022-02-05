/**
 * 
 */
package com.pri.algorithms.sorting;

import java.util.Arrays;

/**
 * @author prita
 *
 */
public class CountingSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int [] arr= {2,12,15,22,2,5,4,6,2,8,7,7,9,2,10,19,9};
		
		// Find the max element
		// create array of length of max element
		// store the count of each element at key element position
		// calculate the actual position of each key element
		// store the element in temp array
		
		int max=arr[0];
		for(int i=1;i<arr.length;i++) {
			if (arr[i]>max) {
				max=arr[i];
			}
		}
		//System.out.println(max);
		int [] count = new int[max+1];
		
		for(int j=0;j<arr.length;j++) {
			count[arr[j]]++;//count[arr[j]];
		}
		System.out.println(Arrays.toString(count));
		for(int k=1;k<count.length;k++) {
			count[k]=count[k]+ count[k-1];
		}
		
		System.out.println(Arrays.toString(count));
		
		int [] tempArray=new int[count[count.length-1]];
		//arr {2,1,1,0,2,5,4,0,2,8,7,7,9,2,0,1,9};
		//count [3, 6, 10, 10, 11, 12, 12, 14, 15, 17]
		for(int l=arr.length-1;l>=0;l--) {
			
			count[arr[l]]=count[arr[l]]-1;
			tempArray[count[arr[l]]]=arr[l];
			
			//System.out.println(Arrays.toString(tempArray));
		}
		
		

		System.out.println(Arrays.toString(tempArray));
		
		System.arraycopy(tempArray, 0, arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
		
	}

}
