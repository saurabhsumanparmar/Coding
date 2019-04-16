package graph;

import java.util.ArrayList;

// https://www.geeksforgeeks.org/minimum-steps-reach-target-knight/
public class MinimumStepsToReachTargetByAKnight {
	public static void main(String [] args) {
		int [][] traversed = new int[6][6];
		
		MinimumStepsToReachTargetByAKnight testClazz = new MinimumStepsToReachTargetByAKnight();
		testClazz.stepsRequired(1, 3, 5, 0, traversed);
		
		System.out.println(traversed[5][0]);
		
		
		int [][] traversed2 = new int[6][6];		
		testClazz.stepsRequired(1, 2, 4, 5, traversed2);
		
		System.out.println(traversed2[4][5]);
	}
	
	class Cell {
		int row;
		int col;
		public Cell(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	void stepsRequired(int row, int col, int targetRow, int targetCol, int [][] traversed) {
		if(row == targetRow && col == targetCol) {
			return;
		}
		
		ArrayList<Cell> cellsReachable = getReachableCellFromSource(row, col, traversed);
		for(int i=0; i< cellsReachable.size(); i++) {
			stepsRequired(cellsReachable.get(i).row, cellsReachable.get(i).col, targetRow, targetCol, traversed);
		}
	}

	private ArrayList<Cell> getReachableCellFromSource(int row, int col, int[][] traversed) {
		ArrayList<Cell> output = new ArrayList<Cell>();
		
		int rowLength = traversed.length;
		if(rowLength == 0) {
			return output;
		}
		
		int colLength = traversed[0].length;
		
		int [] dx = {-2, -2, -1, 1, 2, 2, 1, -1};
		int [] dy = {-1, 1, 2, 2, 1, -1, -2, -2};
		
		for(int i=0; i< dx.length ; i++) {
			if((row +dx[i] >=0 && row +dx[i] < rowLength) && (col +dy[i] >=0 && col +dy[i] < colLength)
					&& (traversed[row +dx[i]][col +dy[i]] ==0 || traversed[row][col] + 1 < traversed[row +dx[i]][col +dy[i]]) )  {
				traversed[row +dx[i]][col +dy[i]] =traversed[row][col] + 1;
				output.add(new Cell(row +dx[i], col +dy[i] ));
			}
		}
		
		return output;
	}
}
