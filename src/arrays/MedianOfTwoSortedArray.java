package arrays;

import java.util.Arrays;
import java.util.List;

public class MedianOfTwoSortedArray { 
	
	/*public static void main(String [] args) {
		 Integer [] arr = {-37, -9, 10, 19};
		 
		 List<Integer> list = Arrays.asList(arr);
		 
		 Integer [] arr2 = {-29, 18, 46};
		 List<Integer> list2 = Arrays.asList(arr2);
		
		 
		 MedianOfTwoSortedArray testClass = new MedianOfTwoSortedArray();
		 System.out.println(testClass.findMedianSortedArrays(list, list2));
	}*/
	
	public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
		if(a.size() ==0 && b.size()==0) {
			return 0;
		} else if (a.size() + b.size() == 1){
			return a.size()>0 ? a.get(0) : b.get(0);
		}
		
		double output = 0;
		
		int firstArraySize = a.size();
		int secArraySize = b.size();
		
		int firstMedian;
		int secondMedian;
		
		if((firstArraySize + secArraySize)%2 != 0) {
			firstMedian = (firstArraySize + secArraySize +1)/2;
			secondMedian = firstMedian;
		} else {
			firstMedian = (firstArraySize + secArraySize)/2;
			secondMedian = firstMedian+1;
		}
		
		int count=0;
		int firstArrIndex = -1;
		int secArrIndex = -1;

		while(count <= secondMedian) {
			int arrayInwhichCountHasIncreased = -1;
			if(firstArrIndex+1 <a.size() && secArrIndex+1 <b.size() && a.get(firstArrIndex+1) > b.get(secArrIndex+1)) {
				secArrIndex++;
				arrayInwhichCountHasIncreased = 1;
			} else if(firstArrIndex+1 <a.size() && secArrIndex+1 <b.size() && a.get(firstArrIndex+1) < b.get(secArrIndex+1) ) {
				firstArrIndex++;
				arrayInwhichCountHasIncreased = 0;
			} else if(firstArrIndex+1 <a.size()) {
				firstArrIndex++;
				arrayInwhichCountHasIncreased = 0;
			} else {
				secArrIndex++;
				arrayInwhichCountHasIncreased = 1;
			}
			
			count++;
			
			if(count == firstMedian) {
				output += arrayInwhichCountHasIncreased == 0 ? a.get(firstArrIndex) : b.get(secArrIndex);
				if(firstMedian == secondMedian) {
					output += output;
				}
			} else if(count == secondMedian) {
				output += arrayInwhichCountHasIncreased == 0 ? a.get(firstArrIndex) : b.get(secArrIndex);
			}
		}
		
		return output/2;
    }
}
