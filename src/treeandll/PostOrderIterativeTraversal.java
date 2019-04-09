package treeandll;

import java.util.Stack;

import com.parmar.NodesAtDistanceKInTree;
import com.parmar.TreeNode;

public class PostOrderIterativeTraversal {
	public static void main(String [] args) {
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
		postOrderIterative(root);
	}
	
	static void postOrderIterative(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		while(root != null) {
			TreeNode ptr = root;
			
			while(ptr != null) {
				if(ptr.right != null) {
					stack.push(ptr.right);
				}
				
				stack.push(ptr);
				
				ptr = ptr.left;
			}
			
			TreeNode popedItem = stack.pop();
			if(stack.isEmpty()) {
				System.out.print(popedItem.val);
				break;
			}
			
			while(!stack.isEmpty()) {
				
				if(popedItem.right != stack.peek()) {
					System.out.print(popedItem.val);
				} else {
					root = stack.pop();
					stack.push(popedItem);
					break;
				}
				
				popedItem = stack.pop();
				
				if(stack.isEmpty()) {
					System.out.print(popedItem.val);
					root =null;
				}
			}
		}
	}
}
