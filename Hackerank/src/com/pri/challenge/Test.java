package com.pri.challenge;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []a= {12,4,5,3,8,7};
		for(int unpartionedIndex=1;unpartionedIndex<a.length;unpartionedIndex++)
    	{
    		int newElement=a[unpartionedIndex];
    		int i;
    		for(i=unpartionedIndex;i>0 && newElement<a[i-1];i--) {
    			a[i]=a[i-1];
    		}
    		a[i]=newElement;
    	}
		
		System.out.println(Arrays.toString(a));
	}
	// 4 12 5 3 8 7
	/*
	 * newEle=5
	 * if(5<12)
	 * a[1]=a[0]=12
	 * 
	 * */
}
