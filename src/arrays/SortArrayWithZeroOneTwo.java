package arrays;

public class SortArrayWithZeroOneTwo {
	public static void main(String [] args) {
		int [] arr1 = {1, 2, 2, 0, 1, 0, 2, 2};
		sortZeroOneTwo(arr1);
		
		for(int i=0; i< arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		
		System.out.println();
		int [] arr2 = {2, 2, 2, 0, 1, 0, 1, 1};
		sortZeroOneTwo(arr2);
		
		for(int i=0; i< arr2.length; i++) {
			System.out.print(arr2[i] + " ");
		}
		
		System.out.println();
		int [] arr3 = {2, 2, 2, 0, 0, 0, 0, 0};
		sortZeroOneTwo(arr3);
		
		for(int i=0; i< arr3.length; i++) {
			System.out.print(arr3[i] + " ");
		}
		
		System.out.println();
		int [] arr4 = {0, 0, 0, 1, 1, 2, 2, 2};
		sortZeroOneTwo(arr4);
		
		for(int i=0; i< arr4.length; i++) {
			System.out.print(arr4[i] + " ");
		}
	}
	
	public static void sortZeroOneTwo (int [] arr) {
		int twoStartIndex = -1;
		int oneStartindex = -1;
		int oneendIndex = -1;
		
		int start = 0;
		int end = arr.length -1;
		
		while(start <= end) {
			while(arr[start] ==0 && start <arr.length ) {
				start++;
			}
			
			while(arr[end] !=0 && start <= end) {
				if(arr[end]== 2) {
					if(twoStartIndex == -1) {
						twoStartIndex = arr.length -1;
					} else {
						twoStartIndex = twoStartIndex-1;
					}
					
					arr[twoStartIndex] = 2;
					
					if(oneStartindex != -1) {
						oneStartindex = end;
						oneendIndex = oneendIndex-1;
						arr[oneStartindex] = 1;
					}
					
				} else if(arr[end] ==1) {
					if(oneStartindex != -1) {
						oneStartindex = end;
						oneendIndex = oneendIndex-1;
					} else {
						oneStartindex = end;
						oneendIndex = end;
					}
					
					arr[oneStartindex] = 1;
				}
				
				end--;
			}
			
			if(start < end) {
				if(arr[start] ==1) {
					if(oneStartindex != -1) {
						oneStartindex = oneStartindex-1;
					} else {
						oneStartindex = end;
						oneendIndex = end;
					}
					
					arr[oneStartindex] = 1;
					
				} else if(arr[start] ==2) {
					if(twoStartIndex == -1) {
						arr[arr.length -1] =2;
						twoStartIndex = arr.length-1;
					} else {
						twoStartIndex = twoStartIndex -1;
						arr[twoStartIndex] = 2;
					}
					
					if(oneStartindex != -1) {
						oneStartindex = end;
						oneendIndex = oneendIndex-1;
						arr[oneStartindex] = 1;
					}
				}
				
				arr[start] = 0;
			}
			
			start++;
			end--;
		}
	}
}
