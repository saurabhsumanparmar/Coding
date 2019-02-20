package com.parmar;

import java.util.ArrayList;

class GenerateAllPermutationOfString {
	  
	  public static void main(String[] args) {
		 GenerateAllPermutationOfString testClass = new GenerateAllPermutationOfString();
	     char [] arr = { 'A', 'B', 'C'};
	     ArrayList<String> output = new ArrayList<String>();
	     testClass.generatePermutation(arr, 0, 2, output);
	     
	     int i = 0;
	     while(i < output.size()) {
	       System.out.println(output.get(i));
	       i++;
	     }
	  }
	  	    
	  // Mistakes : 
	  // 1. Wrong initialization ..in the recursion loop
	  // 2. and lots of typos
	  private void generatePermutation(char[] input, int start, int end, ArrayList<String> output) {
	    
	     if(start == end ) {
	        StringBuffer bf = new StringBuffer();
	     
	        int i = 0;
	     
	        while( i<= end) {
	          bf.append(input[i]);
	          i++; 
	        }
	     
	        output.add(bf.toString());
	      
	        return;
	     }
	    
	   
	     for(int j=start; j <= end ; j++) {
	        swap(input, start, j);
	        generatePermutation(input, start+1, end, output);
	        swap(input, start, j);
	     }
	   
	  }
	  
	  private void swap(char[] input, int i, int j) {
	     char temp = input[i];
	     input[i] = input[j];
	     input[j] = temp;
	  }
	  
	}
