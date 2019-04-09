package graph;

// error : only 
public class WordSearchBoard {
	public static void main(String [] args) {
		char [] [] matrix = { {'A', 'B', 'C', 'E'},
		  {'S', 'F', 'C', 'S'},
		  {'A', 'D', 'E', 'E'},
		};
		
		System.out.println(isWordFoundWrapper("ABFSAB", matrix));
		System.out.println(isWordFoundWrapper("ABCD", matrix));
		System.out.println(isWordFoundWrapper("ABCCED", matrix));
		System.out.println(isWordFoundWrapper("SEE", matrix));
		System.out.println(isWordFoundWrapper("ABCB", matrix));
	}
	
	static boolean isWordFoundWrapper (String word, char [][] matrix) {
		if(word.length() ==0) {
			return true;
		}
		
		int matrixRowSize = matrix.length;
		
		if(matrixRowSize == 0) {
			return false;
		}
		
		int matrixColSize = matrix[0].length;
		
		for(int i=0; i < matrixRowSize; i++) {
			for(int j=0; j < matrixColSize; j++) {
				if(isWordFound(word, 0, matrix, i, j)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	// Comments : Diagonals not allowed hence commented
	static boolean isWordFound(String word, int index, char [][] matrix, int i, int j) {
		int matrixRowSize = matrix.length;
		int matrixColSize = matrix[0].length;
		if(matrix[i][j] == word.charAt(index) && index == word.length()-1) {
			return true;
		} 
		
		if(matrix[i][j] == word.charAt(index)) {
			
			boolean topLeft= false;
			boolean topMiddle = false;
			boolean topRight = false;
			boolean rowLeft = false;
			boolean rowright = false;
			boolean bottomLeft = false;
			boolean bottomMiddle = false;
			boolean bottomright = false;
			
			
			/*if(i-1 >=0 && j-1 >=0) {
				topLeft = isWordFound(word, index +1, matrix, i-1, j-1);
			}*/
			
			if(i-1>=0) {
				topMiddle = isWordFound(word, index +1, matrix, i-1, j);
			}
			
			/*if(i-1>= 0 && j+1 <=matrixColSize-1) {
				topRight = isWordFound(word, index +1, matrix, i-1, j+1);
			}*/
			
			if(j-1>= 0) {
				rowLeft = isWordFound(word, index +1, matrix, i, j-1);
			}
			
			if(j+1 <=matrixColSize-1) {
				rowright = isWordFound(word, index +1, matrix, i, j+1);
			}
			
			/*if(i+1 <=matrixRowSize-1 && j-1 >=0) {
				bottomLeft = isWordFound(word, index +1, matrix, i+1, j-1);
			}*/
			
			if(i+1 <=matrixRowSize-1) {
				bottomMiddle = isWordFound(word, index +1, matrix, i+1, j);
			}
			
			/*if(i+1 <=matrixRowSize-1 && j+1 <=matrixColSize-1) {
				bottomright = isWordFound(word, index +1, matrix, i+1, j+1);
			}*/
			
			boolean repeating = isWordFound(word, index +1, matrix, i, j);
			
			return topLeft || topMiddle || topRight || rowLeft || rowright ||
					bottomLeft || bottomMiddle || bottomright || repeating;
		}
		
		return false;
	}
}
