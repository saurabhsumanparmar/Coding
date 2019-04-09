package heapandmap;

import java.util.ArrayList;

public class MergeKSortedList {
	 public static void main(String [] args) {
		 MergeKSortedList testobject = new MergeKSortedList();
		 ListNode A = new ListNode(1);
		 A.next = new ListNode(10);
		 A.next.next = new ListNode(20);
		 
		 ListNode B = new ListNode(4);
		 B.next = new ListNode(11);
		 B.next.next = new ListNode(13);
		 
		 ListNode C = new ListNode(3);
		 C.next = new ListNode(8);
		 C.next.next = new ListNode(9);
		 
		 ArrayList<ListNode> a = new ArrayList<ListNode>();
		 a.add(A);
		 a.add(B);
		 a.add(C);
		 
		 ListNode output = testobject.mergeKLists(a);
		 
		 while(output !=null) {
			 System.out.print(output.val + " ");
			 output = output.next;
		 }
		 
	 }
	
	 public ListNode mergeKLists(ArrayList<ListNode> a) {
		 ListNode output = null;
		 ListNode currentPointer = null;
		 ListNode [] heap = new ListNode [a.size()];
		 int heapSize = a.size();
		 
		 for(int i=0; i<a.size(); i++) {
			 heap[i] = a.get(i);
		 }
		 
		 for(int i=(heapSize)/2; i>=0; i--) {
			 heapify(heap, i, 0, heapSize-1);
		 }
		 
		 while(heapSize > 0) {
			 if(output == null) {
				 output = heap[0];
				 currentPointer = output;
			 } else {
				 currentPointer.next = heap[0];
				 currentPointer = currentPointer.next;
			 }
			 
			 if(heap[0].next !=null) {
				 heap[0] = heap[0].next;
				 heapify(heap, 0, 0, heapSize-1);
			 } else {
				 heap[0] = heap[heapSize-1];
				 heapSize--;
				 heapify(heap, 0, 0, heapSize-1);
			 }
		 }
		 	 
		 return output;
	 }

	private void heapify(ListNode[] heap, int index, int start, int end) {
		int left = 2*index + 1;
		int right = 2*index + 2;
		
		int smaller = index;
		
		if(left <= end && heap[left].val < heap[smaller].val) {
			smaller = left;
		}
		
		if(right <= end && heap[right].val < heap[smaller].val) {
			smaller = right;
		}
		
		if(smaller != index) {
			ListNode temp = heap[index];
			heap[index] = heap[smaller];
			heap[smaller] = temp;
					
			heapify(heap, smaller, start, end);
		}
	}
}


class ListNode {
	      public int val;
	      public ListNode next;
	       ListNode(int x) { val = x; next = null; }
}

