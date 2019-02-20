package com.parmar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class WordBreak {
	public static void main(String [] args) {
		WordBreak testClass = new WordBreak();
		ArrayList<String> dict = new ArrayList<String>();
		//"trainer", "my", "interview"
		dict.add("aa");
		//dict.add("a");
		//dict.add("b");
		dict.add("ccd");
		dict.add("bb");
		dict.add("x");
		
		System.out.println(testClass.wordBreak2("aaccdbbx", dict));
		//test for ""  
	}
	
	public int wordBreak(String A, ArrayList<String> B) {
		boolean [][] table1 = new boolean[A.length()][A.length()];
		for(int gap=0; gap<=A.length(); gap++) {
			for(int i=0; i+gap < A.length(); i++) {
				table1[i][i+gap] = doesDictContains(A, i, i+gap, B);
			}
		}
		
		boolean [][] table2 = new boolean[A.length()][A.length()];
		
		for(int i=0; i< A.length(); i++) {
			table2[i][i] = table1[i][i];
		}
		
		for(int gap=1; gap<=A.length(); gap++) {
			for(int i=0; i+gap < A.length(); i++) {
				if(table1[i][i+gap]) {
					table2[i][i+gap] = true;
				} else {
					for(int k=i; k < i+gap; k++) {
						if(table2[i][k] && table2[k+1][i+gap]) {
							table2[i][i+gap] = true;
						} else {
							if(!table2[i][i+gap]) table2[i][i+gap] = false;
						}
					}
				}		
			}
		}
		
		if(table2[0][A.length()-1]) {
			return 1;
		} else {
			return 0;
		}
    }

	private boolean doesDictContains(String a, int i, int j, ArrayList<String> b) {
		String str = a.substring(i, j+1);
		for(String str1 : b) {
			if(str1.equals(str)) {
				return true;
			}
		}
		
		return false;
	}
	
	public int wordBreak2(String A, ArrayList<String> B) {
		int [] lookup = new int[A.length()];
		for(int i=0; i<A.length(); i++) {
			lookup[i]= -1;
		}
		
		int lastIndex = -1;
		if(doesDictContains(A, 0, 0, B)) {
			lastIndex++;
			lookup[0] = 0;
		}
		
		for(int i=1; i < A.length(); i++) {
			if(doesDictContains(A, 0, i, B)) {
				lastIndex++;
				lookup[lastIndex] = i;
				continue;
			} else if (lastIndex != -1){
				for(int j=0; j<=lastIndex; j++) {
					if(doesDictContains(A, lookup[j] + 1, i, B)) {
						lastIndex++;
						lookup[lastIndex] = i;
						continue;
					}
				}
			}
		}
		
		if(lastIndex != -1 && lookup[lastIndex]== A.length()-1) {
			return 1;
		} else {
			return 0;
		}
	}
}
