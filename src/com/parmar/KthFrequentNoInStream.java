package com.parmar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// assumption : their will be different frequency of each values
public class KthFrequentNoInStream {
	public static void main(String [] args) {
		
		// 1- 6 times, 2- 5 times, 3- 2 times, 7- 1 times, 8- 4 times, 9 - 3 times
		Integer [] arr = new Integer[] { 1, 1, 2, 2, 3, 9, 2, 8, 7, 3, 9, 9, 1, 8, 8, 2, 1, 1, 1, 2, 8};
		
		List<Integer> lst = Arrays.asList(arr);
		Iterator<Integer> itr = lst.iterator();
		
		KthFrequentNoInStream testClass = new KthFrequentNoInStream();
		
		System.out.println(testClass.getklargestelement(itr, 4)); // 
	}
	
	class HeapObj {
	    int freq;
	    int value;
	    int index;
	    
	    HeapObj(int freq, int value) {
	        this.freq = freq;
	        this.value = value;
	        this.index = -1;
	    }
	    
	    public void setFrequency(int freq) {
	     this.freq = freq;
	    }
	    
	    public int getFrequency() {
	     return freq;
	    }
	    
	    public void setValue(int value) {
	     this.value = value;
	    }
	    
	    public void setIndex(int index) {
		     this.index = index;
		}
	    
	    public int getIndex() {
		     return this.index;
		}
	}

    // mistakes : 
	// 1. Infinite while loop
	// 2. Additional changes in heap object to store index in array .. to heapify while incrementing the frequency count
	// 3. If else check to heapify 
	public int getklargestelement(Iterator<Integer> itr, int k){
	   Map <Integer, HeapObj> map  = new HashMap<Integer, HeapObj>();
	   HeapObj[] heap = new HeapObj[k];
	   
	   int count = 0;
	   while(itr.hasNext() && count < k) {
	       int value = itr.next();
	       HeapObj obj = map.get(value);
	       if(obj != null) {
	    	   obj.setFrequency(obj.getFrequency() + 1);
	       } else {
	    	   HeapObj newObj = new HeapObj(1, value);
	           map.put(value, newObj);
	           heap[count] = newObj;
	           newObj.setIndex(count);
	           count++;
	       }
	   }
	   
	   // Build min heap 
	   int j;
	   int endindex;
	   // if count is less than K
	   
	   if(count < k) {
		   j = count/2;
		   endindex = count -1;
	   } else {
		   j = k/2;
		   endindex = k -1;
	   }

	   while(j >=0) {
	       heapify(heap, j, endindex);
	       j--;
	   }
	   
	   if(count < k) {
		   return -1; // to represent that in the given input kth smallest doesn't exists
	   }
	   
	   while(itr.hasNext()) {
		   int value = itr.next();
	       HeapObj obj = map.get(value);
	       if(obj != null) {
	    	   obj.setFrequency(obj.getFrequency() +1);
	    	   
	    	   if(obj.getIndex() == -1 && obj.getFrequency() > heap[0].getFrequency()) { // not in heap
	    		   heap[0].setIndex(-1);
	    		   heap[0] = obj;
	    		   obj.setIndex(0);
	    	   } else if(obj.getIndex() !=-1){
	    		   heapify(heap, obj.getIndex(), endindex);
	    	   }
	    	   
	       } else {
	    	   HeapObj newObj = new HeapObj(1, value);
	           map.put(value, newObj);
	       }
	   }
	   
	   return heap[0].value;
	}

	// min heap
	private void heapify(HeapObj[] heap, int j, int endindex) {
		int left = 2*j +1;
		int right = 2*j + 2;
		
		int min = j;
		
		if(left <= endindex && heap[left].freq < heap[min].freq) {
			min = left;
		}
		
		if(right <= endindex && heap[right].freq < heap[min].freq) {
			min = right;
		}
		
		if(min != j) {
			HeapObj temp = heap[j];
			heap[j] = heap[min];
			heap[j].setIndex(j);
			heap[min] = temp;
			heap[min].setIndex(min);
			
			heapify(heap, min, endindex);
		}
	}

}
