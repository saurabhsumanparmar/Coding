package com.parmar;

public class MinCostPathInMatrixWithUPRightLeftDownAllowed {
	public static void main(String [] args) {
		MinCostPathInMatrixWithUPRightLeftDownAllowed testClazz = new MinCostPathInMatrixWithUPRightLeftDownAllowed();
		int [][] matrix = { {31, 100, 65, 12, 18},
					 {10, 13, 47, 157, 6},
					 {100, 113, 174, 11, 33},
					 {88, 124, 41, 20, 140},
					 {99, 32, 111, 41, 20}};
		System.out.println(testClazz.minCostPath(matrix));
	}
	
	class Node {
		int value;
		int row;
		int col;
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getCol() {
			return col;
		}
		public void setCol(int col) {
			this.col = col;
		}
		public Node(int value, int row, int col) {
			super();
			this.value = value;
			this.row = row;
			this.col = col;
		}
	}
	
	class MinHeap {
		Node [] arr;
		int maxSize;
		int size;
		
		public int getSize() {
			return size;
		}

		public MinHeap(int maxSize) {
			arr = new Node[maxSize];
			size = 0;
		}
		
		public int getMaxSize() {
			return maxSize;
		}
		
		public int insert(Node value, DistNode [][] distance) {
			arr[size] = value;
			distance[arr[size].row][arr[size].col].index = size;
			
			size++;
			
			int index = propageToRoot(size-1, value, distance);
			return index;
		}
		
		private int propageToRoot(int i, Node node, DistNode [][] distance) {
			if(i == 0) {
				return 0;
			}
			
			int parent;
			if(i % 2 == 0) {
				parent = (i-2)/2;
			} else {
				parent = (i-1)/2;
			}
			
			Node parentNode = arr[parent];
			if(parentNode.value > node.value) {
				Node temp = parentNode;
				arr[parent] = node;
				distance[arr[parent].row][arr[parent].col].index = parent;
				arr[i] = temp;
				distance[arr[i].row][arr[i].col].index = i;
				
				return propageToRoot(parent, node, distance);
			} else {
				return i;
			}
		}

		public Node getMin() {
			return arr[0];
		}
		
		public Node dequeue(DistNode [][] distance) {
			Node returnValue = arr[0];
			arr[0] = arr[size-1];
			distance[arr[0].row][arr[0].col].index = 0;
			
			size--;
			
			heapify(0, distance);
			
			return returnValue;
		}

		private void heapify(int i, DistNode [][] distance) {
			int left = 2*i +1;
			int right = 2*i +2;
			
			int min = i;
			
			if(left < size && arr[left].value < arr[min].value) {
				min =left;
			}
			
			if(right < size && arr[right].value < arr[min].value) {
				min = right;
			}
			
			if(min != i) {
				Node temp = arr[i];
				arr[i] =arr[min];
				distance[arr[i].row][arr[i].col].index = i;
				
				arr[min] = temp;
				distance[arr[min].row][arr[min].col].index = min;
				
				heapify(min, distance);
				
			}
		}

		// Handles only decrement in value
		public int update(int index, int value, DistNode [][] distance) {
			arr[index].value = value;
			
			return propageToRoot(index, arr[index], distance);
		}
	}
	
	class DistNode {
		int value;
		int index;
		
		DistNode (int value) {
			this.value = value;
			index =-1;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public int getIndex() {
			return index;
		}
		
		public void setIndex(int index) {
			this.index = index;
		}
	}
	
	
	public int minCostPath(int [][] matrix) {
		int row = matrix.length;
		if(row == 0) {
			return 0;
		}
		
		int col = matrix[0].length;
		boolean [][] processed = new boolean[row][col];
		DistNode [][] distance = new DistNode[row][col];
		
		// Initialise distance to -1
		
		for(int i=0; i<row; i++) {
			for (int j=0; j < col; j++) {
				distance[i][j] = new DistNode(-1);
			}
		}	
		
		MinHeap heap = new MinHeap(row*col);
		
		distance[0][0].index = heap.insert(new Node(matrix[0][0], 0, 0), distance);
		distance[0][0].value = matrix[0][0];
		processed[0][0] = true;
		
		while(heap.getSize() > 0) {
			Node node = heap.dequeue(distance);
			int indexI = node.getRow();
			int indexJ = node.getCol();
			int distanceSofar = node.value;
			
			if(indexI == row-1 && indexJ== col-1) {
				return distanceSofar;
			}
			
			int neighbourRow [] = { -1, 0, 1, 0};
			int neighbourCol [] = { 0, 1, 0, -1};
			
			for(int i=0; i< neighbourRow.length; i++) {
				int tempi = indexI + neighbourRow[i];
				int tempJ = indexJ+ neighbourCol[i] ;
				
				// check if the value is inside the boundary and not processed
				if((0 <= tempi && tempi < row) && (0<= tempJ && tempJ < col) &&
						!processed[tempi][tempJ]) {
					
					if(distance[tempi][tempJ].value == -1) {
						distance[tempi][tempJ].value = distanceSofar + matrix[tempi][tempJ];
						
						if(tempi == row-1 && tempJ== col-1) {
							return distance[tempi][tempJ].value;
						}
						
						// insert in heap
						heap.insert(new Node(matrix[tempi][tempJ], tempi, tempJ), distance);
					} else if(distance[tempi][tempJ].value > distanceSofar + matrix[tempi][tempJ]) {
						distance[tempi][tempJ].value = distance[indexI][indexJ].value + matrix[tempi][tempJ];
						
						// update node in heap with new distance ...basically decrease value in heap
						distance[tempi][tempJ].index = heap.update(distance[tempi][tempJ].index, distance[tempi][tempJ].value, distance);
					}
				}
			}
		}
		
		return distance[row-1][col-1].value;
	}
}
