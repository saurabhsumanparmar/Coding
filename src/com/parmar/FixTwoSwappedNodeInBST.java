package com.parmar;

public class FixTwoSwappedNodeInBST {
	
	public static void main(String [] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(10);
		root.left.right = new TreeNode(8);
		root.right = new TreeNode(15);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(20);
		root.right.right.left = new TreeNode(18);
		root.right.right.left.left = new TreeNode(17);
		root.right.right.left.right = new TreeNode(19);
		
		inOrderTraversal(root);
		
		System.out.println("After Fixing ..");
		
		fixNodes(root);
		
		inOrderTraversal(root);
	}
	
	public static void inOrderTraversal(TreeNode root) {
		if(root== null) return;
		
		if(root.left != null) {
			inOrderTraversal(root.left);
		}
		
		System.out.print(root.val + " ");
		
		if(root.right != null) {
			inOrderTraversal(root.right);
		}
	}
	
	public static void fixNodes(TreeNode root) {
		TreeNode [] faultyNodes = new TreeNode[3]; // why three : in last first next
		fixNodes(root, null, faultyNodes);
		
		if(faultyNodes[1] != null) {
			int temp = faultyNodes[1].val;
			faultyNodes[1].val = faultyNodes[0].val;
			faultyNodes[0].val = temp;
		} else {
			int temp = faultyNodes[2].val;
			faultyNodes[2].val = faultyNodes[0].val;
			faultyNodes[0].val = temp;
		}
	}
	
	public static TreeNode fixNodes(TreeNode root, TreeNode prev, TreeNode [] faultyNodes) {
		
		if(root.left != null) {
			prev = fixNodes(root.left, prev, faultyNodes);
		}
		
		if(prev != null && prev.val > root.val) {
			if(faultyNodes[0] ==null) {
				faultyNodes[0] = prev;
				faultyNodes[2] = root;
			} else {
				faultyNodes[1] = root;
			}
		}
		
		if(root.right != null) {
			prev = fixNodes(root.right, root, faultyNodes);
		}
		
		return root.right != null ? prev : root;
	}
}
