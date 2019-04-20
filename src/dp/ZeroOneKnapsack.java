package dp;

// https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
public class ZeroOneKnapsack {
	public static void main(String [] args) {
		int val[] = {60, 100, 120};
		int wt[] = {10, 20, 30}; 
		int  W = 50; 
		System.out.println(maxValue(wt, val, W));
	}
	
	// mistake ...Forget line no 33 ..gajab  ..koi nahi
	static int maxValue(int [] weight, int [] val, int maxWeight) {
		
		int [][] matrix = new int[val.length][maxWeight+1];
		
		for(int i=0; i < val.length; i++) {
			if(weight[i] < maxWeight) {
				matrix[i][weight[i]] = val[i];
			}
		}
		
		for(int i=1; i < val.length; i++) {
			for(int j=1; j <= maxWeight; j++) {
				int maxCollected=0;
				
				maxCollected = matrix[i-1][j];
				
				if(j- weight[i] >= 0 && (val[i] + matrix[i-1][j- weight[i]]) > maxCollected) {
					maxCollected = val[i] + matrix[i-1][j- weight[i]];
				}
				
				matrix[i][j] = maxCollected;
			}
		}
		
		return matrix[val.length-1][maxWeight];
	}
}
