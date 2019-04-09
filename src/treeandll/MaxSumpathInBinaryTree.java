package treeandll;

public class MaxSumpathInBinaryTree {
	
	/*Your submission failed for the following input:
		9 -200 -100 -1 -300 -400 -1 -1 -1 -1
		There are 1 lines in the input

		Line 1 ( Corresponds to arg 1 ) : First number represents the number of integers in input line. Then follows serialized representation of the tree. The serialization of a binary tree follows a level order description of left and right child of nodes, where -1 signifies a NULL child.
		    For example,
		         1
		        / \ 
		       2    3
		          /
		        4
		         \
		          5
		    will have representation as {1 2 3 -1 -1 4 -1 -1 5 -1 -1}
		    The first integer on the line indicates the number of integers to follow in the serialized representation of the tree.

		Your function returned the following :
		2147483348
		The expected returned value :
		-100
*/

	public static void main(String [] args) {
		MaxSumpathInBinaryTree testClass = new MaxSumpathInBinaryTree();
		TreeNode root = new TreeNode(-8);
		root.left = new TreeNode(9);
		root.left.left = new TreeNode(-3);
		root.left.right = new TreeNode(-1);
		root.right = new TreeNode(12);
		
		System.out.println(testClass.maxPathSum(root)); 
		
		/*System.out.println(Integer.MIN_VALUE);
		
		int [] sumSoFar = new int[2];
		 sumSoFar[0] = Integer.MIN_VALUE;
		 sumSoFar[1] = Integer.MIN_VALUE;
		System.out.println(sumSoFar[1]);
		
		int afterAssigningTOint = Integer.MIN_VALUE + (-100);
		System.out.println(afterAssigningTOint);*/
	}
	
	 public int maxPathSum(TreeNode A) {
		 if(A == null) {
			 return 0;
		 }
		 
		 int [] sumSoFar = new int[2]; 
		 sumSoFar[0] = Integer.MIN_VALUE;
		 sumSoFar[1] = Integer.MIN_VALUE;
		 return maxPathSum2(A, sumSoFar)[1];
	 } 
	 
	 public int[] maxPathSum2(TreeNode A, int [] sumSoFar) {
		 if(A == null) {
			 return sumSoFar;
		 }
		 
		 if(A.left == null && A.right == null) {
			 sumSoFar[0] = A.val;
			 sumSoFar[1] = A.val;
			 
			 return sumSoFar;
		 }
		 
		 int leftSum = Integer.MIN_VALUE;
		 int rightSum = Integer.MIN_VALUE;
		 
		 int maxValue = A.val;
		 
		 if(A.left != null) {
			 leftSum = maxPathSum2(A.left, sumSoFar)[0];
			 
			 if(maxValue < A.val + leftSum)  {
				 maxValue = A.val + leftSum;
			 }
		 }
		 
		 if(A.right != null) {
			 rightSum = maxPathSum2(A.right, sumSoFar)[0];
			 
			 if(maxValue < A.val + rightSum)  {
				 maxValue = A.val + rightSum;
			 }
		 }
		  
		 sumSoFar[0] = maxValue;
		 
		 if(A.left != null && A.right != null) {
			 if(sumSoFar[1] < (A.val + leftSum + rightSum) ) {
				 sumSoFar[1] = A.val + leftSum + rightSum;
			 }
		 }
		 
		 if(sumSoFar[0] > sumSoFar[1]) {
			 sumSoFar[1] = sumSoFar[0];
		 }
		 
		 return sumSoFar;
	 }
}

