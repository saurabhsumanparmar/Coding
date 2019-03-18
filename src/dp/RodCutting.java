package dp;

public class RodCutting {
	public static void main(String [] args) {
		int [] arr1 = {0, 1, 5, 8, 9, 10, 17, 17, 20};
		int [] arr2 = {0, 3, 5, 8, 9, 10, 17, 17, 20};
		
		System.out.println(maximizeValue(arr1));
		System.out.println(maximizeValue(arr2));
	}
	
	public static int maximizeValue(int [] cutValues) {
		if(cutValues.length ==0) return 0;
		if(cutValues.length ==2) return cutValues[1];
		
		int [] maxValue = new int[cutValues.length];
		maxValue[0] = 0;
		maxValue[1] = cutValues[1];
		
		for(int i=2; i< maxValue.length; i++) {
			maxValue[i] = cutValues[i];
			for(int j=0; j<= i/2 ; j++) {
				int val = maxValue[j] + maxValue[i-j];
				
				if(val> maxValue[i]) {
					maxValue[i] = val;
				}
			}
		}
		
		return maxValue[maxValue.length-1];
	}
}
