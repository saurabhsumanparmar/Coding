package com.parmar;

public class MaxCircularSumSubarray {
	
	
	// Write down the mistakes
	
	/* Mistakes : 
	 * Few major logical error while moving the index in circular fashion
	 * */
	
	public static void main(String [] args) {
		MaxCircularSumSubarray testClass = new MaxCircularSumSubarray();
		
		//int [] arr = { 9, 8, -25, 2, 4, -5};
		//int [] arr = { 9, 8, -3, 2, 4, -5};
		//int [] arr = { 9, 8, 12, 2, 4, 1};
		//int [] arr = { -9, -8, -2, 6, -4, -5};
		int [] arr = { -9, -8, -2, -1, -4, -5};
		
		System.out.println("Max circular sum is :" + testClass.maxCircularSum(arr));
	}
	
	private int maxCircularSum(int [] arr) {
		if(arr.length == 0) return 0;
		
		boolean endCrossed = false;
		int start = 0;
		
		int maxGlobalSum = arr[0];
		int maxLocalSum = arr[0];
		
		int movingIndex=1;
		while(!endCrossed && start != movingIndex) {
			int tempSum = maxLocalSum + arr[movingIndex];
			if(arr[movingIndex] > tempSum && !endCrossed) {
				start = movingIndex;
				maxLocalSum = arr[movingIndex];
			} else {	
				maxLocalSum = tempSum;
			}
			
			if(maxLocalSum > maxGlobalSum) {
				maxGlobalSum = maxLocalSum;
			}
			
			if(movingIndex == arr.length-1) {
				movingIndex =0;
				endCrossed = true;
			} else {
				movingIndex++;
			}
			
			if(maxLocalSum < 0 && endCrossed) {
				break;
			}
		}
		
		return maxGlobalSum;
	}
}
