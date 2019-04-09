package heapandmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KthSmallest {
	 public static void main1(String [] args) {
		 Integer [] arr = { 94, 87, 100, 11, 23, 98, 17, 35, 43, 66, 34, 53, 72, 80, 5, 34, 64, 71,
				 9, 16, 41, 66, 96 };
		 
		 List<Integer> list = Arrays.asList(arr);
		 //[2 1 4 3 2]
		// list.add(2);
		 //list.add(1);
		 //list.add(4);
		 //list.add(3);
		 //list.add(2);
		
		 
		 KthSmallest testClass = new KthSmallest();
		 System.out.println(testClass.kthsmallest(list, 19));
	 }
	 
	 public int kthsmallest(final List<Integer> A, int B) {
	        // Assuming B < A.size()
	        
	        int [] maxHeap = new int[B];
	        for(int i=0; i< B; i++) {
	            maxHeap[i] = A.get(i);
	        }
	        
	        int j = (B -1) /2;
	        
	        while(j >= 0) {
	            heapify(maxHeap, j);
	            j--;
	        }
	        
	        for(int i=B; i< A.size(); i++) {
	            if(A.get(i) < maxHeap[0]) {
	                maxHeap[0] = A.get(i);
	                heapify(maxHeap, 0);
	            }
	        }
	        
	        
	        int kthSmallest = maxHeap[0];
	        
	        
	        return kthSmallest;
	    }
	    
	    public void heapify(int [] maxHeap, int i) {
	       int l = 2*i + 1;
	       int r = 2*i + 2;
	       
	       int largest  = i;
	       
	       if(l < maxHeap.length && maxHeap[largest] < maxHeap[l]) {
	           largest = l;
	       }
	       
	       if(r < maxHeap.length && maxHeap[largest] < maxHeap[r]) {
	           largest = r;
	       }
	       
	       if(largest != i) {
	            int temp =  maxHeap[i];
	            maxHeap[i] = maxHeap[largest];
	            maxHeap[largest] = temp;
	            heapify(maxHeap, largest);
	       }
	       
	    }  
	    
	    public int kthsmallestUsingQuickSort(List<Integer> A, int B) {
	    	int  start = 0;
	    	int end = A.size() -1;
	    	
	    	int index =  findPivot(A, start, end); //changes array as well
	    	int kth = B;
	    	
	    	while((index - start) +1  != kth) {
	    		if((index - start) +1  < kth) {
	    			start = index +1;
	    			kth = kth - ((index - start) +1);
	    			index =  findPivot(A, start, end);
	    		} else {
	    			index =  findPivot(A, start, index-1);
	    		}
	    	}
	    	
	    	return A.get(index);	    	
	    }
	    
	    
	    public static void main(String [] args) {
			 Integer [] arr = {1, 2, 3, 4, 5};
			 
			 List<Integer> list = Arrays.asList(arr);
			 
			 KthSmallest testClass = new KthSmallest();
			 System.out.println(testClass.findPivot(list, 0 , list.size()-1));
			 System.out.println(list);
		 }

		private int findPivot(List<Integer> a, int start, int end) {
			if(start == end) {
				return start;
			}
			
			int pivot = end;
			int left  = start;
			int right  = end;
			
			while(left<right) {
				while(a.get(right) >= a.get(pivot) && right > start) {
					right--;
				}
				
				while(a.get(left) < a.get(pivot) && left < end) {
					left++;
				}
				
				if(left < right) {
					swap(a, left, right);
				}
			}
			
			swap(a, left, pivot);
			
			return left;
		}

		private void swap(List<Integer> a, int start, int end) {
			int temp  = a.get(start);
			a.set(start, a.get(end));
			a.set(end, temp);
		}
}
