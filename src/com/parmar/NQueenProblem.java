package com.parmar;

import java.util.ArrayList;

public class NQueenProblem {
	public static void main(String [] args) {
		ArrayList<int []> output = new ArrayList<>();
		int[] posArr = new int[8];
		
		NQueenProblem testClass = new NQueenProblem();
		testClass.queenPositions(posArr, 0, output);
		
		System.out.println("No of arrangements:" + output.size());
		for(int i=0; i < output.size(); i++) {
			System.out.println();
			for(int j=0; j < output.size(); j++) {
				System.out.print(output.get(i)[j] +  " ");
			}
		}
	}
	
	public void queenPositions(int [] posArr, int currentIndex, ArrayList<int []> output) {
		for(int i=0; i<posArr.length; i++) {
			boolean isIOk = checkIfIIsValidRowNumber(posArr, currentIndex, i);
			
			if(isIOk) {
				
				if(currentIndex == posArr.length-1) {
					addThisArrangementToOutput(posArr, currentIndex, output);
				} else {
					posArr[currentIndex] = i;
					queenPositions(posArr, currentIndex++, output);
				}
				
			}
		}
	}

	private void addThisArrangementToOutput(int[] posArr, int currentIndex, ArrayList<int []> output) {
		int [] arr = new int[posArr.length];
		for(int i=0; i<currentIndex; i++) {
			arr[i] = posArr[i];
		}
		
		arr[posArr.length -1] = currentIndex;
		
		output.add(posArr);
	}

	private boolean checkIfIIsValidRowNumber(int[] posArr, int currentIndex, int rowNumber) {
		for(int i=0; i<currentIndex; i++) {
			if(posArr[i] == rowNumber) {
				return false;
			}
			
			int xDiff = posArr[i] - rowNumber;
			int yDiff = i - currentIndex;
			
			if((xDiff + yDiff) == 0) {
				return false;
			} else if(xDiff == yDiff) {
				return false;
			}
		}
		
		return true;
	}
}
