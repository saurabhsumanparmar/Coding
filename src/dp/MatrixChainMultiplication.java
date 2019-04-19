package dp;


// https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
public class MatrixChainMultiplication {
	public static void main(String [] args) {
		int arr [] = {10, 20, 30, 40, 30};
		System.out.println(minimumOperation(arr));
		
		int arr2 [] = {10, 20, 30};
		System.out.println(minimumOperation(arr2));
	}
	
	static int minimumOperation(int [] arr) {
		if(arr.length < 3) {
			return 0;
		}
		
		int [][] operations = new int[arr.length][arr.length];
		
		for(int k=1; k< arr.length-1; k++) {
			for(int i=1; i+k < arr.length; i++) {
				if(k==1) {
					operations[i][i+1]= arr[i-1]*arr[i]*arr[i+1];
				} else {
					int min= Integer.MAX_VALUE;
					for(int j=i+1; j< i+k; j++) {
						if(operations[i][j] + operations[j+1][i+k] + arr[i-1]*arr[j]*arr[i+k] < min) {
							min = operations[i][j] + operations[j+1][i+k] + arr[i-1]*arr[j]*arr[i+k];
						}
					}
					
					operations[i][i+k] = min;
				}
			}
		}
		
		return operations[1][arr.length-1];
	}
}
