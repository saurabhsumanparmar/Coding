package treeandll;


// https://www.geeksforgeeks.org/binary-indexed-tree-range-updates-point-queries/
// Using Binary indexed tree
public class RangeUpdateAndPointQuery {
	
	// Binary indexed tree represented using array
	int [] bit;
	
	private int getSum(int index) {
		index = index +1;
		int sum=0;
		
		while(index > 0) {
			sum = sum + bit[index];
			index = index - (index & (-index));
		}
		
		return sum;
	}
	
	private void update(int i, int val) {
		int index = i+1;
		
		while(index < bit.length) {
			bit[index] = bit[index]+ val;
			index = index + (index & (-index));
		}
	}

	private void update(int l, int r, int n, int val) {
		update(l, val);
		update(r+1, -val);
	}

	private void constructBITree(int[] arr1, int n) {
		bit = new int[n+1];
		
		for(int i=0; i < arr1.length ; i++) {
			update(i, arr1[i]);
		}
	}
	
    public static void main(String args[]) { 
        int arr[] = {0, 0, 0, 0, 0}; 
        int n = arr.length; 
        
        RangeUpdateAndPointQuery testObj = new RangeUpdateAndPointQuery();
  
        testObj.constructBITree(arr,n); 
  
        // Add 2 to all the 
        // element from [2,4] 
        int l = 2, r = 4, val = 2; 
        testObj.update(l, r, n, val); 
  
        int index = 4; 
  
        System.out.println("Element at index "+  
                                index + " is "+  
                                testObj.getSum(index)); 
  
        // Add 4 to all the  
        // element from [0,3]
        l = 0; r = 3; val = 4; 
        testObj.update(l, r, n, val); 
  
        // Find the element 
        // at Index 3 
        index = 3; 
        System.out.println("Element at index "+  
                                index + " is "+  
                                testObj.getSum(index)); 
        
        
        // Another test case .............
        
        int freq[] = {2, 1, 1, 3, 2, 3,  
                4, 5, 6, 7, 8, 9}; 
        RangeUpdateAndPointQuery testObj1 = new RangeUpdateAndPointQuery();

	    // Build fenwick tree from given array 
        testObj1.constructBITree(freq, freq.length);
	
	    System.out.println("Sum of elements in arr[0..5]"+ 
	                    " is "+ testObj1.getSum(5)); 
	      
	      
	    // Update BIT for above change in arr[] 
	    testObj1.update(3, 6);  
	
	    // Find sum after the value is updated 
	    System.out.println("Sum of elements in arr[0..5]"+ 
	                " after update is " + testObj1.getSum(5)); 
    }
}
