package arrays;

public class GoldMineProblem {
	public static void main(String [] args) {
		int mat[][] = { {1, 3, 1, 5},
                {2, 2, 4, 1},
                {5, 0, 2, 3},
                {0, 6, 1, 2}};
		System.out.println(maximumAmount(mat));
		
		int mat2[][] = {{10, 33, 13, 15},
                {22, 21, 04, 1},
                {5, 0, 2, 3},
                {0, 6, 14, 2}};
		
		System.out.println(maximumAmount(mat2));
	}
	
	static int maximumAmount(int [][] arr) {
		int rowSize = arr.length;
		
		if(rowSize ==0) {
			return -1;
		}
		
		int colSize = arr[0].length;
		
		int [][] maxArr = new int[rowSize][colSize];
		
		for(int i=0; i < rowSize; i++) {
			maxArr[i][0] = arr[i][0];
		}
		
		for(int j=1; j<colSize; j++) {
			for(int i=0; i< rowSize; i++) {
				maxArr[i][j] =  maxArr[i][j-1] + arr[i][j];
				
				if(i-1 >=0 && maxArr[i][j] < maxArr[i-1][j-1] + arr[i][j]) {
					maxArr[i][j] = maxArr[i-1][j-1] + arr[i][j];
				}
				
				if(i + 1 < rowSize  && maxArr[i][j] < maxArr[i+1][j-1] + arr[i][j]) {
					maxArr[i][j] = maxArr[i+1][j-1] + arr[i][j];
				}
				
				if(j == rowSize -1 && i-1 >= 0 && maxArr[i-1][j] > maxArr[i][j]) {
					maxArr[i][j] = maxArr[i-1][j];
				}
			}
		}
		
		return maxArr[rowSize -1][colSize -1];
	}
}
