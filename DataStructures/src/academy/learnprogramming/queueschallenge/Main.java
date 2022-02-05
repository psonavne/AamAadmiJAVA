package academy.learnprogramming.queueschallenge;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        // should return true
        System.out.println(checkForPalindrome("abccba"));
        // should return true
        System.out.println(checkForPalindrome("Was it a car or a cat I saw?"));
        // should return true
        System.out.println(checkForPalindrome("I did, did I?"));
        // should return false
        System.out.println(checkForPalindrome("hello"));
        // should return true
        System.out.println(checkForPalindrome("Don't nod"));
    }

    public static boolean checkForPalindrome(String string) {

    	LinkedList<Character> charlist=new LinkedList<Character>();

    	ArrayDeque<Character> arrList=new ArrayDeque<Character>();
    	
    	char [] charArr=string.toLowerCase().toCharArray();
    	for(int i=0;i<charArr.length;i++) {
    		if(charArr[i] >= 'a' && charArr[i] <= 'z') {
    		charlist.push(charArr[i]);
    		arrList.add(charArr[i]);
    		}
    	}
		/*
		 * System.out.println("Stack" + charlist.toString()); System.out.println("queue"
		 * + arrList.toString());
		 */
    	StringBuilder stackString =new StringBuilder();
    	StringBuilder queueString =new StringBuilder();
    	
    	while(!charlist.isEmpty() && !arrList.isEmpty()) {
    		if(!charlist.pop().equals(arrList.remove()))
    			return false;
    	}
    	
    	return true;
		/*
		 * while(!charlist.isEmpty()) { stackString.append(charlist.pop()); }
		 * while(!arrList.isEmpty()) { queueString.append(arrList.removeFirst()); }
		 */
		/*
		 * System.out.println(stackString +":"+ queueString);
		 * //System.out.println("Queue" + queueString);
		 * if(stackString.toString().equals(queueString.toString())) return true; else
		 * return false;
		 */
    	
    	
    	
    	
        
    }
}
