package pri.euler.challenge;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		problem1();
	}

	public static void problem1() {
		int number=0;
		
		for(int i=1;i<1000;i++) {
			if(i%3 ==0 || i%5==0) {
				number=number+i;
			}
		}
		
		System.out.println("Problem #1: " + number);
	}
}
