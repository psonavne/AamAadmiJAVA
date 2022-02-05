package com.pri.algorithms.sorting;

import java.util.Arrays;

public class RadixSort {

	public static void main(String[] args) {
		int [] arr = {432,8,530,90,88,231,11,45,677,199};
		
		radixSort(arr,10);
	}
	public static int calculateMaxNumber(int [] arr) {
		int max=arr[0];
		for(int i=1;i<arr.length;i++) {
			if(arr[i] > max) {
				max=arr[i];
			}
		}
		
		return max;
	}
	public static void radixSort(int [] arr,int n) {
		
		int max=calculateMaxNumber(arr);
		
		for(int pos=1;max/pos>0;pos=pos*10) {
		int [] count=new int [10];
		
		for(int i=0;i<arr.length;i++) {
			count[(arr[i]/pos)%10]++;
		}
		
		System.out.println("count"+ Arrays.toString(count));
		for(int i=1;i<arr.length;i++) {
			count[i]=count[i] + count[i-1];
		}
		System.out.println("after add count"+ Arrays.toString(count));
		int[] tempArray=new int [count[count.length-1]];
		for(int i=arr.length-1;i>=0;i--) {
			count[(arr[i]/pos)%10]--;
			tempArray[count[(arr[i]/pos)%10]]=arr[i];
		}
		
		System.out.println("Temp" + Arrays.toString(tempArray));
		System.arraycopy(tempArray, 0, arr, 0, arr.length);
		System.out.println("Arr" + Arrays.toString(tempArray));
		}
		
		/*count=new int [max+1];
		//mod=%100/10;
		for(int i=0;i<arr.length;i++) {
			count[arr[i]%100/10]++;
		}
		
		System.out.println("count"+ Arrays.toString(count));
		for(int i=1;i<arr.length;i++) {
			count[i]=count[i] + count[i-1];
		}
		System.out.println("after add count"+ Arrays.toString(count));
		tempArray=new int [count[count.length-1]];
		for(int i=arr.length-1;i>=0;i--) {
			count[arr[i]%100/10]--;
			tempArray[count[arr[i]%100/10]]=arr[i];
		}
		
		System.out.println("Temp" + Arrays.toString(tempArray));
*/	}
	
}
