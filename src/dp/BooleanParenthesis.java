package dp;



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
		
		char [] symbols1 = {'T', 'F', 'F'} ;
		char [] operators1 = {'^', '|'};
		System.out.println(testClass.noofWaysToEvaluateTrue(symbols1, operators1));
	}

	private int noofWaysToEvaluateTrue(char [] symbols, char [] operators) {
		
		int [][] T = new int[symbols.length][symbols.length];
		int [][] F = new int[symbols.length][symbols.length];
		
		for(int i=0; i<symbols.length ; i++) {
			if(symbols[i] == 'T') {
				T[i][i] = 1;
			} else {
				F[i][i] = 1;
			}
		}
		
		for(int k=1; k < symbols.length; k++) {
			for(int i=0; i+k < symbols.length; i++) {
				if(k==1) {
					if(evaluate(operators[i], symbols[i], symbols[i+1]))  {
						T[i][i+1] = 1;
					} else {
						F[i][i+1] = 1;
					}
				} else {
					int maxTruth = 0;
					int maxFalse = 0;
					
					for(int j=i; j < i+k; j++) {
						if(operators[j] == '|') {
							int noofTimesTrue = T[i][j] * T[j+1][i+k] + T[i][j] * F[j+1][i+k] + F[i][j] * T[j+1][i+k];
							
							maxTruth += noofTimesTrue;
							maxFalse += F[i][j] * F[j+1][i+k];
						} else if(operators[j] == '&') {
							maxTruth += T[i][j] * T[j+1][i+k];
							
							int noOfTimesFalse = F[i][j] * (T[j+1][i+k] + F[j+1][i+k]) > F[j+1][i+k] *
									(T[i][j]) ? F[i][j] * (T[j+1][i+k] + F[j+1][i+k])
											: F[j+1][i+k] *
											(T[i][j]);
							
							maxFalse += noOfTimesFalse;
						} else if(operators[j] == '^') {
							int noofTimesTrue = T[i][j] * F[j+1][i+k] + F[i][j] * T[j+1][i+k];
							maxTruth += noofTimesTrue;
							
							int noofTimesFalse = T[i][j] * T[j+1][i+k] + F[i][j] * F[j+1][i+k];
							maxFalse += noofTimesFalse ;
						}
					}
					
					T[i][i+k] = maxTruth;
					F[i][i+k] = maxFalse;
				}
			}
		}
		
		return T[0][symbols.length-1];
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
