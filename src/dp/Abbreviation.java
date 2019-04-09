package dp;

// https://www.hackerrank.com/challenges/abbr/problem
public class Abbreviation {
	public static void main(String [] args) {
		System.out.println(abbreviation("ABcDE", "ABDE"));
		System.out.println(abbreviation("AbcDE", "AFDE"));
	}
	
	static String abbreviation(String a, String b) {
		if(b.length() > a.length()) {
			return "NO";
		}
		
		boolean [][] matrix = new boolean[b.length()][a.length()];
		for(int i=0;i <b.length(); i++) {
			for(int j=0; j< a.length(); j++) {
				
				if(i==0 && j==0) {
					if(b.charAt(0) == a.charAt(0) || b.charAt(0) == Character.toUpperCase(a.charAt(0))) {
						matrix[0][0] = true;
					} else if(i==0 && j==0  && a.charAt(0) < 97) {
						return "NO";
					}
				} else if(i==0){
					if(((matrix[0][j-1] && a.charAt(j) >= 97)) || (b.charAt(0) == a.charAt(j)) ||
							(b.charAt(0) == Character.toUpperCase(a.charAt(j)))) {
						matrix[0][j] = true;
					} else if((b.charAt(0) != a.charAt(j)) && 
							(b.charAt(0) != Character.toUpperCase(a.charAt(j))) && 
							a.charAt(i) < 97) {
						return "NO";
					}
				} else {
					if(j==0) {
						matrix[i][j] = false;
					} else if((matrix[i-1][j-1] && b.charAt(i) == a.charAt(j)) ||
							(matrix[i-1][j-1] && b.charAt(i) == Character.toUpperCase(a.charAt(j))) ||
							(matrix[i][j-1] &&a.charAt(j) >= 97)) {
						matrix[i][j] = true;
					}
				}	
			}
		}

		return matrix[b.length()-1][a.length()-1] ? "YES" : "NO";
	}
}
