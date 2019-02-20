package com.parmar;


public class NextSmallestpallindrome {
	
	public static void main(String [] args) {
		NextSmallestpallindrome testClass = new NextSmallestpallindrome();
		int [] input = { 9, 4, 1, 8, 7, 9, 7, 8, 3, 2, 2}; 
		// output : 9 4 1 8 8 0 8 8 1 4 9
		
		
		int [] output = testClass.nextSmallestPallindrome(input);
		
		int index=0;
		
		while(index <output.length) {
			System.out.print(output[index] + " ");
			index++;
		}	
	}
	
	private int[] nextSmallestPallindrome(int [] arr) {
		int length = arr.length;
		
		//initializing index to -ve value
		int mid1 = -1;
		int mid2 = -1;
		int left = -1;
		int right = -1;
		
		if(length%2 ==0) {
			mid2 = length/2;
			mid1 = mid2-1;	
		} else {
			mid1 = length/2;
			mid2 = mid1;
		}
		
		left = mid1-1;
		right = mid2+1;
		
		if(mid1 != mid2 && arr[mid1] != arr[mid2]) {
			if(mid1 > mid2) {
				arr[mid2] = arr[mid1];
				copyLeftTorightInclusive(arr, left, right);
			} else {
				arr[mid1] = arr[mid1] +1;
				copyLeftTorightInclusive(arr, mid1, mid2);
			}
			
			return arr;
		}
		
		while (arr[left] == arr[right] && left >=0 && right< arr.length) {
			left--;
			right++;
		}
		
		if(arr[right] < arr[left]) {
			copyLeftTorightInclusive(arr, left, right);
		} else if(arr[right] > arr[left]) {
			if(mid1 - left ==1) {
				if(mid1 - left ==1 && mid1== 9) { // 193 && 1993
					arr[left] = arr[left] +1;
					copyLeftTorightInclusive(arr, left, right);
						 arr[mid1] = 0;
						 arr[mid2] = 0;
				} else if(mid1 - left ==1 && mid1 != 9 && mid1!= mid2) { // 1883
					arr[mid1] = arr[mid1] +1;
					copyLeftTorightInclusive(arr, mid1, mid2);
				} else if(mid1 - left ==1 && mid1 != 9 && mid1== mid2) { // 183
					arr[mid1] = 0;
					arr[left] = arr[left] +1;
					copyLeftTorightInclusive(arr, left, right);
				}
			}  else {
				int index = findFirstNon9Element(arr, mid1, left);
					
				if(index != left) {
					/*if(mid1 == mid2) {
						arr[index] = arr[index] +1;
						convertMiddleElementToZeroInclusive(arr, index+1, mid1 + (mid1- index) - 1);
						copyLeftTorightInclusive(arr, index, mid1 + (mid1- index));
					} else  {
						arr[index] = arr[index] +1;
						convertMiddleElementToZeroInclusive(arr, index+1, mid2 + (mid1- index) - 1);
						copyLeftTorightInclusive(arr, index, mid2 + (mid1- index));
					}*/
					
					arr[index] = arr[index] +1;
					convertMiddleElementToZeroInclusive(arr, index+1, mid2 + (mid1- index) - 1);
					copyLeftTorightInclusive(arr, index, mid2 + (mid1- index));
					
				} else {
					arr[index] = arr[index] +1;
					convertMiddleElementToZeroInclusive(arr, index+1, mid1 + (mid1- index) - 1);
				}
			}
			
		} else {		
			// Create new array and fill
			int [] newArr = new int[arr.length+1];
			newArr[0] = 1;
			newArr[arr.length] = 1;
			
			int movingIndex = 1;
			while(movingIndex < arr.length) {
				newArr[movingIndex] =0;
			}
			
			return newArr;
		}
		
		
		
		return arr;
	}

	private void convertMiddleElementToZeroInclusive(int[] arr, int i, int j) {
		while(i<=j) {
			arr[i] =0;
			arr[j] =0;
			
			i++;
			j--;
		}
		
	}

	private int findFirstNon9Element(int[] arr, int mid1, int left) {
		int index = mid1;
		while(index>left && arr[index] ==9) {
			index--;
		}

		return index;
	}

	private void copyLeftTorightInclusive(int[] arr, int left, int right) {
		while(left >=0 && right <arr.length) {
			arr[right] = arr[left];
			left--;
			right++;
		}	
	}

}
