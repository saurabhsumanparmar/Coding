package com.parmar;

public class RegularExpressionII {
	public int isMatch(final String A, final String B) {
		
		//check for zero length A & B
		if(A.length() == 0 && B.length() == 0) {
			return 1;
		} else if(A.length() !=0 && B.length() == 0) {
			return 0;
		} else if(A.length() ==0 ) {
			return isBcontainsOnlyStar(B) ? 1 :0;
		}
		
		boolean table[][] = new boolean[A.length()][B.length()];
		
			if(A.charAt(0) == B.charAt(0)) {
				table[0][0] =true;
			}
			
			if(B.charAt(0) == '.' || B.charAt(0) == '*') {
				table[0][0] =true;
			}

		// update first row and first column		
		for(int i=1; i<A.length(); i++) {
			if(table[0][i-1] && B.charAt(i) == '*') {
				table[0][i-1] = true;
			}
		}
		
		for(int i=1; i<A.length(); i++) {
			if(table[i-1][0] && B.charAt(0) == '*') {
				table[i-1][0] = true;
			}
		}
			
		
		for(int i=1; i<A.length(); i++) {
			for(int j=1; j<B.length(); j++) {
				if(table[i-1][j-1]) {
					
					if(A.charAt(i) == B.charAt(j)) {
						table[i][j] =true;
					} else if(B.charAt(j) == '*' ) {
						table[i][j] = table[i-1][j] || table[i][j-1];
					} else if(B.charAt(j) == '.' ) {
						table[i][j] =true;
					}
					
				}
			}
		}
		
		if(table[A.length()-1][B.length()-1]) {
			return 1;
		} else {
			return 0;
		}
    }

	private boolean isBcontainsOnlyStar(String b) {
		for(int i=0; i< b.length(); i++) {
			if(b.charAt(i) != '*') {
				return false;
			}
		}
		return true;
	}
}
