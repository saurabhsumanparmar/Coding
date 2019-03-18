package stackqueue;

import java.util.Stack;

// https://www.hackerrank.com/challenges/poisonous-plants/problem
public class PoisonousPlant {
	public static void main(String [] args) {
		int [] arr1 = {3, 6, 2, 7, 5};
		System.out.println(maxNoDaysAfterWhichPlantsStopDying(arr1));
		
		int [] arr2 = {6, 5, 8, 4, 7, 10, 9};
		System.out.println(maxNoDaysAfterWhichPlantsStopDying(arr2));
		
		int [] arr3 = {1, 2, 3};
		System.out.println(maxNoDaysAfterWhichPlantsStopDying(arr3));
		
		int [] arr4 = {3, 2, 1};
		System.out.println(maxNoDaysAfterWhichPlantsStopDying(arr4));
		
		// Run below test cases ...below ones failing 
		int [] arr5 = {20, 5, 6, 15, 2, 2, 17, 2, 11, 5, 14, 5, 10, 9, 19, 12, 5};
		System.out.println(maxNoDaysAfterWhichPlantsStopDying(arr5)); // Expected 4
		

		
	}
	
	public static int maxNoDaysAfterWhichPlantsStopDying(int [] arr) {
		
		int maxDays =0;
		
		for(int i=0; i < arr.length;) {
			Stack<Integer> valueStack = new Stack<Integer>();
			Stack<Integer> noOfDaysStack = new Stack<Integer>();
			
			valueStack.push(arr[i]);
			noOfDaysStack.push(-1);
			
			i++;
			
			while(valueStack.size() > 0 && i<arr.length) {
				if(arr[i] > valueStack.peek()) {
					if(valueStack.size() == 1) { // when it's first element
						valueStack.push(arr[i]);
						noOfDaysStack.push(1);
						
						maxDays = maxDays == 0 ? 1: maxDays;
					} else if(noOfDaysStack.peek() == 1) {
						valueStack.pop();
						noOfDaysStack.pop();
						
						valueStack.push(arr[i]);
						noOfDaysStack.push(1);
					} else {
						valueStack.push(arr[i]);
						noOfDaysStack.push(1);
					}
					
					i++;
				} else {
					if(valueStack.size() == 1) {
						//i++;
						break;
					} else {
						int localMax = noOfDaysStack.peek();
						while(valueStack.size() > 1 && arr[i] < valueStack.peek()) {
						
							localMax = noOfDaysStack.pop();
							
							valueStack.pop();
						}
						
						if(valueStack.size() > 1) {
							if(localMax+1 == noOfDaysStack.peek()) {
								noOfDaysStack.pop();
								valueStack.pop();
							}
							
							valueStack.push(arr[i]);
							noOfDaysStack.push(localMax+1);
							
							maxDays = maxDays <  localMax+1 ? localMax+1: maxDays;
						} else {
							if(valueStack.peek() > arr[i]) {
								//i++;
								break;
							} else {
								valueStack.push(arr[i]);
								noOfDaysStack.push(localMax+1);
								
								maxDays = maxDays <  (localMax+1) ? (localMax+1): maxDays;
							}
						}
						
						i++;				
					}
				}
			}
			
		}
		
		return maxDays;
	}
}
