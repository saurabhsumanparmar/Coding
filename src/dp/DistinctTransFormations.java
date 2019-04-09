package dp;

//https://practice.geeksforgeeks.org/problems/distinct-transformations/0
public class DistinctTransFormations {
	public static void main(String [] args) {
		System.out.println(noOftransformations("abcccdf", "abccdf"));
		System.out.println(noOftransformations("uwnny", "uwnny"));
		System.out.println(noOftransformations("aaa", "a"));
		System.out.println(noOftransformations("aaa", "aa"));
	}
	
	/*Mistakes : 
	 * 1. line no 28 ..>= instead of >
	 * 2. Logical error in initialising first column ..case ..aaa & aa 
	*/
	static int noOftransformations(String str, String pat) {
		if(pat.length() > str.length() || pat.length() ==0 || str.length() ==0) {
			return 0;
		}
		
		int [][] matrix = new int[str.length()][pat.length()];
		
		if(str.charAt(0) == pat.charAt(0)) {
			matrix[0][0] = 1;
		}
		
		for(int i=1; i < str.length(); i++) {
			matrix[i][0] = matrix[0][0];
			if(str.charAt(i) == pat.charAt(0)) {
				matrix[i][0] =  matrix[i][0] +1;
			}
		}
		
		for(int i=1; i<str.length(); i++) {
			for(int j=0; j<pat.length(); j++) {
				int possiblities = 0;
				
				if(i-1>= 0 && j-1 >= 0 && matrix[i-1][j-1] > 0 
						&& str.charAt(i) == pat.charAt(j)) {
					possiblities = matrix[i-1][j-1];
				}
				
				if(matrix[i-1][j] > 0) {
					possiblities  += matrix[i-1][j];
				}
				
				matrix[i][j] = possiblities;
			}
		}
		
		return matrix[str.length()-1][pat.length()-1];
	}
}
