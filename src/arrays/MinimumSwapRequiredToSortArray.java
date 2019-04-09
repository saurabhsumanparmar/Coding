package arrays;

public class MinimumSwapRequiredToSortArray {
	public static void main(String [] args) {
		int [] arr = { 3, 4, 5, 1, 2};
		MinimumSwapRequiredToSortArray testClazz = new MinimumSwapRequiredToSortArray();
		System.out.println(testClazz.swapRequired(arr));
	}
	
	// assumption ..array will contain all the element between 1 to N
	private int swapRequired(int [] arr) {
		int [] visited = new int[arr.length];
		int noOfSwaps=0;
		
		for(int i=0; i < arr.length; i++) {
			if(visited[i] ==0 && arr[i] != i+1) {
				int j=i;
				while(arr[j] != j+1) {
					swap(arr[j]-1, j, arr);
					noOfSwaps++;
					visited[arr[j]-1] = 1;
				}
				
				visited[j] = 1; // not required though
			}
			
			visited[i] = 1; // not required though
		}
		
		return noOfSwaps;
	}

	private void swap(int i, int j, int [] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] =temp;
	}
}
