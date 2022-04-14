/**
 * 
 */
package com.pri.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Pritam Sonavne
 *
 */
class Result1 {

    /*
     * Complete the 'miniMaxSum' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void miniMaxSum(List<Integer> arr) {
    
    	Collections.sort(arr);
    	
    	Integer min=0,max=0;
    	
		/*
		 * for(int i=0; i<arr.size()-1;i++) { min= min+ arr.get(i); } for(int i=1;
		 * i<arr.size();i++) { max= max+ arr.get(i); }
		 */
    	Integer sum=0;
    	List<Integer> sumSet=new ArrayList<Integer>();
    	for(int i=0;i<arr.size();i++) {
    		
    		for(int j=0;j<arr.size();j++) {
    			
    			if(i!=j) {
    				sum=sum+ arr.get(j);
    			}
    			
    		}
    		sumSet.add(sum);
			sum=0;
    	}
    	
    	System.out.println(sumSet.get(0) + " "+ sumSet.get(sumSet.size()-1));

    }

}

public class MinMaxfromArray {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> arr = Arrays.asList(1,2,3,4,5);

        Result1.miniMaxSum(arr);

        bufferedReader.close();
    }
}
