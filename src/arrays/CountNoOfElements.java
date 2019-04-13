package arrays;

import java.util.ArrayList;
import java.util.Collections;

//https://www.geeksforgeeks.org/element-1st-array-count-elements-less-equal-2nd-array/
public class CountNoOfElements {
	public static void main(String [] args) {
		int [] arr1 = {1, 2, 3, 4, 7, 9};
		//int [] arr1 = {2};
		int [] arr2 =  {0, 1, 2, 1, 1, 4};
		int [] output = countNoOfElements(arr1, arr2);
		
		
		//4, 5, 5, 6, 6, 6
		for(int i=0; i < output.length;i++) {
			System.out.print(output[i] + " ");
		}
	}
	
	static int[] countNoOfElements(int [] arr1, int [] arr2) {
		int [] output = new int[arr1.length];
		
		
		ArrayList<Integer> lst  = new ArrayList<Integer>();
		for(int i=0; i < arr2.length;i++) {
			lst.add(arr2[i]);
		}
		Collections.sort(lst);
		
		for(int i=0; i < arr1.length;i++) {
			output[i] = getMaxIndexlessThani(arr1[i], lst);
		}
				
		return output;
	}

	private static int getMaxIndexlessThani(Integer i, ArrayList<Integer> lst) {
		int begin = 0;
		int end = lst.size()-1;
		
		if(i < lst.get(begin)) {
			return 0;
		}
		
		if(lst.get(end) < i) {
			return lst.size();
		}
		
		int mid;
		while(begin < end) {
			mid = (begin+end)/2;
			if((int)lst.get(mid) <= i) {
				begin = mid;
			} else {
				end = mid-1;
			}	
			
			if(begin == mid && (end == mid+1 || end == mid)) {
				break;
			}
		}
		
		return begin+1;
	}
}
