package com.parmar;

import java.util.LinkedList;

//Need to run this code
public class MinWindowString {
	
	public static void main(String [] args) {
		 String str1 =  "0mJdGXwLm9AOZ5xA8u92KDYqGJroQ537hoRXjQoUCMOpDYwxoNjexJGWQJAIxSFF3ZbIe27oFx"
		 		+ "UTJxtv8mORwpuRZn30MDj3kXRW2Ix3lslj7kwmGZPXAKhBW4q5T2BzsqbL0ZETFqRdxVm8GCGfqshvpWz"
		 		+ "sRvprUcF9vw3VlftqTRzKRzr4zYB2P6C7lg3I7EhGMPukUj8XGGZTXqPqnCqes";
		 String str2 = "rsm2ty04PYPEOPYO5";
		 MinWindowString testClass = new MinWindowString();
		 System.out.println(testClass.minWindow(str1, str2));
		 
		 //System.out.println((int)str1.charAt(0));
		 
		 System.out.println(425 % 1000);
	}
	 
	public String minWindow(String A, String B) {
		int [] charCountTable = new int[75];
		
		for(int i=0; i< B.length(); i++) {
			charCountTable[B.charAt(i) - 48] = ++charCountTable[B.charAt(i) - 48];
		}
		
		LinkedList<Integer> [] indexTable = new LinkedList[75];
		
		int totalOccurance = B.length();
		int startIndex = -1;
		int endIndex = -1;
		
		for(int i=0; i<A.length(); i++) {
			int index = A.charAt(i) - 48;
			
			if(charCountTable[index] != 0) {
				charCountTable[index]--;
				totalOccurance--;
				
				if(indexTable[index] ==null) {
					indexTable[index] = new LinkedList<Integer>();
					
				}
				
				indexTable[index].add(i);
			} else if(charCountTable[index] == 0 && indexTable[index] !=null && indexTable[index].size() > 0) {
				LinkedList<Integer> list = indexTable[index];
				list.add(i);
				list.removeFirst();
			}
			
			if(totalOccurance ==0) {
				int minIndex = Integer.MAX_VALUE;
				int maxIndex = Integer.MIN_VALUE;
				
				for(int j =0; j<charCountTable.length; j++) {
					if(charCountTable [j] == 0 && indexTable[j]!= null && indexTable[j].size() >0) {
						if(minIndex > indexTable[j].getFirst()) {
							minIndex = indexTable[j].getFirst();
						}
						
						if(maxIndex < indexTable[j].getLast()) {
							maxIndex = indexTable[j].getLast();
						}
					}
				}
				
				if(startIndex !=-1) {
					if((endIndex - startIndex)  > (maxIndex -minIndex)) {
						endIndex = maxIndex;
						startIndex = minIndex;
					}
				} else {
					endIndex = maxIndex;
					startIndex = minIndex;
				}
			}
		}
		
		if(startIndex == -1) return "";
		
		return A.substring(startIndex, endIndex+1);
    }
}
