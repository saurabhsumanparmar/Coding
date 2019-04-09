package dp;

public class MinimumInsertionToFormAPallindome {
	public static void main(String [] args) {
		System.out.println(minInsertions("abcda"));
		System.out.println(minInsertions("abcde"));
		System.out.println(minInsertions("aa"));
		System.out.println(minInsertions("aa"));
	}
	
	static int minInsertions(String str) {
		if(str.length() ==0) {
			return 0;
		} else if(str.length() ==1) {
			return 1;
		}
				
		int [][] matrix = new int[str.length()][str.length()];
		
		for(int i=0; i < str.length(); i++) {
			matrix[i][i] =0;
		}
		
		for(int k=1; k< str.length(); k++) {
			for(int i=0; i+k < str.length(); i++) {
				int j = i+k;
				
				if(k ==1) {
					if(str.charAt(i) == str.charAt(j)) {
						matrix[i][j] =0;
					} else {
						matrix[i][j] =1;
					}
				} else {
					if(str.charAt(i) == str.charAt(j)) {
						matrix[i][j] = matrix[i+1][j-1];
					} else {
						matrix[i][j] = min(matrix[i+1][j] +1, matrix[i][j-1] +1, matrix[i+1][j-1] +2);
					}
				}
			}
		}
		
		return matrix[0][str.length()-1];
	}

	private static int min(int i, int j, int k) {
		if(i <= j && i <=k) {
			return i;
		} else if (j <= i && j <=k) {
			return j;
		} else {
			return k;
		}
	}
}
