package dp;

public class StockBuyAndSellAtmostKTimes {
	public static void main(String [] args) {
		int [] t1 = {1, 2, 3, 4, 5, 6 , 7};
		int [] t2 = {5, 10, 2, 12, 13, 6, 13};
		
		// Test cases :
		System.out.println(maxProfitAtMostKTimes(t1, 1)); // expected 6
		System.out.println(maxProfitAtMostKTimes(t1, 2)); // expected 6
		System.out.println(maxProfitAtMostKTimes(t2, 3)); // 
		System.out.println(maxProfitAtMostKTimes(t2, 2));
		System.out.println(maxProfitAtMostKTimes(t2, 5));
		
		int [] t3 = {10, 22, 5, 75, 65, 80};
		int [] t4 = {12, 14, 17, 10, 14, 13, 12, 15};
		int [] t5 = {100, 30, 15, 10, 8, 25, 80};
		
		System.out.println(maxProfitAtMostKTimes(t3, 2)); // expected ..87
		System.out.println(maxProfitAtMostKTimes(t4, 3)); // expected ..12
		System.out.println(maxProfitAtMostKTimes(t5, 3)); // expected ..72
		
	}
	
	// Mistake  ...
	// In line no 103 ..k-l, j intstead of i-1, l-1
	// line no 48 missing
	private static int maxProfitAtMostKTimes(int [] arr, int k) {
		int [][] matrix = new int[k+1][arr.length];
				
		for(int i=1; i <=k ; i++) {
			for(int j=1; j< arr.length; j++) {
				if(j ==1) {
					if(i==1 && arr[1] > arr[0]) {
						matrix[i][j] = arr[1] - arr[0];
					} else {
						matrix[i][j] = matrix[i-1][j];
					}
					
					continue;
				}
				
				int maxProfit = matrix[i][j-1];
				
				for(int l=0; l < j; l++) {
					int profit = maximizeProfit(l, j, arr);
					
					maxProfit = maxProfit > profit ? maxProfit : profit;
					
					if(l-1 >=0 && matrix[i-1][l-1] + profit > maxProfit) {
						maxProfit = matrix[i-1][l-1] + profit;
					}
				}
				
				matrix[i][j] = maxProfit;
			}
		}
		
		return matrix[k][arr.length-1];
	}
	
	private static int maximizeProfit(int i, int j, int [] arr) {
		if(i==j) return 0;
		
		int maxprofit = 0;
		
		int min= arr[i];
		int max = arr[i];
		
		while(i<=j) {
			if(arr[j] >= max) {
				max = arr[j];
				maxprofit = maxprofit > max-min ? maxprofit : max-min;
			} else if(arr[j] < min) {
				min = arr[i];
				max = arr[i];
			}
			
			i++;
		}
		
		return maxprofit;
	}
}
