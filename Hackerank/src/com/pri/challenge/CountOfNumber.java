/**
 * 
 */
package com.pri.challenge;

/**
 * @author Pritam Sonavne
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class Result {

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void plusMinus(List<Integer> arr) {
    // Write your code here
        if(!arr.isEmpty()){
            
            int pos = 0,neg = 0,zero=0;
            for (Integer i : arr){
				if(i<0) {
            		neg++;
            	}else if (i>0) {
            		pos++;
            	}else if(i==0){
            		zero++;
            	}
            }
            int size=arr.size();
            
            System.out.println(BigDecimal.valueOf(Double.valueOf(pos)/size).setScale(6,BigDecimal.ROUND_HALF_UP));
            System.out.println(BigDecimal.valueOf(Double.valueOf(neg)/size).setScale(6,BigDecimal.ROUND_HALF_UP));
            System.out.println(BigDecimal.valueOf(Double.valueOf(zero)/size).setScale(6,BigDecimal.ROUND_HALF_UP));
            
        }
    }

}

public class CountOfNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> arr =Arrays.asList(-4, 3, -9, 0, 4, 1);
        Result.plusMinus(arr);

        bufferedReader.close();
    }
}

