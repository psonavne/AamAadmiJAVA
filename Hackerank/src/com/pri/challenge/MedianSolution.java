package com.pri.challenge;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class MedianSolution {

    /*
     * Complete the runningMedian function below.
     */
        static double runningMedian(int[] a, int length) {
        /*
         * Write your code here.
         */
         double result=0;
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
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        PriorityQueue<Integer> min = new PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Integer> max = new PriorityQueue<Integer>();
        int a = in.nextInt();
        System.out.println(String.format("%.1f", (float)a));
        if (n == 1) return;
        int b = in.nextInt();


        min.add(Math.min(a, b));
        max.add(Math.max(a, b));
        System.out.println(String.format("%.1f", ((float)(max.peek() + min.peek())) / 2));

        for (int i = 2; i < n; i++) {
            int v = in.nextInt();
            if (v >= max.peek())
                max.add(v);
            else
                min.add(v);
            if (Math.abs(max.size() - min.size()) > 1) {
                if (min.size() > max.size()) {
                    max.add(min.poll());
                } else {
                    min.add(max.poll());
                }
            }

            if (max.size() == min.size())
                System.out.println(String.format("%.1f", ((float)(max.peek() + min.peek())) / 2));
            else if (max.size() > min.size())
                System.out.println(String.format("%.1f", (float)max.peek()));
            else
                System.out.println(String.format("%.1f", (float)min.peek()));
            }

        
    }
}
