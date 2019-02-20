package com.parmar;

import java.util.List;

// gut feeling will run
public class RotatedSortedArray {
	
	 public int search(final List<Integer> a, int b) {
		 
		 int start = 0;
		 int end = a.size();
		 while(start >=0 &&  end < a.size()) {
			int mid = (end - start)/2;
			
			if(b == a.get(mid)) {
				return mid;
			}
			
			if(start == end) {
				return -1;
			}
			
			// case  1 :  where there is rotation in second half
			if(start > end && mid > start) {
			   if(b < mid && b >= start) {
				   end = mid - 1;
			   } else {
				   start = mid + 1;
			   }
			
			// case  2 :  where there is rotation in first half
			} else if(start > mid && end < start) {
				if(b > mid && b <= end) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
				
			// no rotation
			} else {
				if(b < mid) {
					end = mid -1;
				} else {
					start = mid + 1;
				}
			}
		 }
		 
		 return -1;
	 }
}
