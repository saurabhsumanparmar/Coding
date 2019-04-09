package dp;

import java.util.Stack;

// https://practice.geeksforgeeks.org/problems/similar-expressions/0
public class SimilarExpression {
	public static void main(String [] args) {
		/*String s1 = "-(a+b+c)";
		String s2 = "-a-b-c";*/
		String s1 = "a-b-(c-d)";
		String s2 = "a-b-c-d";
		System.out.println(isSimilar(s1, s2));
	}
	
	// assumption : in  string s1 and s2 only lower alphabets and +, - , ( , ) character will there
	// Mistakes : in if conditions  .. like .. i > 0 instead of i>= 0, j == s1.length instead of j > s1.length
	// logical error : block missing to revert the sign of count in map
	public static boolean isSimilar(String s1, String s2) {
		int[] map = new int[26];
		Stack<String> signStack = new Stack<String>();
		
		int j=0;
		while(j < s1.length() + s2.length()) {
			int i=0;
			String s;
			if(j <s1.length()) {
				i = j;
				s = s1;
			} else {
				i = j-s1.length();
				s = s2;
			}
			
			if(j == s1.length() && i ==0) {
				for(int k=0; k< 26; k++) {
					if(map[k] !=0) {
						map[k] = -map[k];
					}
				}
			}
			
			switch(s.charAt(i)) {
			case '+':
				break;
			case '-':
				break;
			case '(':
				if(i-1>= 0 && s.charAt(i-1) == '-') {
					/*if(signStack.isEmpty()) {
						signStack.push("-");
					} else*/ if(!signStack.isEmpty() && "-".equals(signStack.peek())) {
						signStack.push("+");
					} else {
						signStack.push("-");
					}
				} else if(i-1>= 0 && s.charAt(i-1) == '+'){
					
					 	if(!signStack.isEmpty() && "-".equals(signStack.peek())) {
							signStack.push("-");
						} else {
							signStack.push("+");
						}
				} else {
					signStack.push("+");
				}
				break;
			case ')':
				signStack.pop();
				break;
			default :
				if(i-1>= 0 && s.charAt(i-1) == '-') {
					if(!signStack.isEmpty() && "-".equals(signStack.peek())) {
						map[s.charAt(i) -97]++;
					} else if(!signStack.isEmpty() && "+".equals(signStack.peek())) {
						map[s.charAt(i) -97]--;
					} else if(signStack.isEmpty()){
						map[s.charAt(i) -97]--;
					}
				} else if(i-1>= 0 && s1.charAt(i-1) == '+'){
					if(!signStack.isEmpty() && "-".equals(signStack.peek())) {
						map[s.charAt(i)-97]--;
					} else if(!signStack.isEmpty() && "+".equals(signStack.peek())) {
						map[s.charAt(i) -97]++;
					} else if(signStack.isEmpty()){
						map[s.charAt(i) -97]++;
					}
				} else {
				 	if(!signStack.isEmpty() && "-".equals(signStack.peek())) {
				 		map[s.charAt(i) -97]--;
					} else {
						map[s.charAt(i) -97]++;
					}
				}
				break;
			}
			
			j++;
		}
		
		for(int i=0; i< 26; i++) {
			if(map[i] !=0) {
				return false;
			}
		}
		
		return true;
	}
}
