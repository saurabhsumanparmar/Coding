package arrays;

public class RotateMatrixAntiClockWiseByKElements {
	void rotateMatrix(int [][] matrix, int k) {
		int rowSize = matrix.length;
		
		if(rowSize == 0) {
			return;
		}
		
		int colSize =  matrix[0].length;
		
		for(int i=0,j=0, colcount= colSize, rowcount = rowSize; i<rowSize/2 && j< colSize/2;
				i++, j++, colcount = colcount-2, rowcount= rowcount-2) {
			if(!doesRingHasMoreThanOrEqualToKelements(rowSize, colSize, k)) {
				break;
			}
			
			int lastIndex = findIndexAfterKAntiClockWise(i, j, i, j, rowSize, colSize, k);
		}
	}
	
	class CoOrdinate {
		int row;
		int col;
		public CoOrdinate(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	class RelativeIndex {
		int quad;
		int offset;
		public RelativeIndex(int quad, int offset) {
			super();
			this.quad = quad;
			this.offset = offset;
		}
	}

	private  int findIndexAfterKAntiClockWise(int i, int j, int topLefti, int topLeftj, int rowSize, int colSize, int k) {
		int topRighti = topLefti;
		int topRightj = topLeftj + colSize-1;
		
		int bottomRighti = topRighti + rowSize -1;
		int bottomRightj = topRightj;
		
		int bottomLefti = bottomRighti;
		int bottomLeftj = bottomRightj - (colSize-1);
		
		int quad;
		int offset =0;
		if(i== topLefti) {
			quad =1;
		} else if (i== bottomLefti) {
			quad = 3;
		} else if(j == topLeftj) {
			quad = 2;
		} else {
			quad = 4;
		}
		
		switch(quad) {
		case 1:
			offset = j;
			break;
		case 2:
			offset = i;
			break;
		case 3:
			offset = j;
			break;
		case 4:
			offset = i;
			break;
		}
		
		int destQuad;
		int destOffset;
		RelativeIndex relativeIndex;
		
		boolean flag =true;
		switch(quad) {
		case 1:
			if(k <= offset) {
				destQuad = 1;
				destOffset = offset -k;
				break;
			}
			
			
			String traversalPath = "col";
			int noOfElementTraversed = i;
			int currentQuad = 2;
			
			relativeIndex = getRelativeIndex(traversalPath, rowSize, colSize, noOfElementTraversed, currentQuad, k);	
			break;
		case 2:
			if(rowSize - 1 - offset >= 0) {
				destQuad = 2;
				destOffset = offset + k;
				break;
			}
			
			
			String traversalPath2 = "row";
			int noOfElementTraversed2 = rowSize - 1 - offset;
			int currentQuad2 = 3;
			
			relativeIndex = getRelativeIndex(traversalPath2, rowSize, colSize, noOfElementTraversed2, currentQuad2, k);
			
			break;
		case 3:
			if(colSize - 1 - offset >=0) {
				destQuad = 3;
				destOffset = offset + k;
				break;
			}
			
			
			String traversalPath3 = "col";
			int noOfElementTraversed3 = colSize - 1 - offset;
			int currentQuad3 = 4;
			
			relativeIndex = getRelativeIndex(traversalPath3, rowSize, colSize, noOfElementTraversed3, currentQuad3, k);
			
			break;
		case 4:
			if(k <= i) {
				destQuad = 4;
				destOffset = i -k;
				break;
			}
			
			
			String traversalPath4 = "row";
			int noOfElementTraversed4 = i-k;
			int currentQuad4 = 1;
			
			relativeIndex = getRelativeIndex(traversalPath4, rowSize, colSize, noOfElementTraversed4, currentQuad4, k);
			break;
		}
		
		return 0;
	}

	private RelativeIndex getRelativeIndex(String traversalPath, int rowSize, int colSize,
			int noOfElementTraversed, int currentQuad, int k) {
		int destQuad;
		int destOffset;
		while(true) {
			if("row".equals(traversalPath)) {
				if(colSize -1 + noOfElementTraversed < k) {
					destQuad = currentQuad;
					destOffset = k-noOfElementTraversed;
					break;
				} else {
					currentQuad = currentQuad ==4 ? 1: currentQuad+1;
					noOfElementTraversed = noOfElementTraversed + colSize -1;
					traversalPath = "col";
				}
			}
			
			if("col".equals(traversalPath)) {
				if(rowSize -1 + noOfElementTraversed < k) {
					destQuad = currentQuad;
					destOffset = k-noOfElementTraversed;
					break;
				} else {
					currentQuad = currentQuad ==4 ? 1: currentQuad+1;
					noOfElementTraversed = noOfElementTraversed + rowSize -1;
					traversalPath = "row";
				}
			}
		}
		
		return new RelativeIndex(destQuad, destOffset);
	}

	private  boolean doesRingHasMoreThanOrEqualToKelements(int rowSize, int colSize, int k) {
		if(rowSize==1) {
			return colSize >= k ? true :false;
		} else if(colSize ==1) {
			return rowSize >= k ? true :false;
		} else {
			int size = 2*colSize + 2* rowSize -4;
			
			return size >=k ? true : false;
		}
	}

	private void rotate2(int [][] matrix, int k) {
		int rowSize = matrix.length;
		
		if(rowSize ==0) {
			return;
		}
		
		int colSize =  matrix[0].length;
		
		for(int i=0, j=0, rowCount= rowSize, colCount = colSize; i < rowSize/2 && j < colSize/2;
				i++, j++, rowCount = rowCount-2, colCount = colSize-2) {
			if(!checkIfRingHasMoreThanKelements(rowCount, colCount, k)) {
				break;
			}
			
			rotateRing(matrix, i, j, rowCount, colCount, k);
		}
	}

	private void rotateRing(int [][] matrix, int topLeftRowIndex, int topLeftColIndex, int rowCount, int colCount, int k) {
		int [] tempArr = new int[getRinglength(rowCount, colCount)];
		int index =0;
		
		// left top to right top
		for(int j= topLeftColIndex; j <= topLeftColIndex + colCount -1; j++) {
			tempArr[index] = matrix[topLeftRowIndex][j];
			index++;
		}
		
		// right top to right bottom
		for(int i= topLeftRowIndex +1; i <= topLeftRowIndex+ rowCount -1; i++) {
			tempArr[index] = matrix [i] [topLeftColIndex + colCount -1];
			index++;
		}
		
		// right bottom to left bottom
		for(int j=topLeftColIndex + colCount -1; j >= topLeftColIndex ; j--) {
			tempArr[index] = matrix [topLeftRowIndex + rowCount -1] [j];
			index++;
		}
		
		// left bottom to left top
		for(int i= topLeftRowIndex + rowCount -1; i > topLeftRowIndex; i--) {
			tempArr[index] = matrix [i] [topLeftColIndex];
			index++;
		}
		
		rotateTempArrByK(tempArr, k);
		index=0;
		
		for(int j= topLeftColIndex; j <= topLeftColIndex + colCount -1; j++) {
			matrix[topLeftRowIndex][j] = tempArr[index];
			index++;
		}
		
		// right top to right bottom
		for(int i= topLeftRowIndex +1; i <= topLeftRowIndex+ rowCount -1; i++) {
			matrix [i] [topLeftColIndex + colCount -1]= tempArr[index];
			index++;
		}
		
		// right bottom to left bottom
		for(int j=topLeftColIndex + colCount -1; j >= topLeftColIndex ; j--) {
			matrix [topLeftRowIndex + rowCount -1] [j] = tempArr[index];
			index++;
		}
		
		// left bottom to left top
		for(int i= topLeftRowIndex + rowCount -1; i > topLeftRowIndex; i--) {
			matrix [i] [topLeftColIndex] = tempArr[index];
			index++;
		}
	}
	
	private void rotateTempArrByK(int[] tempArr, int k) {
		int firstRotationDestinationIndex = 0 -k + tempArr.length;
		
		boolean [] visited = new boolean[tempArr.length];
		for(int i=0; i < firstRotationDestinationIndex; i++) {
			if(visited[i]) {
				continue;
			}
			
			int originSwapIndex = i;
			int nextSwapIndex = i -k >= 0 ? i-k : i-k + tempArr.length;
			
			int temp1 = tempArr[originSwapIndex];
			int temp2 = tempArr[nextSwapIndex];
			while(!visited[nextSwapIndex]) {
				tempArr[nextSwapIndex] = temp1;
				visited[nextSwapIndex] = true;
				originSwapIndex = nextSwapIndex;
				nextSwapIndex = nextSwapIndex -k >= 0 ? nextSwapIndex-k : nextSwapIndex-k + tempArr.length;
				temp1 = temp2;
				temp2 = tempArr[nextSwapIndex];
			}
		}
	}

	private int getRinglength(int rowCount, int colCount) {
		if(rowCount == 1) {
			return colCount;
		} else if(colCount == 1) {
			return rowCount;
		} else {
			return 2*rowCount + 2 *colCount -4;
		}
	}

	private boolean checkIfRingHasMoreThanKelements(int rowCount, int colCount, int k) {
		if(getRinglength(rowCount,colCount) >=k) {
			return true;
		}
		
		return false;
	}
}
