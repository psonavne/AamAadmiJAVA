/**
 * 
 */
package com.pri.algorithms.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author prita
 *
 */
public class BucketSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int [] arr= {56,12,32,6,45,5,43};
		
		bucketSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void bucketSort(int [] arr) {
		
		List<Integer> [] buckets=new List [10];
		
		for(int i=0;i<buckets.length;i++) {
			buckets[i]=new LinkedList<Integer>();
		}
		
		for(int i=0;i<arr.length;i++) {
			buckets[getHash(arr[i])].add(arr[i]);
		}
		
		for(int i=0;i<buckets.length;i++) {
			Collections.sort(buckets[i]);
		}
		int j=0;
		for(int i=0;i<buckets.length;i++) {
			for(int k=0;k<buckets[i].size();k++) {
				arr[j++]=buckets[i].get(k);
			}
		}
	}
	
	private static int getHash(int value) {
		return value/10;
	}
}
