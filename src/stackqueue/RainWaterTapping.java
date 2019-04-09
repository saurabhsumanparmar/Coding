package stackqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://www.interviewbit.com/problems/rain-water-trapped/
public class RainWaterTapping {
	public static void main(String [] args) {
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(0); lst.add(1); lst.add(0); lst.add(2); lst.add(1); lst.add(0); lst.add(1); lst.add(3); lst.add(2);
		lst.add(1); lst.add(2); lst.add(1);
		// [0,1,0,2,1,0,1,3,2,1,2,1]
		
		System.out.println(waterTapped(lst));
	}
	
	public static int waterTapped(final List<Integer> A) {
		Stack<Integer> indexStack = new Stack<Integer>();
		int waterTapped = 0;
		
		if(A.size() ==0) {
			return waterTapped;
		}
		
		int index=0;
		while(A.get(index) ==0 && index < A.size()) {
			index++;
		}
		
		indexStack.push(index);
		
		for(int i=index +1; i < A.size(); i++) {
			if(A.get(i) ==0) {
				continue;
			}
			
			if(A.get(i) < A.get(indexStack.peek())) {
				waterTapped = waterTapped + A.get(i) * (i-indexStack.peek() -1);
				
			} else {
				int currentIndex = i;
				int popedIndex = indexStack.pop();
				waterTapped = waterTapped + A.get(popedIndex) * (currentIndex-popedIndex -1);
				currentIndex = indexStack .isEmpty() ? -1 : indexStack.peek();
				
				while(currentIndex > 0 && A.get(currentIndex) <= A.get(i)) {
					indexStack.pop();
					waterTapped = waterTapped + (A.get(currentIndex) - A.get(popedIndex))
							* (i- currentIndex -1);
					popedIndex = currentIndex;
					currentIndex = indexStack.isEmpty() ? -1 : indexStack.peek();
				}
				
				if(currentIndex > 0 && A.get(currentIndex) > A.get(i)) {
					waterTapped = waterTapped + (A.get(i) - A.get(popedIndex))
							* (i- currentIndex -1);
				}
			}
			
			indexStack.push(i);
		}
		
		return waterTapped;
	}
}
