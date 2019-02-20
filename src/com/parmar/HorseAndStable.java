package com.parmar;

public class HorseAndStable {
	
	public static void main(String [] args) {
		  HorseAndStable testClass = new HorseAndStable();
		  System.out.println(testClass.arrange("BWWWWBBWWBWBWWBBBBBWBWWBBBWWWWBBBW", 28));
		  //test for "BWWWWBBWWBWBWWBBBBBWBWWBBBWWWWBBBW"
		  
	 }
	
	 public int arrange(String A, int B) {
		 
		 if(A.length() < B) {
			 return -1;
		 }
		 
		 int [][] table = new int[A.length()][B+1];
		 
		 //Initialisations
		 popuplateFirstColumn(table, A);
		 
		 
		 for(int k=1; k<=B; k++) {
			 for(int i=1; i< A.length(); i++) {
				 if(i < k) {
					 //table[i][k] = -1 ;
					 continue;
				 }
				 
				 if(i == k) {
					 table[i][k] = 0 ;
					 continue;
				 }
				 
				 for(int j=0; j<i; j++) { 
					 int minValue = table[j][k-1] + productOfWhiteAndBlack(j, i, A);
					  
					 if(table[i][k] ==0 || table[i][k] > minValue) {
						 table[i][k] = minValue; 
					 }
				 }
			 }
		 }
		 
		 return table[A.length()-1][B];
	 }
	 
	private void popuplateFirstColumn(int[][] table, String a) {
		int noOfWhite = 0;
		int NoofBlack = 0;
		for(int i = 0; i<a.length(); i++) {
			if(a.charAt(i) == 'W') {
				noOfWhite++;
			} else {
				NoofBlack++;
			}
			
			table[i][0] = noOfWhite * NoofBlack;
		}
	}

	// Scope to optimise
	private int productOfWhiteAndBlack(int j, int i, String a) {
		if(j==i || j+1 ==i) {
			return 0;
		}
		
		int noOfWhite = 0;
		int NoofBlack = 0;
		for(int k = j+1; k<=i; k++) {
			if(a.charAt(k) == 'W') {
				noOfWhite++;
			} else {
				NoofBlack++;
			}
		}
		
		return noOfWhite * NoofBlack;
	}
}
