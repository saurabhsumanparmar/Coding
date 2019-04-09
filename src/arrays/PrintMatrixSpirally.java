package arrays;

public class PrintMatrixSpirally {
	public static void main(String [] args) {
		/*int [][] matrix = {
				{1, 2, 3, 4}, 
				{5, 6, 7, 8},
				{ 9,10, 11, 12},
				{13, 14, 15, 16},
				{17, 18, 19, 20}
		};*/
		
		int [][] matrix = {
				{1, 2, 3}, 
				{4, 5, 6},
				{7, 8, 9},
				{10, 11, 12},
				{13, 14, 15}
		};
		
		PrintMatrixSpirally testClazz = new PrintMatrixSpirally();
		testClazz.printMatrxSpirally(matrix);
	}
	
	// code ran in first the attempt ..claps
	// But took time to formalize the loop and variables ..need to cut it down
	public void printMatrxSpirally(int [][] matrix) {
		int row = matrix.length;
		
		if(row ==0) {
			return;
		}
		
		int col = matrix[0].length;
		int totalCount = row *col;
		
		int elementsInCol = col;
		int elementsInRow = row;
		for(int i=0, j=0; totalCount > 0; i++, j++) {
			
			int indexRow = i;
			int indexCol = j;
			
			// right movement
			for(int k=indexCol, count = elementsInCol; totalCount> 0 && count > 0; k++, count--) {
				System.out.println(matrix[indexRow][k]);
				totalCount--;
				indexCol =k;
			}
			
			// down movement
			for(int k=indexRow+1, count = elementsInRow-1; totalCount> 0 && count > 0; k++, count--) {
				System.out.println(matrix[k][indexCol]);
				totalCount--;
				indexRow =k;
			}
			
			// left movement
			for(int k=indexCol-1, count = elementsInCol-1; totalCount> 0 && count > 0; k--, count--) {
				System.out.println(matrix[indexRow][k]);
				totalCount--;
				indexCol =k;
			}
			
			// up movement
			for(int k=indexRow-1, count = elementsInRow-2; totalCount> 0 && count > 0; k--, count--) {
				System.out.println(matrix[k][indexCol]);
				totalCount--;
				indexRow =k;
			}
			
			elementsInCol = elementsInCol-2;
			elementsInRow = elementsInRow -2;
		}
		
	}
}
