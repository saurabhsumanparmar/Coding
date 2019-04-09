package treeandll;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.parmar.TreeNode;

public class TopViewOfTree {
	
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
				
		topViewOfTree2(root);
		
		System.out.println("");
		
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.left.right = new TreeNode(4);
		root1.left.right.right = new TreeNode(5);
		root1.left.right.right.right = new TreeNode(6);
		root1.right = new TreeNode(3);

		topViewOfTree2(root1);
	}
	
	public static void topViewOfTree2(TreeNode root) {
		if(root == null) {
			return;
		}
		
		Queue<TreeNode> nodeQ = new LinkedList<TreeNode>();
		Queue<Integer> positionQ = new LinkedList<Integer>();
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		nodeQ.add(root);
		positionQ.add(0);
		int maxLeft = 0;
		int minRight = 0;
		while(!nodeQ.isEmpty()) {
			TreeNode node = nodeQ.poll();
			int position = positionQ.poll();
			
			if(position> 0 && position> maxLeft) {
				map.put(position, node.val);
				maxLeft = position;
			} else if(position < 0 && position < minRight) {
				map.put(position, node.val);
				minRight = position;
			} else if(position ==0 && map.get(0) == null) {
				map.put(0, node.val);
			}
			
			if(node.left != null) {
				nodeQ.add(node.left);
				positionQ.add(position+1);
			}
			
			if(node.right != null) {
				nodeQ.add(node.right);
				positionQ.add(position-1);
			}
		}
				
		while(maxLeft >=0) {
			System.out.print(map.get(maxLeft));
			maxLeft--;
		}
		
		int i=-1; 
		while(i >= minRight) {
			System.out.print(map.get(i));
			i--;
		}
	}
}
