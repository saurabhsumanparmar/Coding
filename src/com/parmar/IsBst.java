package com.parmar;

public class IsBst {
	
	public int[] isBst(TreeNode root, int valueFromParent) {
		int [] returnArr = new int[2];
		
		if(root ==null) {
			returnArr[0] =1;
		}
		
		int [] left =null;
		int [] right = null;
		//int [] passValue;
		
		if(valueFromParent > root.val) {
			returnArr[0] =1;
			return returnArr;
		}
		
		if(root.left != null) {
			left = isBst(root.left, valueFromParent);
			
			if(left[0] !=1 || left[1] > root.val) {
				returnArr[0] = 0;
				return returnArr;
			}
		}
		
		if(root.right != null) {
			right = isBst(root.right, root.val);
			
			if(right[0] !=1 || right[1] < root.val) {
				returnArr[0] = 0;
				return returnArr;
			}
		}
		
		
		if(right != null) {
			returnArr[1] = right[1];
		} else {
			returnArr[1] = root.val;
		}
		
		returnArr[0] = 1;
		
		return returnArr;
		
	}

}
