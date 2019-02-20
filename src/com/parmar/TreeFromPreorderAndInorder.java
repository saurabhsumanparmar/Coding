package com.parmar;

import java.util.ArrayList;

public class TreeFromPreorderAndInorder {
	
	public TreeNode buildTree(ArrayList<Integer> A, ArrayList<Integer> B) {
		if(A.size() == 0) {
			return null;
		}
		
		if(A.size() ==1) {
			TreeNode root = new TreeNode(A.get(0));
			return root;
		}
		
		TreeNode root = new TreeNode(A.get(0));
		
		int indexInB =0 ;
		for(int i=0; i < B.size(); i++) {
			if(A.get(0) == B.get(i)) {
				indexInB = i;
				break;
			}
		}
		
		if(indexInB != 0) {
			root.left = buildTree(A, 1, 1 + indexInB -1, B, 0, indexInB-1);
		}
		
		if(indexInB != A.size() -1) {
			root.right = buildTree(A, indexInB+1, A.size(), B, indexInB+1, A.size());
		}	
				
		return root;
    }
	
	public TreeNode buildTree(ArrayList<Integer> A, int startA, int endA, ArrayList<Integer> B, int startB, int endB) {
		if(startA ==endA) {
			TreeNode root = new TreeNode(A.get(startA));
			return root;
		}
		
		TreeNode root = new TreeNode(A.get(startA));
		
		int indexInB = startA;
		for(int i=startB; i <= endB ; i++) {
			if(A.get(startA) == B.get(i)) {
				indexInB = i;
				break;
			}
		}
		
		root.left = buildTree(A, startA + 1, startA + 1 + indexInB -1, B, 0, indexInB-1);
		root.right = buildTree(A, startA + 1 + indexInB, endA, B, indexInB+1, endB);
				
		return root;
    }
}


