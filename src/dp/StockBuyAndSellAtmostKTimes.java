package dp;

public class StockBuyAndSellAtmostKTimes {
	public static void main(String [] args) {
		int [] t1 = {1, 2, 3, 4, 5, 6 , 7};
		int [] t2 = {5, 10, 2, 12, 13, 6, 13}; //, 4, 15, 10 , 3, 16, 19};
		
		// Test cases :
		System.out.println(findMaximsedProfitAtmostKTimes(t1, 1)); // expected 6
		System.out.println(findMaximsedProfitAtmostKTimes(t1, 2)); // expected 6
		System.out.println(findMaximsedProfitAtmostKTimes(t2, 3)); // 
		System.out.println(findMaximsedProfitAtmostKTimes(t2, 2));
		System.out.println(findMaximsedProfitAtmostKTimes(t2, 5));
	}
	
	private static int findMaximsedProfitAtmostKTimes(int [] arr, int k) {
		
		int [][] matrix = new int[arr.length][k+1];
		int[][] matrix2 = new int[arr.length][arr.length];
		
		for(int gap=1; gap < arr.length; gap++) {
			for(int i=0; i + gap < arr.length; i++) {
				
				int min = i;
				int max = i;
				
				for(int j = i+1; j <=i+ gap; j++) {
					if(arr[j] >= arr[max]) {
						max = j;
						
						if(arr[max] - arr[min] > matrix2[i][i+gap]) {
							matrix2[i][i+gap] = arr[max] - arr[min];
						}
					} else if(arr[j] < arr[max]) {
						min = j;
						max = j;
					}
				}
			}
		}
		
		for(int i=0; i < arr.length; i++) {
			matrix[i][1] = matrix2[0][i];
		}
		
		for(int noOftimes =2; noOftimes <= k; noOftimes++) {
			for(int i =0; i < arr.length; i++) {
				matrix[i][noOftimes] = matrix[i][noOftimes-1];
				
				for(int j = 0; j < i; j++) {
					int maxProfit = matrix[j][noOftimes-1] + matrix2[j+1][i];
					if(maxProfit > matrix[i][noOftimes]) {
						matrix[i][noOftimes] = maxProfit;
					}
				}
			}
		}
		
		return matrix[arr.length-1][k];
	}

	private static int max(int i, int j) {
		if(i >= j ) {
			return i;
		} else {
			return j;
		}
	}
	
	//todo
	private static int findMaximsedProfitAtmostKTimes2(int [] arr, int k) {
		return 0;
	}
}
