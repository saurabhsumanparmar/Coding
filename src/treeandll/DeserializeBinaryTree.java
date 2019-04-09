package treeandll;

public class DeserializeBinaryTree {
	public static void main(String [] args) {
		int [] inorder = {4, 6, 5, 2, 1, 7 , 9, 3, 8};
		int [] preOrder = {1, 2, 4, 5, 6, 3, 7, 9, 8};
		
		TreeNode root = deserialize(inorder, 0, 8, preOrder, 0, 8);
		preOrder(root);
	}
	
	// Errors in the code : Seems no error ..there was problem in the input .. WTF :):):)
	static TreeNode deserialize(int [] inorder, int inorderStartIndex, int inorderEndIndex,
			int [] preorder, int preorderStartIndex, int preorderEndIndex) {
		TreeNode root = null;
		
		if(inorderEndIndex == inorderStartIndex) {
			return new TreeNode(inorder[inorderStartIndex]);
		}
		
		root = new TreeNode(preorder[preorderStartIndex]);
		
		int i=inorderStartIndex;
		while(i <= inorderEndIndex && inorder[i] != preorder[preorderStartIndex]) {
			i++;
		}
		
		if(i != inorderStartIndex) {
			root.left= deserialize(inorder, inorderStartIndex, i-1, preorder, preorderStartIndex+1,
					preorderStartIndex + (i- inorderStartIndex));
		}
		
		if(i != inorderEndIndex) {
			root.right = deserialize(inorder, i+1, inorderEndIndex, preorder, preorderStartIndex + (i- inorderStartIndex) +1,
					preorderEndIndex);
		}
		
		return root;
	}
	
	static void preOrder(TreeNode node) {
		if(node ==null) {
			return;
		}
		
		System.out.print(node.val);
		
		if(node.left != null) {
			preOrder(node.left);
		}
		
		if(node.right != null) {
			preOrder(node.right);
		}
	}
}
