package com.parmar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Numrange {
	public static void main(String [] args) {
		 Integer [] arr = { 10, 5, 1, 0, 2};
		 
		 List<Integer> list = Arrays.asList(arr);
		
		 
		 Numrange testClass = new Numrange();
		 System.out.println(testClass.numRange(list, 6, 8));
	}
	
	public int numRange(List<Integer> A, int B, int C) {
        int count = 0;
        
        if(A.size() ==0) {
            return count;   
        }
        
        int minEnd = -1;
        
        int i=0;
        int j=0;
        int sum = A.get(0);
        while(i+j < A.size()) {
            if(sum < B) {
                j++;
            } else if(sum >= B && sum <= C) {
                count++;
                if(minEnd == -1) {
                    minEnd = i+j;
                }
                
                j++;
             
            } else { //sum > C
            	
                if(minEnd == -1) {
                    i++; j=i;
                } else {
                	Index index = findNewStart(sum, A, i, minEnd, j,  B, C);
                    i = index.getStart(); 
                    minEnd= index.getStart();
                    
                    if(i != -1) {
                    	count++;
                    } else {
                    	i++; j=0;
                    }    
                }
            }
        }
        
        return count;
    }

	private Index findNewStart(int sum, List<Integer> a, int i, int minEnd, int j,  int b, int c) {
		if(i+j >= a.size()-1) {
			new Index (-1, -1);
		}
		
		sum = sum + a.get(minEnd+1) - a.get(i);
		i++;
		minEnd +=1;
		int j1 = 0;
		
		while(minEnd < a.size()) { // && i < minEnd
			if(sum < b) {
			   if(i >= a.size()-1  || minEnd>= a.size()-1) {
        			new Index (-1, -1);
        	   }
			   minEnd++;
               sum = sum + a.get(minEnd);
            } else if(sum >= b && sum <= c) {
            	new Index (i, minEnd);
            } else { //sum > C
            	
            	if(i >= a.size()-1  || minEnd>= a.size()-1) {
        			new Index (-1, -1);
        		}
            	
            	if(i == minEnd) {
            		i++;
            		minEnd ++; 
            	} else {
            		sum = sum + a.get(minEnd+1) - a.get(i);
            		i++;
            		minEnd +=1;
            	}
            }
		}
		
		return new Index (-1, -1);
	}
	
	class Index {
		int start;
		int end;
		public int getStart() {
			return start;
		}
		public void setStart(int start) {
			this.start = start;
		}
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}
		public Index(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
	}
}
