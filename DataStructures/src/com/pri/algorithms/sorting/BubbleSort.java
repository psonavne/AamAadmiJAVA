package com.pri.algorithms.sorting;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] numbers= {5,2,9,1,4};
		System.out.println(bubbleSort(numbers));
		
		for(int i=0;i<numbers.length;i++)
		{
			System.out.println(numbers[i]);
		}
		System.out.println(Arrays.toString(numbers));
	}

	static int [] bubbleSort(int [] numbers) {
		int iter=0;
		for(int j=0;j<numbers.length;j++)
		for (int i=0;i<numbers.length-j-1;i++) {
			if(numbers[i]>numbers[i+1]) {
				int temp= numbers[i];
				numbers[i]=numbers[i+1];
				numbers[i+1]=temp;
				
			}
			iter++;
		}
		
		System.out.println(iter);
		return numbers;
	}
}

//{5,2,9,1,4}
//temp=5
//{2,5,9,1,4}
//{2,5,1,9,4}
//{2,5,1,4,9}
//{2,1,5,4,9}
//{2,1,5,4,9}
//{1,2,4,5,9}