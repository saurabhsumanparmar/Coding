package com.parmar;

import java.util.ArrayList;

public class OrderOfpeopleHeight_SegmentTree {
	
	/* Heights: 5 3 2 6 1 4
	InFronts: 0 1 2 0 3 2
	
	Output : 
	actual order is: 5 3 2 1 6 4 */
	
	/* No of mistake observed in the code
	1. less than instead of greater than in quicksort function
	2. Infine loop in quicksort function -> uneccessary while loop
	3. Typos in swap statements
	
	 */
	
	public static void main(String [] args) {
		OrderOfpeopleHeight_SegmentTree testClass = new OrderOfpeopleHeight_SegmentTree();
		ArrayList<Integer> A  = new ArrayList<Integer>();
		A.add(5);
		A.add(3);
		A.add(2);
		A.add(6);
		A.add(1);
		A.add(4);
		
		ArrayList<Integer> B  = new ArrayList<Integer>();
		B.add(0);
		B.add(1);
		B.add(2);
		B.add(0);
		B.add(3);
		B.add(2);
		
		System.out.println(testClass.order(A, B));		  
	}
	
	class IntervalNode {
		int start;
		int end;
		int count;
		IntervalNode left;
		IntervalNode right;
		IntervalNode(int start, int end) {
			count = 0;
			this.start=start;
			this.end=end;
			left=null;
			right =null;
	  }
	}
	
	
	public ArrayList<Integer> order(ArrayList<Integer> A, ArrayList<Integer> B) {
		int[] output = new int[A.size()];
		
		sortListAAndUpdateBAsPerPeopleHeight(A, B);
		
		IntervalNode intervalTree = buildIntervalTree(0, A.size() -1);
		
		ArrayList<Integer> output2 = new ArrayList<Integer>();
		
		
		for(int i=0; i<A.size(); i++) {
			int index = updateAndGetPosition(intervalTree, B.get(i)+1);
			output[index] = A.get(i);
		}
		
		for(int i=0; i< A.size(); i++) {
			output2.add(output[i]);
		}
		
		return output2;
    }

	private int updateAndGetPosition(IntervalNode root, Integer position) {
		if(root.left == null && root.right == null) {
			root.count =1;
			return root.start;
		}
		
		if(root.left == null) {
			int value =  updateAndGetPosition(root.right, position);
			root.count = root.count+1;
			return value;
		}
		
		int noOfOpenPostionInLeft = (root.left.end - root.left.start + 1) -root.left.count;
		
		if(noOfOpenPostionInLeft < position) {
			int value =  updateAndGetPosition(root.right, position -noOfOpenPostionInLeft);
			root.count = root.count+1;
			return value;
		} else {
			int value =  updateAndGetPosition(root.left, position);
			root.count = root.count+1;
			return value;
		}
	}

	private IntervalNode buildIntervalTree(int i, int j) {
		if(i==j) {
			return new IntervalNode(i, j);
		}
		
		int mid = (i + j) /2;
		
		IntervalNode left = buildIntervalTree(i, mid);
		IntervalNode right = buildIntervalTree(mid+1, j);
		
		IntervalNode root = new IntervalNode(i,j);
		root.left = left;
		root.right = right;
		
		return root;
	}

	private void sortListAAndUpdateBAsPerPeopleHeight(ArrayList<Integer> a, ArrayList<Integer> b) {
		quickSort(a, 0, a.size() -1, b);
	}
	
	private void quickSort(ArrayList<Integer> a, int start, int end, ArrayList<Integer> b) {
		if(start == end) {
			return;
		}
		
		
			int partition = findPivot(a, start, end, b);
			
			if((partition-1) > start) {
				quickSort(a, start, partition-1, b);
			}
			
			if((partition + 1) < end) {
				quickSort(a, partition+1, end, b);;
			}
		
	}

	private int findPivot(ArrayList<Integer> a, int start, int end, ArrayList<Integer> b) {
		if(start == end) {
			return start;
		}
		
		int pivot = end;
		
		int left = start;
		int right = end;
		
		while(left< right) {
			while(a.get(left) < a.get(pivot) && left < end) {
				left ++;
			}
			
			while(a.get(right) >= a.get(pivot) && right > start) {
				right --;
			}
			
			if(left < right) {
				int temp = a.get(right);
				a.set(right, a.get(left));
				a.set(left, temp);
				
				
				int temp2 = b.get(right);
				b.set(right, b.get(left));
				b.set(left, temp2);
			}
		}
		
		int temp = a.get(left);
		a.set(left, a.get(pivot));
		a.set(pivot, temp);
		
		int temp2 = b.get(left);
		b.set(left, b.get(pivot));
		b.set(pivot, temp2);
		
		return left;
	}
}
