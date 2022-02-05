/**
 * 
 */
package com.pri.challenge;

/**
 * @author prita
 *
 */


import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Contacts {
    /*
     * Complete the contacts function below.
     */
    static int[] contacts(String[][] queries) {
        /*
         * Write your code here.
         */
    	int [] tempResult = new int[queries.length];
    	int counter=0;
    	TreeSet<String> contactSet=new TreeSet<String>(); 
    	for(String [] rows : queries) {
    		//System.out.println(Arrays.toString(rows));
    		if(rows[0].equalsIgnoreCase("add")) {
    			contactSet.add(rows[1]);
    		}
    		else {
    			Set<String> tailSet=contactSet.tailSet(rows[1]);
    			Iterator<String> itr=tailSet.iterator();
        		int count=0;
        		while(itr.hasNext()) {
        			if(itr.next().startsWith(rows[1]))
        				count++;
        		}
    			
    			tempResult[counter++]=count++;
    		}
    	}
    	int [] result = new int[counter];
    	System.arraycopy(tempResult, 0, result, 0, counter);
    	return result;

    }
    private static int find(Set<String> contactSet, String value) {
    	int count = 0;
    	if(contactSet.isEmpty()) {
    		return count;
    	}else {
    		Iterator<String> itr=contactSet.iterator();
    		
    		while(itr.hasNext()) {
    			if(itr.next().startsWith(value))
    				count++;
    		}
    	}
    	return count;
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);
		
		  for (int resultItr = 0; resultItr < result.length; resultItr++) {
		  System.out.println(String.valueOf(result[resultItr])); }
		 

    }
}

