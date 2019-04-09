package treeandll;

import java.util.ArrayList;

public class NodesAtDistanceKInTree {
	
	public static void main(String [] args) {
		NodesAtDistanceKInTree testClass = new NodesAtDistanceKInTree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(6);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		root.right.right.left = new TreeNode(8);
		root.right.right.left.left = new TreeNode(10);
		root.right.right.left.right = new TreeNode(9);
		
		ArrayList<Integer> output = new ArrayList<Integer>();
		int sourceValue = 5;
		int distance = 3;
		
		if(root.val == sourceValue) {
			output.add(root.val);
		} else {
			testClass.nodesAtdistanceK(root, sourceValue, -1, output ,distance);
		}
		
		for (int i=0; i<output.size(); i++) {
			System.out.print(output.get(i) + " ");
		}
	}
	
	
	 /* mistakes due to which it was not running
	  * 1. dry run was not proper
	  * 2. distance was wronged passed , need to introduce additional parameter
	  * 3. 
	  * */
	private int nodesAtdistanceK(TreeNode root, int sourceValue, int distance, ArrayList<Integer> lst, int distanceWhenMatched) {
		
		if(distance ==1) {
			lst.add(root.val);
			return -1;
		}
		
		if(distance !=-1) {
			if(root.left !=null) {
				nodesAtdistanceK(root.left, sourceValue, distance-1, lst, distanceWhenMatched);
			}
			
			if(root.right !=null) {
				nodesAtdistanceK(root.right, sourceValue, distance-1, lst, distanceWhenMatched);
			}
		}
		
		if(distance ==-1) {
			int left =-1;
			int right =-1;
			
			if(sourceValue == root.val) {
				if(distanceWhenMatched ==0) {
					lst.add(root.val);
					return -1;
				}
				
				if(root.left !=null) {
					nodesAtdistanceK(root.left, sourceValue, distanceWhenMatched, lst, distanceWhenMatched);
				}
				
				if(root.right !=null) {
					nodesAtdistanceK(root.right, sourceValue, distanceWhenMatched, lst, distanceWhenMatched);
				}
				
				// returning parent a distance
				return distanceWhenMatched;
			} else {
				if(root.left != null) {
					left = nodesAtdistanceK(root.left, sourceValue, distance, lst, distanceWhenMatched);
				}
				
				if(left != -1) {
					if(left ==1) {
						lst.add(root.val);
						return -1;
					} else {
						distance = left-1;
					}
				}
				
				
				if(root.right !=null) {
					right = nodesAtdistanceK(root.right, sourceValue,  distance, lst, distanceWhenMatched);
				}
				
				if(right != -1) {
					if(right ==1) {
						lst.add(root.val);
						return -1;
					} else if(root.left != null) {
						distance = right-1;
						nodesAtdistanceK(root.left, sourceValue, distance, lst, distanceWhenMatched);
					}
				}
				
				// returning parent a distance
				return distance;
			}
		}
		
		return -1;
	}
}
