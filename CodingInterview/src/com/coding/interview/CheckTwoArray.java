/**
 * 
 */
package com.coding.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author prita
 *
 */
public class CheckTwoArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String [] arr1= {"a","b", "c", "d"};
		String [] arr2= {"x", "y", "z","a"};
		/*
		 * int arr3Length = arr1.length+ arr2.length; String []arr3=new
		 * String[arr3Length]; System.arraycopy(arr1, 0, arr3, 0, arr1.length);
		 * System.arraycopy(arr2, 0, arr3, arr1.length,arr2.length);
		 * System.out.println("Arrays" + Arrays.toString(arr3)); //Arrays[a, b, c, d, x,
		 * y, z, a] String arr="abcdxyza"; Map<String,Integer> arrFound= new
		 * HashMap<String, Integer>(); boolean found=false; for (int
		 * i=0;i<arr3.length;i++) { if(arrFound.containsKey(arr3[i])) { found=true;
		 * break; } arrFound.put(arr3[i],1); } System.out.println("Identical" + found);
		 */
		
		System.out.println(matchArray(arr1, arr2));
	}

	static boolean matchArray(String [] arr1, String [] arr2) {
		Map<String,Boolean> arr1Map=new HashMap<String, Boolean>();
		for (int i=0;i<arr1.length;i++) {
			arr1Map.put(arr1[i], true);
		}
		
		for(int j=0;j<arr2.length;j++) {
			if(arr1Map.containsKey(arr2[j])) {
				return true;
			}
		}
		return false;
	}
}
