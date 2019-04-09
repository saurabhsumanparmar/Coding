package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedianOfArray {
	
	//
	//
	
	// 1
	//
	
	// 1
	// 3
	
	// 1 3 5 8
	// 2 4 6
	
	// 1 3 5 7
	// 4
	
	//
	// 5 6 7
	
	// 1 3 4
	
	public static void main(String [] args) {
		 
		 List<Integer> list1 = new ArrayList<Integer>() ;
		 //list1.add(1);
		 //list1.add(3);
		 //list1.add(5);
		 //list1.add(8);
		 
		 List<Integer> list2 = new ArrayList<Integer>() ;
		 list2.add(2);
		 list2.add(4);
		 list2.add(6);
		 //list2.add(9);
		 
		 MedianOfArray testClass = new MedianOfArray();
		 System.out.println(testClass.findMedianSortedArrays(list1, list2));
	 }
	
	public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
		int noOfElement = a.size() + b.size();
		
		if(noOfElement == 0) {
			return 0.0d;
		} else if(noOfElement == 1) {
			return a.size() > 0 ? a.get(0): b.get(0);
		}
		
		double median1Ele = 0.0d;
		double median2Ele = 0.0d;
	
		int median1Count;
		int median2Count = -1;
		
		if(noOfElement % 2 ==0) {
			median1Count = noOfElement/2;
			median2Count = median1Count +1;
		} else {
			median1Count = noOfElement/2 +1;
		}
		
		int currentIndex;
		List<Integer> currentArray;
		int i=0; int j=0; int count =0;
		
		if(a.size() > 0 && b.size() > 0) {
			if(a.get(0) < b.get(0))  {
				currentIndex = i;
				currentArray = a;
			} else {
				currentIndex = j;
				currentArray = b;
			}
		} else if(a.size() > 0) {
			currentIndex = i;
			currentArray = a;
		} else {
			currentIndex = j;
			currentArray = b;
		}
		
		
		while((i < a.size() || j < b.size()) && count <= median1Count +1)  {
			
			if(count == median1Count) {
				median1Ele = currentArray.get(currentIndex);
			} else if( count == median2Count) {
				median2Ele = currentArray.get(currentIndex);	
			}
			
			if(i < a.size() && j < b.size()) {
				
				if(a.get(i) < b.get(j)) {
					currentIndex = i;
					currentArray = a;
					i++;
				} else {
					currentIndex = j;
					currentArray = b;
					j++;
				}
				
			} else if(i < a.size()) {
				currentIndex = i;
				currentArray = a;
				i++;
			} else {
				currentIndex = j;
				currentArray = b;
				j++;
			}
			
			count++;
		}
		
		
		return median2Count == -1 ? median1Ele : (median1Ele + median2Ele) /2;
    }
}
