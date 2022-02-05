package com.pri.challenge;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Median {

    /*
     * Complete the runningMedian function below.
     */
    static double runningMedian(int[] a, int length) {
        /*
         * Write your code here.
         */
    	double result = 0;
    	if(length==1) {
    		return a[0];
    	} else if(length==2) {
    		//return new Double((a[0] + a[1])/2).doubleValue();
    		result=(a[0] + a[1])/2.0;
    	} else {
		    	for(int unpartionedIndex=1;unpartionedIndex<length;unpartionedIndex++)
		    	{
		    		int newElement=a[unpartionedIndex];
		    		int i;
		    		for(i=unpartionedIndex;i>0 && newElement<a[i-1];i--) {
		    			a[i]=a[i-1];
		    		}
		    		a[i]=newElement;
		    	}
		    	
		    	
		    	if(length%2!=0) {
		    		return a[length/2];
		    	}else {
		    		if(length>=4) {
		    			//return new Double((a[length/2] + a[(length-1)/2])/2).doubleValue();
		    			result=(a[length/2] + a[(length-1)/2])/2.0;
		    		}
		    	}
    	}
    	return result;
    }
// 5/2 0 1 2 3 4 5 6 
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int aCount = Integer.parseInt(scanner.nextLine().trim());

        int[] a = new int[aCount];
        double[] result =new double[aCount];
        for (int aItr = 0; aItr < aCount; aItr++) {
            int aItem = Integer.parseInt(scanner.nextLine().trim());
            a[aItr] = aItem;
            result[aItr]=runningMedian(a,aItr+1);
        }

        //double[] result = runningMedian(a);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            //bufferedWriter.write(String.valueOf(result[resultItr]));
        	System.out.println(result[resultItr]);
            
        }
    }
}
