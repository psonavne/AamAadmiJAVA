package com.coding.interview.sort;

import java.util.Arrays;

public class Sorting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr1= {10,8,12,5,1,2};
		//bubbleSort(arr1);
		int [] arr2= {10,8,12,5,1,2};
		//selectionSort(arr2);
		//{8,10,12,5,1,2}
		insertionSort(arr1);
	}
	
	public static void bubbleSort(int [] arr) {
		
		for(int i=arr.length-1;i>=0;i--)
		for(int j=0;j<i;j++) {
			if(arr[j]>arr[j+1]) {
				int temp=arr[j];
				arr[j]=arr[j+1];
				arr[j+1]=temp;
			}
		}
			
		System.out.println(Arrays.toString(arr));
		
	}
	
	public static void selectionSort(int [] arr) {
		//{10,8,12,5,1,2};
		//{1,8,12,5,10,2};
		//{1,2,12,5,10,8};
		//{1,2,5,12,10,8};
		for(int i=0;i<arr.length-2;i++) {
			int minindex=i;
			for(int j=i+1;j<arr.length;j++) {
				if(arr[j]<arr[minindex]) {
					minindex=j;
				}
			}
			/*
			 * int temp=arr[i]; arr[i]=arr[minindex]; arr[minindex]=temp;
			 */
			int temp=arr[minindex];
			arr[minindex]=arr[i];
			arr[i]=temp;
			System.out.println(Arrays.toString(arr));
		}
			
		//System.out.println(Arrays.toString(arr));
	}
	
	public static void insertionSort(int arr[]) {
		
		//{10,8,12,5,1,2};
		for(int i=0;i<arr.length;i++) {
			int current=arr[i];
			int j=i-1;
			while(j>=0 && arr[j]>current) {
				arr[j+1]=arr[j];
				j=j-1;
			}
			arr[j+1]=current;
		}
		System.out.println(Arrays.toString(arr));
	}

}
