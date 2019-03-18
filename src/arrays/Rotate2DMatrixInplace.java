package arrays;

public class Rotate2DMatrixInplace {
	public static void main(String [] args) {
		int [] [] arr = {{1, 2,3},
				{4, 5, 6},
				{7,8,9}};
		
		rotate(arr);
		
		for(int i=0; i< arr.length; i++) {
			for(int j=0; j< arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
		}
	}
	
	public static void rotate(int [][] arr) {
		int n = arr.length;
		
		for(int i=0, j=0, count=n; i < n/2; i++, j++, count =count-2) {
			
			int offset = 0;
			while(offset < count && count> 0)  {
				
				int topLefti = i;
				int topLeftj = j;	
				
				int topRighti = topLefti;
				int topRightj = topLeftj + count-1;
				
				int bottomRighti = topRighti + count-1;
				int bottomRightj = topRightj;
				
				int bottomLefti = bottomRighti;
				int bottomLeftj = bottomRightj -(count-1);
						
				
				//Right
				int temp = arr[topRighti +offset][topRightj];
				arr[topRighti+offset][topRightj] = arr[topRighti +offset][topLeftj];

				//Down
				int temp2 = arr[bottomRighti] [bottomRightj - offset];
				arr[bottomRightj - offset] [bottomRightj] = temp;
				
				//Left
				temp = arr[bottomLefti - offset] [bottomLeftj];
				arr[bottomLefti - offset] [bottomLeftj] = temp2;
				
				//Up
				arr[topRighti +offset][topLeftj] = temp;
				
				offset++;
			}
		}
		
	}
}
