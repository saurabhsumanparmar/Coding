package com.parmar;


public class ReorderList {
	class ListNode {
		public int val;
		public ListNode next;
		    
		ListNode(int x) { val = x; next = null; }
	}
	
	public static void main(String [] args) {
		 ReorderList testClass = new ReorderList();
		 ListNode head =   testClass. new ListNode(1);
		 head.next = testClass.new ListNode(2);
		 head.next.next = testClass.new ListNode(3);
		 head.next.next.next = testClass.new ListNode(4);
		 //head.next.next.next.next = testClass.new ListNode(5);
				 
		 
		 testClass.reorderList(head);
		 
		 while(head != null) {
			 System.out.println(head.val);
			 head =head.next;
		 }
	}
	
	public ListNode reorderList(ListNode A) {
		if(A.next == null) {
			return A;
		}
		
		//put a check for length for 2 ..check it's required or not
		
		ListNode head = A;
		int length = 0;
		while(head != null) {
			length++;
			head = head.next;
		}
		
		int mid = length/2;
		head = A;
		
		ListNode secList = null;
		while(mid > 0) {
			head = head.next;
			mid--;
		}
		
		secList = head.next;
		head.next = null;
		head = A;
		
		// reverse the second half
		secList = reverseList(secList);
		
		// Now Merge
		while(head != null) {
			ListNode temp1 = head.next;
			if(secList.next != null) {
				ListNode temp2 = secList.next;
				head.next = secList;
				secList.next = temp1;
				
				head = temp1;
				secList = temp2;
			} else {
				if(secList != null) {
					head.next = secList;
					secList.next = temp1;
				} 
				
				head = head.next;
			}
		}
		
		return A;
    }

	private ListNode reverseList(ListNode head) {
		ListNode cur = head;
		
		if(cur.next == null) {
			return cur;
		}
		
		ListNode next = cur.next;
		
		while(next != null) {
			if(next.next != null) {
				ListNode temp = next.next;
				next.next = cur;
				cur = next;
				next = temp;
			} else {
				next.next = cur;
				cur = next;
				next = null;
			}	
		}
		
		head.next = null;
		
		return cur;
	}
}
