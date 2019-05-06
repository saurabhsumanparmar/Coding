package treeandll;

public class TreeNodeWithRandomPointer {

	public	int val;
	 public TreeNodeWithRandomPointer left;
	 public  TreeNodeWithRandomPointer right;
	 public  TreeNodeWithRandomPointer random;
	  public TreeNodeWithRandomPointer(int x) {
	   val = x;
	   left=null;
	   right=null;
	   random =null;
	  }
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}

}
