package treeandll;

public class ConnectNodeAtSameLevel {
	private void connectNodes(TreeNodeWithRandomPointer root) {
		
		if(root == null) {
			return;
		}
		
		if(root.left != null) {
			connectNodes(root, root.left);
		} else if(root.right != null) {
			connectNodes(root, root.right);
		}
	}
	private void connectNodes(TreeNodeWithRandomPointer parent, TreeNodeWithRandomPointer child) {
		
		if(parent ==null || child == null) {
			return;
		}
		
		TreeNodeWithRandomPointer parentPtr = parent;
		TreeNodeWithRandomPointer childPtr = child;
		
		while(parentPtr != null) {
			if(parentPtr.left != null && parentPtr.left != childPtr) {
				childPtr.random = parentPtr.left;
				childPtr = childPtr.random;
			} else if(parentPtr.right != null && parentPtr.right != childPtr) {
				childPtr.random = parentPtr.right;
				childPtr = childPtr.random;
				parentPtr = parentPtr.random;
			} else {
				parentPtr = parentPtr.random;
			}
		}
		
		parent = child;
		
		while(child != null) {
			if(child.left == null && child.right == null) {
				child = child.random;
			} else if(child.left !=null) {
				child =child.left;
				break;
			} else if(child.right !=null) {
				child =child.right;
				break;
			}
		}
		
		if(child!=null) {
			connectNodes(parent, child);
		}
	}
}
