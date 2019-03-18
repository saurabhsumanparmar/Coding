package arrays;

import java.util.ArrayList;
import java.util.List;

public class IndexOfElementInUnSizedArray {
	
	public static void main(String [] args) {
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(2);
		lst.add(5);
		lst.add(8);
		lst.add(9);
		lst.add(10);
		lst.add(15);
		lst.add(18);
		lst.add(21);
		
		System.out.println(indexInArrayOfInfiniteSize(15, lst));
	}
	
	public static int indexInArrayOfInfiniteSize(int element, List<Integer> lst) {
		int start = -1;
		int incrementFactor = 0;
		int end = start + (int)Math.pow(incrementFactor, 2);
		
		while(start < end) {
			
			try {
				int endvalue = lst.get(end);
				
				if(element == endvalue) {
					return end;
				}else if(element > endvalue) {
					incrementFactor++;
				} else {
					return binarySearch(start, end, lst, element);
				}
				
			} catch(Exception ex) {
				System.out.println("Exception due to out of bound..falling back");
			} finally {
				if(incrementFactor == 0) {
					return -1;
				}   else {
					start =  start + (int)Math.pow(incrementFactor -1, 2);
					incrementFactor = 0;
					end = start + (int)Math.pow(incrementFactor, 2);
				}
			}
		}
		
		return -1;
	}

	private static int binarySearch(int start, int end, List<Integer> lst, int element) {
		int mid = (start + end) /2;
		
		if(lst.get(mid) == element) // for case start == end
				return mid;
		
		while(start < end) {
			if(lst.get(mid) == element) {
				return mid;
			} else if(lst.get(mid) > element) {
				start =mid +1;
			}  else {
				end = mid -1;
			}
		}
		
		return -1;
	}

}
