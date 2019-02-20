package com.parmar;

//Not tested
public class KthNonRepeatingCharInString {
	public char kthNonRepeating(String s, int K) {
		int count=0;
		int [] charFrequecy = new int[52];
		
		for(int i=0; i<s.length(); i++) {
			int value = s.charAt(i);
			charFrequecy[value -65]++;
		}
		
		char output =' ';
		for(int i=0; i<s.length(); i++) {
			int value = s.charAt(i);
			if(charFrequecy[value -65] ==0) {
				count++;
				
				if(count ==K) {
					output = s.charAt(i);
					break;
				}
			}
		}
		
		return output;
	}
}
