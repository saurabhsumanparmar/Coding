package com.parmar;


public class Substract {
	
	public static void main(String [] args) {
		 Substract testClass = new Substract();
		 ListNode head =   testClass. new ListNode(55);
		 head.next = testClass.new ListNode(35);
		 head.next.next = testClass.new ListNode(19);
		 //head.next.next.next = testClass.new ListNode(4);
		 //head.next.next.next.next = testClass.new ListNode(5);		 
		 
		 testClass.subtract(head);
		 
		 while(head != null) {
			 System.out.println(head.val);
			 head = head.next;
		 }
	}
	
	class ListNode {
		     public int val;
		     public ListNode next;
		     ListNode(int x) { val = x; next = null; }
	}
	
	 public ListNode subtract(ListNode A) {
		 if(A == null || A.next == null) {
             return A;
         }
		 
		 ListNode head = A;
		 subtractRecursively(head, head, 1);
		 return A;
	 }
	 
	 public ListNode subtractRecursively(ListNode head, ListNode curPtr, int position) {
		 if(curPtr.next !=null) {
			 ListNode returnClass = subtractRecursively(head, curPtr.next, position +1);
			 
			 if(position > returnClass.val) {
				 returnClass.next.val = curPtr.val - returnClass.next.val;
				 returnClass.next = returnClass.next.next;	 
				 returnClass.val++;
			 }
			 
			 return returnClass;
		 } else {
			 ListNode returnClass = new ListNode(2);
			 returnClass.next = head;
			 returnClass.next.val = curPtr.val - returnClass.next.val;
			 returnClass.next = returnClass.next.next;
			 return returnClass;
		 }
	 }
	 
	 
	 //***************************************
	 
		public ListNode subtract2(ListNode A) {
			if(A == null || A.next == null) {
                return A;
            }
            
            //put a check for length for 2 ..check it's required or not
            if(A.next.next == null) {
                A.val = A.next.val - A.val;
                return A;
            }
            
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
            	mid--;
                head = head.next;
  
            }
            
            secList = head.next;
            head.next = null;
            head = A;
            
            // reverse the second half
            secList = reverseList(secList);
            
            ListNode holder = secList;
            ListNode holder2 = head;
            
            while(head != null && secList != null) {
                    head.val = secList.val - head.val;
                    holder2 = head;
                    
                    head = head.next;
                    secList = secList.next;
                    
            }
            
            if(head != null) {
                holder2 = head;
            }
            
            // again reverse the second half
            holder = reverseList(holder);
                        
            holder2.next = holder;
            
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
