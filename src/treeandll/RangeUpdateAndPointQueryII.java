package treeandll;

// https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
public class RangeUpdateAndPointQueryII {
	int [] arr;
	int [] st;
	
	public RangeUpdateAndPointQueryII(int[] arr) {
		super();
		this.arr = arr;
		int size = (int)Math.ceil(Math.log(arr.length) / Math.log(2));
		int MAX_SIZE = 2* (int)Math.pow(2, size) -1;
		st = new int[MAX_SIZE];
		
		constructSegmentTree(0, this.arr.length-1, 0);
	}

	private int constructSegmentTree(int i, int j, int si) {
		if(i==j) {
			st[si] = arr[i];
			return st[si];
		}
		
		int mid = (i+j)/2;
		st[si] = constructSegmentTree(i, mid, 2*si+1)
				+ constructSegmentTree(mid+1, j, 2*si+2);
		
		return st[si];
	}

	private void updateValue(int i, int val) {
		int diff = val - arr[i];
		updateUtil(0, this.arr.length-1, i, diff, 0);
	}


	private void updateUtil(int i, int j, int index, int val, int si) {
		if(index < i || index > j) {
			return;
		} else if(i ==j) {
			st[si] = st[si] + val;
			return;
		}
		
		st[si] = st[si] + val;
		
		int mid = (i+j)/2;
		
		updateUtil(i , mid, index, val, 2*si+1);
		updateUtil(mid+1 , j, index, val, 2*si+2);
	}



	private int getSum(int i, int j) {
		return getSumUtil(0, this.arr.length-1, i, j, 0);
	}

	private int getSumUtil(int i, int j, int qs, int qe, int si) {
		if(qs <= i && qe>=j) {
			return st[si];
		} else if(qe < i || qs > j)  {
			return 0;
		}
		
		int mid = (i+j)/2;
		
		return getSumUtil(i, mid, qs, qe, 2*si+1) + getSumUtil(mid+1, j, qs, qe, 2*si+2);
	}

	public static void main(String args[]) { 
        int arr[] = {1, 3, 5, 7, 9, 11}; 
        RangeUpdateAndPointQueryII  obj = new RangeUpdateAndPointQueryII(arr); 
        
        // Build segment tree from given array
  
        // Print sum of values in array from index 1 to 3 
        System.out.println("Sum of values in given range = " + 
                           obj.getSum(1, 3)); 
  
        // Update: set arr[1] = 10 and update corresponding segment 
        // tree nodes 
        obj.updateValue(1, 10);
  
        // Find sum after the value is updated 
        System.out.println("Updated sum of values in given range = " + 
                obj.getSum(1, 3)); 
    }
 
}
