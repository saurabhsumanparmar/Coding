package treeandll;

// https://practice.geeksforgeeks.org/problems/flattening-a-linked-list/1
public class FlatteningALL {
	
	class Node {
		Node next;
		Node bottom;
		int value;
		
		public Node(int value) {
			super();
			this.value = value;
			this.next = null;
			this.bottom = null;
		}
	}
	
	public static void main(String [] args) {
		FlatteningALL instance = new FlatteningALL();
		Node verticalList1 = instance.new Node(5);
		verticalList1.bottom = instance.new Node(7);
		verticalList1.bottom.bottom = instance.new Node(8);
		verticalList1.bottom.bottom.bottom = instance.new Node(30);
		
		Node verticalList2 = instance.new Node(10);
		verticalList2.bottom = instance.new Node(20);
		
		Node verticalList3 = instance.new Node(19);
		verticalList3.bottom = instance.new Node(22);
		verticalList3.bottom.bottom = instance.new Node(50);
		
		Node verticalList4 = instance.new Node(28);
		verticalList4.bottom = instance.new Node(35);
		verticalList4.bottom.bottom = instance.new Node(40);
		verticalList4.bottom.bottom.bottom = instance.new Node(45);
		
		verticalList1.next = verticalList2;
		verticalList2.next = verticalList3;
		verticalList3.next = verticalList4;
		
		Node head = instance.flattenList(verticalList1);
		
		Node movingPtr = head;
		while(movingPtr != null) {
			System.out.println(movingPtr.value + " ");
			movingPtr = movingPtr.next;
		}
	}
	
	public Node flattenList(Node node) {
		Node head = node;
		Node movingPtr = node;
		Node list = node;
		
		while(movingPtr !=null) {
			list = collectNext(list);
			movingPtr.bottom = null;
			movingPtr.next = list;
			movingPtr = movingPtr.next;
		}
		
		return head;
	}

	private Node collectNext(Node list) {
		if(list.bottom == null && list.next != null) {
			return list.next;
		} else if(list.bottom != null && list.next == null) {
			return list.bottom;
		} else if(list.bottom != null && list.next != null && list.bottom.value <= list.next.value) {
			 list.bottom.next = list.next;
			 return list.bottom;
		} else if(list.bottom != null && list.next != null && list.bottom.value > list.next.value) {
			Node temp = collectNext(list.next);
			list.next.bottom = list.bottom;
			list.next.next = temp;
			return list.next;
		}
		
		return null;
	}
}
