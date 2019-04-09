package heapandmap;

import java.util.ArrayList;

//https://practice.geeksforgeeks.org/problems/find-smallest-range-containing-elements-from-k-lists/1

/* Mistakes : 
 * 1. swapping of two no ..line no 92
 * 2. maintaining a max value in the heap -> Logical error
*/

public class SmallestRangeInKLists {
	public static void main(String [] args) {
		
		/*A[] : [4, 7, 9, 12, 15]
				B[] : [0, 8, 10, 14, 20]
				C[] : [6, 12, 16, 30, 50]*/
		
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.add(4); list1.add(7); list1.add(9); list1.add(12); list1.add(15);
		
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(0); list2.add(8); list2.add(10); list2.add(14); list2.add(20);
		
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.add(6); list3.add(12); list3.add(16); list3.add(30); list3.add(50);
		
		ArrayList<Integer> [] Klist = new ArrayList[3];
		Klist[0] = list1;
		Klist[1] = list2;
		Klist[2] = list3;
		
		SmallestRangeInKLists testObject  = new SmallestRangeInKLists();
		System.out.println(testObject.smallestRange(Klist));
		
		ArrayList<Integer> list11 = new ArrayList<Integer>();
		list11.add(1); list11.add(3); list11.add(5); list11.add(7); list11.add(9);
		
		ArrayList<Integer> list22 = new ArrayList<Integer>();
		list22.add(0); list22.add(2); list22.add(4); list22.add(6); list22.add(8);
		
		ArrayList<Integer> list33 = new ArrayList<Integer>();
		list33.add(2); list33.add(3); list33.add(5); list33.add(7); list33.add(11);
		
		ArrayList<Integer> [] Klist1 = new ArrayList[3];
		Klist1[0] = list11;
		Klist1[1] = list22;
		Klist1[2] = list33;
		
		System.out.println(testObject.smallestRange(Klist1));
	}
	
	class HeapEntry {
		int listIndex;
	    int valueIndex;
		int value;
		public HeapEntry(int listIndex, int valueIndex, int value) {
			super();
			this.listIndex = listIndex;
			this.valueIndex = valueIndex;
			this.value = value;
		}
	}
	
	 class MinHeap {
		int size;
		int currentSize;
		HeapEntry[] arr;

		public MinHeap(int size) {
			super();
			this.size = size;
			currentSize=-1;
			arr = new HeapEntry[size];
		}
		
		void add(HeapEntry entry) {
			if(currentSize == size -1) {
				arr[0] = entry;
				heapify(0);
			} else {
				arr[++currentSize] = entry;
			}
		}
		
		HeapEntry getMinvalue() {
			if(currentSize == -1) {
				return null;
			}
			
			return arr[0];
		}

		private void heapify(int i) {
			int left = 2*i + 1;
			int right = 2*i + 2;
			int smallest = i;
			
			if(left <= currentSize && arr[smallest].value > arr[left].value) {
				smallest = left;
			}
			
			if(right <= currentSize && arr[smallest].value > arr[right].value) {
				smallest = right;
			}
			
			if(smallest != i) {
				HeapEntry temp = arr[i];
				arr[i] = arr[smallest];
				arr[smallest] = temp;
				
				heapify(smallest);
			}
		}
	}
	
    int smallestRange(ArrayList<Integer> [] klist) {
		int size = klist.length;
		MinHeap minHeap = this.new MinHeap(size);
		int minRange = Integer.MIN_VALUE;
		
		int minValue = Integer.MAX_VALUE;
		int maxValue = Integer.MIN_VALUE;
		for(int i=0; i<size; i++) {
			minHeap.add(new HeapEntry(i, 0, klist[i].get(0)));
			
			if(klist[i].get(0) < minValue) {
				minValue = klist[i].get(0);
			}
			
			if(klist[i].get(0) > maxValue) {
				maxValue = klist[i].get(0);
			}
		}
		
		int range = maxValue - minValue;
		minRange = range;
		
		for(int i=size/2; i >= 0; i--) {
			minHeap.heapify(i);
		}
		
		HeapEntry minEntry = minHeap.getMinvalue();
		while(minEntry != null && minEntry.valueIndex+1  < klist[minEntry.listIndex].size()) {
			int nextValueToInsert = klist[minEntry.listIndex].get(minEntry.valueIndex+1);
			
			if(maxValue < nextValueToInsert) {
				maxValue = nextValueToInsert;
			}
			
			minHeap.add(new HeapEntry(minEntry.listIndex, minEntry.valueIndex+1, nextValueToInsert));
			
			minEntry = minHeap.getMinvalue();
			range = maxValue - minEntry.value;
			
			if(range < minRange) {
				minRange = range;
			}
		}
		
		return minRange;
	}
}
