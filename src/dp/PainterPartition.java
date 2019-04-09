package dp;

// https://practice.geeksforgeeks.org/problems/the-painters-partition-problem/0
public class PainterPartition {
	public static void main(String [] args) {
		int [] arr1 = {10,20, 30, 40};
		System.out.println(minTimeToPaintHouse(arr1, 2));
		
		int [] arr2 = {10, 10, 10, 10};
		System.out.println(minTimeToPaintHouse(arr2, 2));
	}
	
	static int minTimeToPaintHouse(int [] arr, int k) {
		if(arr.length ==0) {
			return -1;
		}
		
		int [][] matrix = new  int[arr.length][k];
		
		for(int i=0; i <k; i++ ) {
			matrix[0][i] = arr[0];
		}
		
		for(int i=1; i< arr.length; i++) {
			for(int l=0; l<k; l++) {
				if(l==0) {
					matrix[i][0] = sum(arr, 0, i);
					continue;
				}
				
				if(l > i) {
					matrix[i][l] = matrix[i][l-1];
					continue;
				}

				for(int j=0; j < i; j++) {
					if(matrix[i][l] ==0) {
						matrix[i][l] = maximum(matrix[j][l-1], sum(arr, j+1, i));
					} else {
						int minValue = maximum(matrix[j][l-1], sum(arr, j+1, i));
						if(minValue < matrix[i][l]) {
							matrix[i][l] = minValue;
						}
					}
				}	
			}
		}
		
		return matrix[arr.length-1][k-1];
	}

	private static int maximum(int i, int j) {
		return i > j ? i : j;
	}

	private static int sum(int[] arr, int i, int j) {
		int sum=0;
		for(int k=i; k<=j; k++) {
			sum += arr[k];
		}
		return sum;
	}
}
