package arrays;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumOfMinForEveryWindowSize {
	
	/*Input:  arr[] = {10, 20, 30, 50, 10, 70, 30}
	Output:         70, 30, 20, 10, 10, 10, 10*/
	
	
	public static void main(String [] args) {
		MaximumOfMinForEveryWindowSize testClass = new MaximumOfMinForEveryWindowSize();
		int [] arr = {10, 20, 30, 50, 10, 70, 30};
		int [] output = testClass.maxOfMinWindows(arr);
		
		int index =0;
		while(index < output.length) {
			System.out.print(output[index] + " ");
			index++;
		}
	}
	
	// mistake : Didn't thought through how to write loop also didn't validated with test case
	private int[] maxOfMinWindows(int [] arr) {
		int [] output =  new int[arr.length];
		
		for(int k=1; k<=arr.length; k++) {
			int maxIndexInWindowSizeK = -1;
			LinkedList<Integer> Q = new LinkedList<Integer>();
			int qSize = 0;
			
			int movingIndex =0;
			
			while(movingIndex < k) {
				while(!Q.isEmpty() && arr[Q.peek()] >= arr[movingIndex]) {
					Q.removeLast();
					qSize--;
				}
				
				Q.addLast(movingIndex);
				qSize++;
				movingIndex++;
			}
			
			maxIndexInWindowSizeK = Q.getFirst();
			
			for(; movingIndex< arr.length; movingIndex++) {
				
				if(qSize ==k) {
					Q.removeFirst();
					qSize--;	
					
				} 
				
				while(!Q.isEmpty() && arr[Q.peek()] >= arr[movingIndex]) {
					Q.removeLast();
					qSize--;
				}
				
				Q.addLast(movingIndex);
				qSize++;
				
				// 70, 40, 50, 40, 70, 20
				if(arr[Q.getFirst()] > arr[maxIndexInWindowSizeK]){
					maxIndexInWindowSizeK = Q.getFirst();
				}
			}
			
			output[k-1] = arr[maxIndexInWindowSizeK];
		}
		
		return output;
	}

}
