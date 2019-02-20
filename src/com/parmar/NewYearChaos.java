package com.parmar;


/*https://www.hackerrank.com/challenges/new-year-chaos/problem?
	h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays*/

public class NewYearChaos {
	public static void main(String [] args) {
		int [] q = {2, 1, 5, 3, 4};
		//int [] q = {2, 5, 1, 3, 4};
		//int [] q = {5, 1, 2, 3, 7, 8, 6, 4};
		//int [] q = {1, 2, 5, 3, 7, 8, 6, 4};
		minimumBribes(q);
	}
	
	// mistakes :
	// 1. Missed import use case where a guy can take more than 2 bribes but can't give more than 2
    static void minimumBribes(int[] q) {
        if(q.length== 0) {
        	System.out.println("Too chaotic");
			return;
        }
        
        int count =0;
        for(int i=q.length-1; i>0; i--) {
        	if(q[i] == i+1) {
        		continue;
        	} else {
        		if(q[i-1] == i+1) {
        			swap(q, i, i-1);
        			count++;
        		} else if(q[i-2] == i+1) {
        			swap(q, i-2, i-1);
        			swap(q, i-1, i);
        			count = count+2;
        		} else {
        			System.out.println("Too chaotic");
        			return;
        		}
        	}
        }
    	
        System.out.println(count);
    }
    
	private static void swap(int[] q, int i, int j) {
		int temp = q[i];
		q[i] = q[j];
		q[j] = temp;
	}
}
