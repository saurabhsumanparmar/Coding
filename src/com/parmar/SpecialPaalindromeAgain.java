package com.parmar;

/*https://www.hackerrank.com/challenges/special-palindrome-again/problem?
	h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings*/

public class SpecialPaalindromeAgain {
	public static void main(String [] args) {
		//String str = "abcbaba";
		//String str = "asasd";
		//String str = "aaba";
		String str = "ccacacabccacabaaaabbcbccbabcbbcaccabaababcbcacabcabacbbbccccabcbcabbaaaaabacbcbbbcababaabcbbaa"
				+ "ababababbabcaabcaacacbbaccbbabbcbbcbacbacabaaaaccacbaabccabbacabaabaaaabbccbaaaab"
				+ "acabcacbbabbacbcbccbbbaaabaaacaabacccaacbcccaacbbcaabcbbccbccacbbcbcaaabbaababacccbaca"
				+ "cbcbcbbccaacbbacbcbaaaacaccbcaaacbbcbbabaaacbaccaccbbabbcccbcbcbcbcabbccbacccbacabcaacbcac"
				+ "cabbacbbccccaabbacccaacbbbacbccbcaaaaaabaacaaabccbbcccaacbacbccaaacaacaaaacbbaaccacbcbaaaccaab"
				+ "cbccacaaccccacaacbcacccbcababcabacaabbcacccbacbbaaaccabbabaaccabbcbbcaabbcabaacabacbcabbaaabccab"
				+ "cacbcbabcbccbabcabbbcbacaaacaabb"
				+ "babbaacbbacaccccabbabcbcabababbcbaaacbaacbacacbabbcacccbccbbbcbcabcabbbcaabbaccccabaa"
				+ "bbcbcccabaacccccaaacbbbcbcacacbabaccccbcbabacaaaabcccaaccacbcbbcccaacccbbcaaaccccaabacabc"
				+ "abbccaababbcabccbcaccccbaaabbbcbabaccacaabcabcbacaccbaccbbaabccbbbccaccabccbabbbccbaabcaab"
				+ "cabcbbabccbaaccabaacbbaaaabcbcabaacacbcaabbaaabaaccacbaacababcbacbaacacccacaacbacbbaacbcbbbabc"
				+ "cbababcbcccbccbcacccbababbcacaaaaacbabcabcacaccabaabcaaaacacbccccaaccbcbccaccacbcaaaba";
		System.out.println(substrCount2(1017,  str));
	}
	
	// Complete the substrCount function below.
    static long substrCount(int n, String s) {
    	long output=0;
    	
    	boolean[][] matrix = new boolean[n][n];
    	
    	for(int i=0; i<n; i++) {
    		matrix[i][i] = true;
    		output++;
    	}
    	
    	for(int gap=1; gap<n; gap++) {
    		for(int i=0; i+gap <n; i++) {
    			int j = i+gap;
    			
    			if(gap ==1) {
    				if(s.charAt(i) == s.charAt(j)) {
    					matrix[i][j] = true;
    					output++;
    				} else {
    					matrix[i][j] = false;
    				}
    			} else {
    				if(s.charAt(i) == s.charAt(j) && matrix[i+1] [j-1]) {
    					
    					if(j-i >= 4 && s.charAt(i)== s.charAt(i+1)) {
    						matrix[i][j] = true;
        					output++;
    					} else if(j-i <4) {
    						matrix[i][j] = true;
        					output++;
    					} else {
    						matrix[i][j] = false;
    					}
    					
    				} else {
    					matrix[i][j] = false;
    				}
    			}
    		}
    	}
    	
    	return output;
    }
    
    static long substrCount2(int n, String s) {
    	
    	char prevToPrev = 'X';
    	char prev = 'X';
    	char cur = 'X';
    	
    	int prevToPrevLen = -1;
    	int prevLen = -1;
    	int curLen = 0; 
    	
    	int i=0;
    	long count=0;
    	while(i < n) {
    		count++;
    		cur = s.charAt(i);
    		int curIndex = i;
    		curLen = 1;
    		
    		while(i+1 < n && s.charAt(i)== s.charAt(i+1)) {
    			i++;
    		}
    		
    		if(i - curIndex > 0) {
    			curLen = i - curIndex;
    			
    			count = count + getCountOfPallindromeInRepreatedCharlen(curLen +1);
    			
    			count = count-1;
    		}
    		
    		if(prevLen == 1 && cur == prevToPrev) {
    			if(curLen == prevToPrevLen) {
    				count = count+curLen;
    			} else if(curLen > prevToPrevLen) {
    				count = count + prevToPrevLen;
    			} else {
    				count = count + curLen;
    			}
    		}
    		
    		
    		prevToPrevLen = prevLen;
    		prevToPrev = prev;
    		
    		prevLen = curLen;
    		prev = cur;
    		
    		i++;
    	}
    	
    	return count;
    }

	private static int getCountOfPallindromeInRepreatedCharlen(int curLen) {
		switch(curLen) {
		case 1:
			return 1;
		case 2:
			return 3;
		case 3:
			return 6;
		case 4:
			return 10;
		case 5:
			return 15;
		case 6:
			return 21;
		}
		
		return 0;
	}
    
}
