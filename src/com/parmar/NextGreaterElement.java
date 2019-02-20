package com.parmar;

import java.util.ArrayList;
import java.util.Stack;

public class NextGreaterElement {
	
	public static void main(String [] args) {
		 ArrayList<Integer> input = new ArrayList<Integer>();
		 //input.add(4);
		 input.add(3);
		 input.add(2);
		 input.add(1);
		 //input.add(8);
		
		 NextGreaterElement testClass = new NextGreaterElement();
		 System.out.println(testClass.nextGreater(input));
	}
	
	public ArrayList<Integer> nextGreater(ArrayList<Integer> A) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		Stack<Integer> stack = new Stack<Integer>();
		
		int i=A.size() -1;
		while(i>= 0) {
			while(!stack.isEmpty() && stack.peek() < A.get(i)) {
				stack.pop();
			}
			
			if(stack.isEmpty()) {
				output.add(-1);
			} else {
				output.add(stack.peek());
			}
			
			stack.push(A.get(i));
			i--;
		}
		
		for(int k=0, j = output.size() -1; k <=j; k++, j--) {
			int temp = output.get(k);
			output.set(k, output.get(j));
			output.set(j, temp);
		}
		
		return output;
    }
}
