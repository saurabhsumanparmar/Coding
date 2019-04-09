package dp;

public class MinSumPartition {
	
	public static void main(String [] args) {
		MinSumPartition testClazz = new MinSumPartition();
		//int [] arr = { 1, 6, 5, 11};
		int [] arr = { 36, 7, 46, 40};
		System.out.println(testClazz.minSumPartition(arr));
	}
	
	
	/*mistakes : 
	 * 1. Logical in passing a proper value ..arr[i] + arr[j]
	 * 2. Exact substraction  : int diff1 = sum - i;
		diff1 = i - diff1 > 0 ? i - diff1  : diff1 - i;
	 * 3. Comparision between 3 variable and missing equal case
	 * */
	
	private int minSumPartition(int [] arr) {
		if(arr.length ==0 || arr.length ==1)
			return arr[0];
		
		if(arr.length ==2) 
			return arr[0] - arr[1] >0 ? arr[0] - arr[1] : arr[1] - arr[0];
			
		int sum=0;
		int [][] matrix = new int[arr.length][arr.length];
		for(int i=0;i < arr.length; i++) {
			sum =sum + arr[i];
			matrix[i][i] = arr[i];
		}
		
		for(int gap=1; gap < arr.length; gap++) {
			for(int i=0; i+ gap < arr.length ; i++) {
				int j = i+gap;
				
				if(gap==1) {
					matrix[i][j] = minPartitionDiff(matrix[i+1][j], matrix[i][j-1], arr[i] + arr[j], sum);
				} else {
					matrix[i][j] = minPartitionDiff(matrix[i+1][j], matrix[i][j-1], 
							arr[i] + arr[j] + matrix[i+1][j-1], sum);
				}
			}
		}
		
		int finalDiff = sum - matrix[0][arr.length-1];
			
		return finalDiff - matrix[0][arr.length-1] > 0 ?
				finalDiff - matrix[0][arr.length-1] : matrix[0][arr.length-1] - finalDiff;
	}

	private int minPartitionDiff(int i, int j, int k, int sum) {
		int diff1 = sum - i;
		diff1 = i - diff1 > 0 ? i - diff1  : diff1 - i;
		int diff2 = sum - j;
		diff2 = j - diff2 > 0 ? j - diff2  : diff2 - j;
		int diff3 = sum - k;
		diff3 = k - diff3 > 0 ? k - diff3  : diff3 - k;
		
		if(diff1 <= diff2 && diff1 < diff3) {
			return i;
		} else if (diff1 >= diff2 && diff2 < diff3){
			return j;
		} else {
			return k;
		}
	}
}
