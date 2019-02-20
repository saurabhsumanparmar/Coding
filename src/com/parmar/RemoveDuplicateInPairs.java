package com.parmar;

public class RemoveDuplicateInPairs {
	private String removeDuplicate(String str) {
		if(str.length() ==0 || str.length() ==1) return str;
		
		StringBuilder outputStr = new StringBuilder("");
		outputStr.setCharAt(0, str.charAt(0));
		int outputStrIndex = 0;
		
		int i =1;
		while(i< str.length()) {
			if(outputStrIndex >=0 && str.charAt(i) == outputStr.charAt(outputStrIndex)) {
				outputStrIndex --;
				i++;
			} else {
				outputStr.setCharAt(outputStrIndex, str.charAt(i));
			}
		}
		
		if(outputStrIndex == -1) {
			return "";
		} else {
			return outputStr.substring(0, outputStrIndex);
		}
	}

}
