package treeandll;

public class NextPointerToRight {
	
	 // Definition for binary tree with next pointer.
	 public class TreeLinkNode {
	      int val;
	      TreeLinkNode left, right, next;
	      TreeLinkNode(int x) { val = x; }
	 }
	
	public class Solution {
	    public void connect(TreeLinkNode root) {
	   
	    	TreeLinkNode parantHead = root;
	    	TreeLinkNode childHead =  null;
	    	
	    	while(root!= null) {
	    		if(root.left !=null ) {
	    			childHead = root.left;
	    		} else if(root.right !=null) {
	    			childHead = root.right;
	    		} else {
	    			root.next = null;
	    			return;
	    		}
	    	}
	    	
	    	connectParentChild(parantHead, childHead);
	    }
	    
	    public void connectParentChild(TreeLinkNode parent, TreeLinkNode child) {
	        
	    	TreeLinkNode nextChild = null;
	    	TreeLinkNode parantHead = parent;
	    	TreeLinkNode childHead =  child;
	    	
	    	boolean leftVisited = false;
	    	boolean rightVisited = false;
	    	
	    	while(parent != null) {
	    		if(!leftVisited && parent.left !=null && parent.left !=child) {
	    			nextChild = parent.left;
	    			leftVisited = true;
	    		} else if(!rightVisited && parent.right !=null && parent.right !=child) {
	    			nextChild = parent.right;
	    			rightVisited = true;
	    		} else {
	    			parent = parent.next;
	    			leftVisited = false;
	    			rightVisited = false;
	    		}
	    		
	    		if(nextChild !=null) {
	    			child.next = nextChild;
		    		child = nextChild;
		    		nextChild = null;
	    		}
	    	}
	    	
	    	child.next = null;
	    	
	    	while(childHead.left == null && childHead.right == null) {
	    		childHead = childHead.next;
	    	}
	    	
	    	if(childHead != null) {
	    		parantHead = childHead;
	    		childHead = childHead.left != null ? childHead.left : childHead.right;
	    		connectParentChild(parantHead, childHead);
	    	}	
	    }
	}
}
