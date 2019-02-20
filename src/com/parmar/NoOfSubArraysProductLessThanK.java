package com.parmar;

/*Input : A[]={1, 2,3,4} 
K = 10
Output : 7
The contiguous subarrays are {1}, {2}, {3}, {4}
{1, 2}, {1, 2, 3} and {2, 3} whose count is 7.*/

public class NoOfSubArraysProductLessThanK {
	public static void main(String [] args) {
		NoOfSubArraysProductLessThanK testClass = new NoOfSubArraysProductLessThanK();
		 int A[]={1, 2,3,4} ;
		System.out.println(testClass.noOfSubArrays(A, 24));
	}
	
	private int noOfSubArrays(int [] arr, int k) {
		int count = 0;
		
		int start = -1;
		int end = -1;
		
		int product = -1;
		
		int i=0;
		while(i< arr.length) {
			if(arr[i] > k) {
				start = -1;
				end = -1;
				
				product = -1;
				continue;
			}
			
			int newProduct;
			if(product == -1) {
				newProduct = arr[i];
			} else {
				newProduct = product * arr[i];
			}
			
			if(newProduct <= k) {
				end =i;
				
				if(start == -1) {
					start = i;
					
					count = count+1;
				} else {
					count = count +1; // For inclusion of new element
					count = count + (end -start);
				}

			} else {
				int index = findIndexForProductLessThanK(arr, start, i, newProduct, k); // end Must be touching i
				
				start = index;
				end = i;
				
				if(index == i) {
					count = count+1;
				} else {
					count = count +1; // For inclusion of new element
					count = count + (end -start);
				}
			}
			
			product = newProduct;
			i++;
		}
		
		return count;
	}

	private int findIndexForProductLessThanK(int[] arr, int start, int i, int newProduct, int k) {
		while(start <i) {
			newProduct = newProduct/ arr[start];
			
			if(newProduct <= k) {
				return start+1;
			}
			
			start ++;
		}
		
		return start;
	}
}
