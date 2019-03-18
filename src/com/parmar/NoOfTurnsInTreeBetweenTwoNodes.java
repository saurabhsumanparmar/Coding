package com.parmar;


public class NoOfTurnsInTreeBetweenTwoNodes {
	class PassedData {
		int noOfValueFound;
	    String lastNodeDirection;
	    int noOfTurns;

		public PassedData() {
			super();
			noOfValueFound = 0;
			lastNodeDirection = "";
			noOfTurns =0;
			
		}
		public int getNoOfValueFound() {
			return noOfValueFound;
		}
		public void setNoOfValueFound(int noOfValueFound) {
			this.noOfValueFound = noOfValueFound;
		}
		public String getLastNodeDirection() {
			return lastNodeDirection;
		}
		public void setLastNodeDirection(String lastNodeDirection) {
			this.lastNodeDirection = lastNodeDirection;
		}
		public int getNoOfTurns() {
			return noOfTurns;
		}
		public void setNoOfTurns(int noOfTurns) {
			this.noOfTurns = noOfTurns;
		}
	}

	public static void main(String [] args) {
		NoOfTurnsInTreeBetweenTwoNodes testClass = new NoOfTurnsInTreeBetweenTwoNodes();
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
		
		PassedData passVal = testClass.new PassedData();
		int firstVal = 1;
		int secVal = 9;
		
		passVal= testClass.noOfTurns(root, firstVal, secVal, passVal);
		
		System.out.println(passVal.noOfTurns);
		
	}
	
	
	/* Correction done to run the code: 
	 * 
	 */
	public PassedData noOfTurns(TreeNode root, int first, int second, PassedData passVal) {
		if(passVal.noOfValueFound==1 && (root.val == first || root.val == second)) {
			passVal.noOfValueFound = 2;
			
			return passVal;
		}
		
		if(passVal.noOfValueFound ==0) {
			
			if((root.val == first || root.val == second)) {
				passVal.noOfValueFound = 1;
				
				if(root.left != null) {
					passVal.lastNodeDirection = "L";
					passVal = noOfTurns(root.left, first, second, passVal);
					
				    if(passVal.noOfValueFound ==2) {
				    	return passVal;
				    }
				    
				    passVal.lastNodeDirection = "";
				    passVal.noOfTurns =0;
				}
				
				if(root.right != null) {
					passVal.lastNodeDirection = "R";
					passVal = noOfTurns(root.right, first, second, passVal);
					
				    if(passVal.noOfValueFound ==2) {
				    	return passVal;
				    }
				    
				    passVal.lastNodeDirection = "";
				    passVal.noOfTurns =0;
				}
				
				return passVal;
			} else {
				PassedData left;
				PassedData right;
				
				if(root.left != null) {
					left = noOfTurns(root.left, first, second, passVal);
					
					if(left.noOfValueFound ==1 && root.right != null) {
						PassedData temp = new PassedData();
						temp.noOfTurns  = left.noOfTurns +1;
						temp.noOfValueFound = 1;
						temp.lastNodeDirection = "R";
						
						if(left.lastNodeDirection == "R") {
							temp.noOfTurns++;
							
						}
						
						right = noOfTurns(root.left, first, second, temp);
						
						return right.noOfValueFound ==2 ? right : left;
					}
					
					if(left.lastNodeDirection == "R") {
						left.noOfTurns++;
						
					}
					
					left.lastNodeDirection = "L";
					return left;
				}
				
				
				if(root.right != null) {
					right = noOfTurns(root.right, first, second, passVal);
					
					if(right.noOfValueFound ==1 && root.left != null) {
						PassedData temp = new PassedData();
						temp.noOfTurns  = right.noOfTurns +1;
						temp.noOfValueFound = 1;
						temp.lastNodeDirection = "L";
						
						if(right.lastNodeDirection == "L") {
							temp.noOfTurns++;
							
						}
						
						left = noOfTurns(root.left, first, second, temp);

						return left.noOfValueFound ==2 ? left : right;
					}
					
					
					if(right.lastNodeDirection == "L") {
						right.noOfTurns++;
						
					}
					
					right.lastNodeDirection = "R";
					
					return right;
				}
			}
			
			
		} else if(passVal.noOfValueFound ==1) {
			
			PassedData left;
			PassedData right;
			
			if(root.left != null) {
				
				PassedData temp = new PassedData();
				temp.noOfTurns  = passVal.noOfTurns;
				temp.noOfValueFound = 1;
				
				if(passVal.lastNodeDirection == "R") {
					temp.noOfTurns++;
				}
				
				temp.lastNodeDirection = "L";
				left = noOfTurns(root.left, first, second, temp);
				
			    if(left.noOfValueFound ==2) {
			    	return left;
			    }
			}
			
			if(root.right != null) {
				PassedData temp = new PassedData();
				temp.noOfTurns  = passVal.noOfTurns;
				temp.noOfValueFound = 1;
				
				if(passVal.lastNodeDirection == "L") {
					temp.noOfTurns++;
				}
				
				temp.lastNodeDirection = "R";
				right = noOfTurns(root.left, first, second, temp);
				
			    if(right.noOfValueFound ==2) {
			    	return right;
			    }
			}
			
			return passVal;
		}
			
			
		return passVal;
	}
	
	
}
