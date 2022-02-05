/**
 * 
 */
package com.pri.challenge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author prita
 *
 */
public class SetOfBrackets {

	 // Complete the isBalanced function below.
    static String isBalanced(String s) {
    	if(s==null) {
    		return "NO";
    	}
    	char[] ch=s.toCharArray();
    	
    	if(ch.length%2==0) {
	    	int counter=ch.length/2;
	    	int i=0;
	    	int j=ch.length-1;
	    		while(i<ch.length/2 && j>=ch.length/2){
	    			
	    			if(ch[i]=='{' && ch[j]=='}')
	    			counter--;
	    			else if(ch[i]=='[' && ch[j]==']')
	    				counter--;
	    			else if(ch[i]=='(' && ch[j]==')')
	    				counter--;
	    			else
	    				break;
	    			
	    			i++;j--;
	    			
	    		}
	    	
	    	return counter==0?"YES": "NO";
    	}
    	else {
    		return "NO";
    	}
		
    	
    }
    static String isBalanced2(String s) {
    	if(s==null) {
    		return "NO";
    	}
    	while(s.length()!=0)
    	{
    		if(s.contains("{}") || s.contains("[]") || s.contains("()")) 
    		{s=s.replace("{}", "");
    		s=s.replace("[]", "");
    		s=s.replace("()", "");
    		}else {
    			break;
    		}
    	}
    	if(s.isEmpty()) {
    		return "YES";
    	}else return "NO";
    }
    
    static String isBalanced3(String s) {
    	if(s==null) {
    		return "NO";
    	}
    	
    	LinkedList<Character> chList=new LinkedList<Character>();
    	char [] ch=s.toCharArray();
    	Map<Character, Character> map=new HashMap<Character, Character>();
    	map.put('}', '{');
    	map.put(']', '[');
    	map.put(')', '(');
    	for(int i=0;i<ch.length;i++) {
    		
    		if(ch[i]=='{') {
    			chList.push(ch[i]);
    		}else if(ch[i]=='[') {
    			chList.push(ch[i]);
    		}else if(ch[i]=='(') {
    			chList.push(ch[i]);
    		}else if(!chList.isEmpty()) {
    			if(map.get(ch[i])==chList.peek())
    				chList.pop();
    			else
    				return "NO";
    		}
    		else if(chList.isEmpty()){
    			return "NO";
    		}
    		
    		
    	}
    	if(chList.isEmpty()) {
    		return "YES";
    	}else return "NO";
    	
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      
    	//System.out.println(isBalanced3("{[()]}"));
    	//System.out.println(isBalanced3("{[(])}"));
    	//System.out.println(isBalanced2("{{[[(())]]}}"));
    	System.out.println(isBalanced3("{(([])[])[]]}"));
    	//System.out.println(isBalanced3("{(([])[])[]}"));
    	
    	// {[(])}
    	// {]}
    	
    	// 			

     }
}
