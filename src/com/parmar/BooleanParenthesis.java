package com.parmar;



/*Given a boolean expression with following symbols.

Symbols
  'T' ---> true
  'F' ---> false

And following operators filled between symbols

Operators
  &   ---> boolean AND
  |   ---> boolean OR
  ^   ---> boolean XOR

Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.

For Example:
The expression is "T | T & F ^ T", it evaluates true
in 4 ways ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T)
and (T|((T&F)^T)).

Return No_of_ways Mod 1003.

Input:
First line contains the test cases T.  1<=T<=500
Each test case have two lines
First is length of string N.  1<=N<=100
Second line is string S (boolean expression).
Output:
No of ways Mod 1003.


Example:
Input:
2
7
T|T&F^T
5
T^F|F

Output:
4
2

** For More Input/Output Examples Use 'Expected Output' option **
*/


// run the case ..returning wrong output
public class BooleanParenthesis {
	public static void main(String [] args) {
		BooleanParenthesis testClass = new BooleanParenthesis();
		char [] symbols = {'T', 'T', 'F', 'T'} ;
		char [] operators = {'|', '&', '^'};
		System.out.println(testClass.noofWaysToEvaluateTrue(symbols, operators));
	}

	private int noofWaysToEvaluateTrue(char [] symbols, char [] operators) {
		
		if(symbols.length == 2) {
			if(evaluate(operators[0], symbols[0], symbols[1])) {
				return 1;
			} else {
				return 0;
			}
		} else if(symbols.length == 1) {
			if(symbols[0] == 'T') {
				return 1;
			} else {
				return 0;
			}
		}
		
		int [][] matrix = new int[symbols.length][symbols.length];
		
		for(int k=1; k< symbols.length; k++) {
			for(int i=0; i + k<symbols.length; i++) {
				int j =i+k; 
				
				if(k == 1) {
					boolean result = evaluate(operators[i], symbols[i], symbols[j]);
					if(result) {
						matrix[i][j] =1; 
					} else {
						matrix[i][j] =0; 
					}
				} else {					
					if(matrix[i+1][j] >0 && evaluate(operators[i], symbols[i], 'T')) {
						matrix[i][j] = matrix[i+1][j];
					} else if(matrix[i+1][j] ==0 && evaluate(operators[i], symbols[i], 'F')) {
						matrix[i][j] = 1;
					}
					
					if(matrix[i][j-1] >0 && evaluate(operators[j-1], 'T', symbols[j])) {
						matrix[i][j] = matrix[i][j] + matrix[i][j-1];
					} else if(matrix[i][j-1] ==0 && evaluate(operators[j-1], 'F', symbols[j])) {
						matrix[i][j] = matrix[i][j] + 1;
					}
				}
			}
		}
		
		return matrix[0][symbols.length-1];
	}
	
	private boolean evaluate(char operator, char left, char right) {
		if(operator == '&') {
			if(left == 'T' && right == 'T') {
				return true;
			} else {
				return false;
			}
		} else if(operator == '|') {
			if(left == 'T' || right == 'T') {
				return true;
			} else {
				return false;
			}
		} else if(operator == '^') {
			if((left == 'T' && right == 'T') || (left == 'F' && right == 'F')) {
				return false;
			} else {
				return true;
			}
		}
		
		return false;
	}
}
