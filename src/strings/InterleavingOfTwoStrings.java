package strings;

public class InterleavingOfTwoStrings {
	public static void main(String [] args) {
		printInterleaving("AB", "CD", 0, 1, 0, 1, new char[4], 0);
		printInterleaving("ABC", "DE", 0, 2, 0, 1, new char[5], 0);
	}
	
	public static void printInterleaving(String str1, String str2, int start1, int end1,
			int start2, int end2, char [] arr, int index) {
		
		if(start1<= end1) {
			arr[index] = str1.charAt(start1);
			printInterleaving(str1, str2, start1+1, end1, start2, end2, arr, index+1);
		}
		
		if(start2 <= end2) {
			arr[index] = str2.charAt(start2);
			printInterleaving(str1, str2, start1, end1, start2+1, end2, arr, index+1);
		}
		
		if(index == str1.length() + str2.length() -1) {
			print(arr);
			return;
		}
	}
	
	public static void print(char[] arr) {
		for(int i=0; i< arr.length; i++) {
			System.out.print(arr[i]);
		}
		
		System.out.println();
	}
}
