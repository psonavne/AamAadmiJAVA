package academy.learnprogramming.stackschallenge;

import java.util.ArrayList;
import java.util.Iterator;
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
        
    	char[] strs=string.toCharArray();
    	
    	LinkedList<Character> linkedList= new LinkedList<Character>();
    	int top=strs.length;
    	for(int i=top-1;i>=0;i--) {
    		if(String.valueOf(strs[i]).matches("a-zA-Z"))
    		linkedList.add(strs[i]);
    	}
    	
    	Iterator<Character> iter=linkedList.iterator();
    	StringBuilder str=new StringBuilder();
    	while(iter.hasNext()) {
    		str.append(iter.next());
    	}
    	
    	System.out.println(string +"::" + str.toString());
    	
    	if(string.equalsIgnoreCase(str.toString()))
    		return true;
    	
    	return false;
    }
}
