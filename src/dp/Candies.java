package dp;

//https://www.hackerrank.com/challenges/candies/problem

// Need to run this cleanly ..10/17 test cases failed :( from hacker rank
public class Candies {
	
	public static void main(String [] args) {
		int [] arr1 =  {4, 6, 4, 5, 6, 2};
		//int [] arr2 = {1, 2, 2};  ..handle for the duplicate cases
		int [] arr3 = {2, 4, 2, 6, 1, 7, 8,9, 2,1};
		int [] arr4 = {2 , 4,3,5,2, 6, 4, 5};
		
		System.out.println(minimumCandies(arr1));
		System.out.println(minimumCandies(arr3));
		System.out.println(minimumCandies(arr4));
	}
	
	public static int minimumCandies(int [] rating) {
		if(rating.length ==0) {
			return 0;
		} else if(rating.length ==1) {
			return rating[0];
		}
		
		int [] smaller = new int[rating.length];
		smaller[rating.length-1] = 0;
		
		for(int i=rating.length-2; i>=0; i--) {
			if(rating[i] > rating[i+1]) {
				smaller[i] = smaller[i+1] +1;
			} else {
				smaller[i] = 0;
			}
		}
		
		int totalCandies = 0;
		int candiesTolastChild = smaller[0] > 0 ? smaller[0]+1 : 1;
		totalCandies = totalCandies+ candiesTolastChild;
		
		for(int i=1; i < rating.length ; i++) {
			if(rating[i] > rating[i-1]) {
				if(smaller[i] ==0) {
					candiesTolastChild = candiesTolastChild+1;
				} else if(smaller[i] <= candiesTolastChild) {
					candiesTolastChild = candiesTolastChild+1;
				} else if(smaller[i] > candiesTolastChild) {
					candiesTolastChild = smaller[i]+1;
				}
			} else {
				if(candiesTolastChild == smaller[i-1] +1) {
					candiesTolastChild = candiesTolastChild-1;
				} else {
					candiesTolastChild = smaller[i] + 1;
				}
			}
			
			totalCandies = totalCandies + candiesTolastChild;
		}
		
		return totalCandies;
	}
}
