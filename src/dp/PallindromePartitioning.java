package dp;

public class PallindromePartitioning {
	public static void main(String [] args) {
		PallindromePartitioning testClass = new PallindromePartitioning();
		  System.out.println(testClass.minCut("bbab"));
		  //test for ""
		  
	 }
	
	public int minCut(String A) {
		
		if(A.length() ==0 || A.length() ==1) {
			return 0;
		} else if(A.length() ==2) {
			if(A.charAt(0) == A.charAt(1)) {
				return 0;
			} else {
				return 1;
			}
		}
		
		// build pallindrome table from index i to j	
		boolean [][] pall = new boolean[A.length()][A.length()];
		for(int i=0; i<A.length(); i++) {
			pall[i][i] = true;
		}
		
		for(int k=1;k<=A.length()-1; k++) {
			for(int i=0; i+k <A.length(); i++) {
				int j =i+k;
				if(A.charAt(i) == A.charAt(j)) {
					if(j-1 >= i+1 && pall[i+1][j-1]) {
						pall[i][j] = true;
					} else if(j-1 >= i+1 && !pall[i+1][j-1]){
						pall[i][j] = false;
					} else {
						pall[i][j] = true;
					}
				} else {
					pall[i][j] = false;
				}
			}
		}
		
		int [][] mincut = new int[A.length()][A.length()];
		for(int i=0; i<A.length(); i++) {
			mincut[i][i] = 0;
		}
		
		for(int i=0; i<A.length()-1; i++) {
			if(pall[i][i+1]) {
				mincut[i][i+1] = 0;
			} else {
				mincut[i][i+1] = 1;
			}	
		}
		
		for(int gap=2; gap <= A.length()-1; gap++) {
			for(int i=0; i<A.length()-1 && i+gap <= A.length()-1; i++) {
				if(pall[i][i+gap]) {
					mincut[i][i+gap] = 0;
				} else {
					mincut[i][i+gap] = gap+1;
					for(int k=i; k <i+gap; k++) {
						if(mincut[i][k] + mincut[k+1][i+gap] +1 < mincut[i][i+gap]) {
							mincut[i][i+gap] = mincut[i][k] + mincut[k+1][i+gap] +1;
						}
					}
				}
			}
		}
		
		return mincut[0][A.length()-1];
    }
}
