package com.parmar;

public class FlattenBinaryTree {
	
	public static void main(String [] args) {
		FlattenBinaryTree testClass = new FlattenBinaryTree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		//root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(6);
		root.right.left = new TreeNode(5);
		
		TreeNode output = testClass.flatten(root);
		while (output != null) {
			System.out.println(output.val);
			output = output.right;
		}
	}
	
	public TreeNode flatten(TreeNode a) {
		return a != null ? flattenWrapper(a)[0] : null;
    }
	
	public TreeNode[] flattenWrapper(TreeNode root) {
		TreeNode[] returnValue = new TreeNode[2];
		TreeNode [] left = null;
		TreeNode [] right = null;
		
		if(root.left != null) {
			left = flattenWrapper(root.left);
		}
		
		if(root.right != null) {
			right = flattenWrapper(root.right);
		}
		
		if(left != null) {
			returnValue[0] = root;
			root.right = left[0];
			
			if(right != null) {
				returnValue[1] = right[1];
				left[1].right = right[0];
			} else {
				returnValue[1] = left[1];
			}
		} else if (right != null){
			returnValue[0] = root;
			returnValue[1] = right[1];
			root.right = right[0];
		} 
		
		if(left == null && right==null) {
			returnValue[0] = root;
			returnValue[1] = root;
		}
		
		root.left = null;
		
		return returnValue;
    }
}
