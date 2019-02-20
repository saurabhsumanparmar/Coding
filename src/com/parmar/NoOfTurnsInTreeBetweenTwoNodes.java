package com.parmar;

public class NoOfTurnsInTreeBetweenTwoNodes {

	/* traversalData []
	 * 0 : no of nodes Found ..values 0, 1, 2
	 * 1 : first or second to be found ..values 1, 2
	 * 2 : previous turn ...values -1 for no turn yet, 1 if left , 2 if right
	 * 3 : previous to previous turn ...values 1 if left , 2 if right ....... seems redundant ..let's keep it
	 * 4 : no of turns
	 * */
	
	
	// yet to be finished
	private int[] noOfTurns(TreeNode root, int first, int second, int [] traversalData) {
		int [] returnData = new int[4];
		
		if(traversalData[0] == 1 && ((traversalData[1] == 1 && root.val == first ) || 
				(traversalData[1] == 2 && root.val == second))) {
			returnData[0] = 2;
			
			// change required
			if(traversalData[2] != traversalData[3]) {
				traversalData[4]++;
			}
			
			returnData[4] = traversalData[4];
			
			return returnData;
		} else if(traversalData[0] == 1 ) {
			int [] left;
			int [] right;
			
			if(root.left != null) {
				if(traversalData[2] == 2) {
					traversalData[4] += 1;
				}
				
				traversalData[2] = 1;
				left = noOfTurns(root.left, first, second, traversalData);
				
				if(left[0] ==2) {
					returnData[0] = 2;
					returnData[4] = left[4];
					
					return left;
				}
			}
			
			
			
			if(root.right != null) {
				if(traversalData[2] == 1) {
					traversalData[4] += 1;
				} 
				
				traversalData[2] = 2;
				right = noOfTurns(root.right, first, second, traversalData);
				
				if(right[0] ==2) {
					returnData[0] =2;
					returnData[4] = right[4];
					
					return right;
				}
			}
			
			returnData[0]= 1;
			returnData[1]= root.val == first ? 2: 1;
			returnData[2] =-1;
			
			return returnData;
		} else if(traversalData[0] == 0 && (root.val == first || root.val == second)) {
			traversalData[0] = 1;
			
			int [] left;
			int [] right;
			
			if(root.left != null) {
				traversalData[2] = 1;
				traversalData[3] = -1;
				left = noOfTurns(root.left, first, second, traversalData);
				
				if(left[0] ==2) {
					returnData[0] = 2;
					returnData[4] = left[4];
					
					return left;
				}
			}
			
			if(root.right != null) {
				traversalData[2] = 2;
				traversalData[3] = -1;
				right = noOfTurns(root.right, first, second, traversalData);
				
				if(right[0] ==2) {
					returnData[0] =2;
					returnData[4] = right[4];
					
					return right;
				}
			}
			
			returnData[0]= 1;
			returnData[1]= root.val == first ? 2: 1;
			returnData[2] =-1;
			
			return returnData;
		} else {
			
		}
		
		return returnData;
	}
}
