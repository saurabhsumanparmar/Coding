package arrays;

public class OverlappingIntervals {
	public static void main(String [] args) {
		int [] a = {1, 2, 6, 9};
		int [] b = {3, 4, 8, 10};
		
		overlappingIntervals(a, b);
		
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i] +  " ");
		}
		
		System.out.println();
		
		for(int i=0; i<a.length; i++) {
			System.out.print(b[i] +  " ");
		}
			
		System.out.println();
		//1 3 2 4 6 8 9 10
		//6 8 1 9 2 4 4 7
		
		int [] a1 = {1, 2, 4, 6};
		int [] b1 = {9, 4, 7, 9};
		
		overlappingIntervals(a1, b1);
		
		for(int i=0; i<a1.length; i++) {
			System.out.print(a1[i] +  " ");
		}
		
		System.out.println();
		
		for(int i=0; i<a1.length; i++) {
			System.out.print(b1[i] +  " ");
		}
			
		System.out.println();
		
	}
	
	public static void overlappingIntervals(int [] begin, int [] end) {
		if(begin.length ==0) {
			return;
		}
		
		int movingI = 0;
		int concatenatedIndex = 0;
		int curMin = begin[movingI];
		int curMax = end[movingI];
		
		while (movingI < begin.length) {
			while(movingI < begin.length && begin[movingI] <= curMax) {
				if(end[movingI] > curMax) {
					curMax = end[movingI];
				}
				
				movingI++;
			}
			
			begin[concatenatedIndex] = curMin;
			end[concatenatedIndex] = curMax;
			
			concatenatedIndex++;
			
			if(movingI < begin.length) {
				curMin = begin[movingI];
				curMax = end[movingI];
			}	
		}
		
		while(concatenatedIndex < begin.length) {
			begin[concatenatedIndex] = -1;
			end[concatenatedIndex] = -1;
			concatenatedIndex++;
		}
	}
	
	public void quickSort(int [] arr, int begin, int end) {
		if(begin== end) {
			return;
		}
		
		int pivot = findPivot(arr, begin, end);
		
		if(pivot -1> begin) {
			quickSort(arr, begin, pivot -1);
		}
		
		if(pivot +1 < end) { 
			quickSort(arr, pivot+1, end);
		}
	}

	private int findPivot(int[] arr, int begin, int end) {
		// TODO Auto-generated method stub
		return 0;
	}
}
