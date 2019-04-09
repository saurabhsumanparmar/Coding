package strings;

public class KMP {
	public static void main(String [] args) { 
	    String txt = "ABABDABACDABABCABAB"; 
	    String pat = "ABABCABAB"; 
	    KMPSearch(txt, pat);
	}

	private static void KMPSearch(String txt, String pat) {
		int[] lps = lps(pat);
		
		int i=0;
		int j =0;
		while(i <txt.length()) {
			if(txt.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
			} 
			
			if(j == pat.length()) {
				System.out.println("Pattern found at" + (i-j));
				j = lps[j-1];
			} else if(i <txt.length() && txt.charAt(i) != pat.charAt(j)){
				if(j == 0) {
					i++;
				} else {
					j = lps[j-1];
				}
			}
		}
	}
	
	private static int[] lps(String str) {
		if(str.length() ==0) {
			return null;
		}
		
		int lps [] = new int[str.length()];
		
		int len=0;
		int i=1;
		lps[0] =0;
		
		while(i < str.length()) {
			if(str.charAt(i) == str.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else if (str.charAt(i) != str.charAt(len)) {
				if(len ==0) {
					lps[i] = 0;
					i++;
				} else {
					len = lps[len - 1];
				}
			}
		}
		
		return lps;
	}
}
