package strings;

public class NextPermutation {
	public static void main(String [] args) {
		NextPermutation testClass = new NextPermutation();
		char [] arr = { '1', '5', '5',  '8', '4', '4',  '3'};
		
		/*test cases
		 * { '1', '2', '3',  '4', '5',  '6', '7'};
		 * { '7', '6', '5',  '3', '2',  '1'};
		 * { '1', '5', '9',  '8', '4',  '3'}
		 * { '1', '5', '9',  '8', '0',  '0'}
		 * { '1', '5', '9',  '8', '7',  '6'}
		 * { '1', '5', '5',  '8', '4', '4',  '3'}
		 * */
		System.out.println(testClass.nextpermutation(arr));
	}
	
	/* Mistakes 
	 * 1. Array out of bound exception
	 * 2. -- instead of  ++
	 * 3. index check after accessing the accessing the index
	*/
	
	private char[] nextpermutation(char[] str) {
		if(str.length ==1 || str.length ==0) return str;
		
		int firstLowestFromRight = -1;
		for(int i=str.length-2; i >=0; i--) {
			if(str[i] < str[i+1]) {
				firstLowestFromRight =i;
				break;
			}
		}
		
		if(firstLowestFromRight == -1) {
			return reverse(str, 0, str.length -1);
		}
		
		int pointer = firstLowestFromRight;
		
		// remember && placement
		while(pointer+1 < str.length && str[pointer+1] > str[firstLowestFromRight]) {
			pointer++;
		}
		
		char temp = str[pointer];
		str[pointer] = str[firstLowestFromRight];
		str[firstLowestFromRight] = temp;
		
		
		return reverse(str, firstLowestFromRight+1 , str.length -1);
	}

	private char[] reverse(char[] str, int start, int end) {
		while(start < end) {
			char temp = str[start];
			str[start] = str[end];
			str[end] = temp;
			start ++;
			end--;
		}
		
		return str;
	}
}
