package arrays;

import java.util.ArrayList;

//https://practice.geeksforgeeks.org/problems/largest-number-formed-from-an-array/0


/* Mistakes : 
 * 1. Line no 114 ..>= 0 ..instead of > 
 * 2. printArrayInReverse instead of print from begining ...as the comparison in quick sort was to move greater to higer element
 * 3. Line no 146 ..&& remainder!=0 was missing
 * 4. line no 87 ..arr[i] instead of i
 * 5. if(start < end) ..condition placement in quicksort
*/
public class LargestNumberFormedFromAnArray {
	public static void main(String [] args) {
		int [] arr1 = {54, 546, 548, 60};
		int [] arr2 = {3, 30, 34, 5, 9};
		
		int [] output1 = largestnumber(arr1);
		int [] output2 = largestnumber(arr2);
		
		printArrayInReverse(output1);
		System.out.println();
		printArrayInReverse(output2);
	}

	private static void printArrayInReverse(int[] intput) {
		for(int i=intput.length -1; i >= 0; i--) {
			System.out.print(intput[i]);
		}
		
	}

	static int[] largestnumber(int [] arr) {
		if(arr.length ==0) {
			return arr;
		}
		
		modifiedQuickSort(arr, 0, arr.length-1);
		
		return arr;
	}
	
	static void modifiedQuickSort(int [] arr, int start, int end) {
		if(start < end) {
			int i = findPivot(arr, start, end);
			
			if(i !=start) {
				modifiedQuickSort(arr, start, i-1);
			}
			
			if(i != end) {
				modifiedQuickSort(arr, i+1, end);
			}
		}
	}

	private static int findPivot(int[] arr, int start, int end) {
		int pivot = end;
		
		int i=start;
		while(start < end) {
			while(compare(start, pivot, arr) < 0) {
				start++;
			}
			
			while(compare(end, pivot, arr) >= 0 && end > i) {
				end--;
			}
			
			if(start < end) {
				int temp = arr[end];
				arr[end] = arr[start];
				arr[start] = temp;
			}
				
		}
		
		int temp = arr[start];
		arr[start] = arr[pivot];
		arr[pivot] = temp;
		
		return start;
	}

	private static int compare(int i, int j, int[] arr) {
		ArrayList<Integer> valuesofi = convertValuesOf(arr[i]);
		ArrayList<Integer> valuesofj = convertValuesOf(arr[j]);
		
		int endIndexi = valuesofi.size()-1;
		int endIndexj = valuesofj.size() -1;
		
		while(endIndexi >= 0 && endIndexj >=0) {
			if(valuesofi.get(endIndexi) > valuesofj.get(endIndexj)) {
				return 1;
			} else if(valuesofi.get(endIndexi) < valuesofj.get(endIndexj)) {
				return -1;
			} else {
				endIndexi--;
				endIndexj--;
			}
		}
		
		if(endIndexi < 0 && endIndexj < 0) {
			return 0;
		} else if(endIndexi < 0) {
			while(endIndexi < 0 && endIndexj >= 0) {
				if(valuesofj.get(endIndexj) < valuesofi.get(valuesofi.size()-1)) {
					return 1;
				} else if(valuesofj.get(endIndexj) > valuesofi.get(valuesofi.size()-1)) {
					return -1;
				} else {
					endIndexj--;
				}
			}
		} else {
			while(endIndexj < 0 && endIndexi >= 0) {
				if(valuesofi.get(endIndexi) < valuesofj.get(valuesofj.size()-1)) {
					return -1;
				} else if(valuesofi.get(endIndexi) > valuesofj.get(valuesofj.size()-1)) {
					return 1;
				} else {
					endIndexi--;
				}
			}
		}
		
		return 0;
	}

	private static ArrayList<Integer> convertValuesOf(int i) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		if(i ==0) {
			output.add(0);
			return output;
		}
		
		int divisionFactor = 1;
		int balanceFactor = 10;
		int lastRemainder =0;
				
		while(true)  {
			int remainder = i % balanceFactor;
			
			if(remainder == lastRemainder && remainder!=0) {
				break;
			}
			
			output.add((remainder - lastRemainder) / divisionFactor);
			lastRemainder = remainder;
			balanceFactor = balanceFactor*10;
			divisionFactor = divisionFactor * 10;
		}

		return output;
	}
}
