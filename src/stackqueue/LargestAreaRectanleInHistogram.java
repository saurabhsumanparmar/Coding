package stackqueue;

import java.util.ArrayList;
import java.util.Stack;

// [2,1,5,6,2,3]

public class LargestAreaRectanleInHistogram {
	public int largestRectangleArea(ArrayList<Integer> A) {
		Stack<Integer> stack = new Stack<Integer>();
		int largestArea = 0;
		
		int count = 0;
		while(count < A.size()) {
			
			if(stack.isEmpty()  || stack.peek() <= A.get(count)) {
				stack.push(count);
				count++;
			} else {
				while(A.get(count) > stack.peek()) {
					int localArea = (count - stack.peek()) * A.get(stack.peek());
					
					if(localArea > largestArea) {
						largestArea = localArea;
					}
					stack.pop();
				}
			}
		}
		
		if(!stack.isEmpty()) {
			int currentIndex = stack.peek() + 1;
			
			while (!stack.isEmpty()) {
				int localArea = (currentIndex - stack.peek()) * A.get(stack.peek());
				
				if(localArea > largestArea) {
					largestArea = localArea;
				}
				
				stack.pop();
			}
		}
			
		return largestArea;
    }

	public int largestRectangularArea(ArrayList<Integer> A) {
		int maxArea = 0;
		
		Stack<Integer> stack = new Stack<Integer>();
		
		int i=0;
		int topIndex;
		
		while(i < A.size()) {
			if(stack.isEmpty() || A.get(stack.peek()) < A.get(i)) {
				stack.push(i++);
			} else {
					topIndex = stack.pop();
					
					int localMax = A.get(topIndex) * (stack.isEmpty() ? i :  i - topIndex);
					maxArea = maxArea > localMax ? maxArea : localMax;
			}
		}
		
		while(!stack.isEmpty()) {
			topIndex = stack.pop();
			
			int localMax = A.get(topIndex) * (stack.isEmpty() ? i : i - topIndex);
			maxArea = maxArea > localMax ? maxArea : localMax;
		}
		
		return maxArea;
	}
}
