package com.parmar;

import java.util.ArrayList;

/*A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

Example :

Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.*/

public class WaysToDecode {
	public static void main(String [] args) {
		  WaysToDecode testClass = new WaysToDecode();
		  System.out.println(testClass.numDecodings("875361268549483279131"));
		  //test for "875361268549483279131"
		  
	 }
	
	 public int numDecodings(String A) {
	        if(A.length() ==0) {
	            return 0;
	        }
	        
	        int [] noOfWays = new int[A.length()];
	        
	        for(int i=0; i<A.length(); i++) {
	           if(i==0) {
	                if(A.charAt(0) == '0') {
	                   return 0;
	                } else {
	                    noOfWays[0] =1;
	                }
	            } else if (i==1) {
	                if((A.charAt(i-1) == '1' || A.charAt(i-1) == '2') && A.charAt(i) != '0') {
	                    noOfWays[1] = 2;
	                } else {
	                    noOfWays[1] = 1;
	                }
	            } else {
	                if((A.charAt(i) == '0' && A.charAt(i-1) != '1')  && (A.charAt(i) == '0' && A.charAt(i-1) != '2')) {
	                    return 0;
	                }
	                
	                if(A.charAt(i-1) == '1' && A.charAt(i) != '0') {
	                    noOfWays[i] = noOfWays[i-1] + noOfWays[i-2];
	                } else if (A.charAt(i-1) == '2' && A.charAt(i) != '0' && A.charAt(i) != '7' &&
	                		A.charAt(i) != '8' && A.charAt(i) != '9') {
	                	noOfWays[i] = noOfWays[i-1] + noOfWays[i-2];
	                } else {
	                    if(A.charAt(i) == '0') {
	                        noOfWays[i] = noOfWays[i-2];
	                    } else {
	                        noOfWays[i] = noOfWays[i-1];
	                    }
	                }
	            }
	        }
	        
	        return noOfWays[noOfWays.length -1];
	    }
}
