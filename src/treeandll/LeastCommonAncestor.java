package treeandll;

public class LeastCommonAncestor {
	public int lca(TreeNode A, int B, int C) {

		return 0;
    }
	
	public int [] lca2(TreeNode A, int B, int C) {
		// 0th index contains flag and 1st contains value
		int [] returnValues = new int[2];
		returnValues[0] = -1;
		
		if(A.left == null && A.right == null) {
			if(A.val == B || A.val==C)  {
				returnValues[0] = 1;
				returnValues[1] = A.val;
			} else {
				returnValues[0] = -1;
			}
			
			return returnValues;
		}
		
		int [] left = new int[2];
		left[0] = -1;
		int [] right = new int[2];;
		right[0] = -1;
		//int valuesInSubtree = -1;
		
		if(A.left != null) {
			left = lca2(A.left, B, C);
			
			if(left[0] ==2) {
				returnValues[0] = 2;
				returnValues[1] = left[1];
				return returnValues;
			}
		}
		
		if(A.right != null) {
			right = lca2(A.right, B, C);
			
			if(right[0] ==2) {
				returnValues[0] = 2;
				returnValues[1] = right[1];
				return returnValues;
			}
		}
		
			if(left[0] == 1 && right[0] ==1) {
				returnValues[0] = 2;
				returnValues[1] = A.val;
				return returnValues;
			} else if(left[0] == 1) {
				if((left[1] == B && A.val == C) || (left[1] == C && A.val == B)) {
					returnValues[0] = 2;
					returnValues[1] = A.val;
				}
				
				return left;
				
			} else if(right[0] ==1) {
				if((right[1] == B && A.val == C) || (right[1] == C && A.val == B)) {
					returnValues[0] = 2;
					returnValues[1] = A.val;
				}
				
				return right;
				
			} else if(A.val == C || A.val == B){
				returnValues[0] = 1;
				returnValues[1] = A.val;
				return returnValues;
			}
		
		return returnValues;
    }
}
