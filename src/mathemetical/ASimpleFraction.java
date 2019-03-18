package mathemetical;

import java.util.ArrayList;

public class ASimpleFraction {
	public static void main(String [] args) {
		System.out.println(encloseDecimalInsideBraces(10, 2));
		System.out.println(encloseDecimalInsideBraces(3, 5));
		System.out.println(encloseDecimalInsideBraces(8, 3));
		System.out.println(encloseDecimalInsideBraces(10, 9));
	}
	
	public static String encloseDecimalInsideBraces(int numerator, int denominator) {	
			int noOfDision = numerator/denominator;
			StringBuffer bf = new StringBuffer(String.valueOf(noOfDision));
			
			int remainder = numerator % denominator;
			
			if(remainder ==0) {
				return bf.toString();
			} else {
				bf.append('.');
			}
			
			ArrayList<Integer> valuesAferDecimal = new ArrayList<Integer>();
			boolean isPatternrepeatingAfterDecimal = false;
			
			while(remainder !=0 && !isPatternrepeatingAfterDecimal) {
				while(remainder < denominator) {
					remainder = remainder *10;
				}
				
				valuesAferDecimal.add(remainder/denominator);
				remainder = remainder % denominator;
				
				isPatternrepeatingAfterDecimal = isRepeatingPatternFound(valuesAferDecimal);
			}
			
			if(valuesAferDecimal.size() > 0) {
				if(isPatternrepeatingAfterDecimal)
					bf.append('(');
				
				int i=0; int max = isPatternrepeatingAfterDecimal ? valuesAferDecimal.size()/2-1 : valuesAferDecimal.size()-1;
				while(i<=max) {
					bf.append(String.valueOf(valuesAferDecimal.get(i)));
					i++;
				}
				
				if(isPatternrepeatingAfterDecimal)
					bf.append(')');
				
			}
			
			return bf.toString();
	}


	private static boolean isRepeatingPatternFound(ArrayList<Integer> valuesAferDecimal) {
		if(valuesAferDecimal.size() == 0 || valuesAferDecimal.size() %2 !=0) return false;
		
		for(int i=0, j = valuesAferDecimal.size()/2; i<valuesAferDecimal.size()/2; i++, j++) {
			if(valuesAferDecimal.get(i) != valuesAferDecimal.get(j)) {
				return false;
			}
		}
		
		return true;
	}
	
	/*public String encloseDecimalInsideBracesOld(int numerator, int denominator) {
		Map<Integer, Integer> primeFactorsOfNum = primeFactors(numerator);
		Map<Integer, Integer> primeFactorsOfDenom = primeFactors(denominator);
		
		boolean doesNumeratorContainsPrime = false;
		
		for(Map.Entry<Integer, Integer> entry: primeFactorsOfDenom.entrySet()) {
			if(isPrime(entry.getKey()) && (primeFactorsOfNum.get(entry.getKey()) == null ||
					primeFactorsOfNum.get(entry.getKey()) < entry.getValue())) {
				doesNumeratorContainsPrime = true;
			}
		}
		
		if(!doesNumeratorContainsPrime) {
			int noOfDision = numerator/denominator;
			int remainder = numerator % denominator;
			
			ArrayList<Integer> valuesAferDecimal = new ArrayList<Integer>();
			
			while(!isRepeatingPatternFound(valuesAferDecimal)) {
				while(remainder < denominator) {
					remainder = remainder *10;
				}
				
				int noOfTimes = remainder/denominator;
				valuesAferDecimal.add(noOfTimes);
			}
			
			StringBuffer bf = new StringBuffer(String.valueOf(noOfDision));
			bf.append('(');
			
			int i=0;
			while(i<valuesAferDecimal.size()/2) {
				bf.append(String.valueOf(valuesAferDecimal.get(i)));
			}
			
			bf.append(')');
			
			return bf.toString();
		} else {
			return String.valueOf(numerator/denominator);
		}
		
	}
	

	private boolean isPrime(Integer key) {
		for(int j =2; j<= key/2; j++) {
			if(key %j == 0) {
				return false;
			}
		}
		return true;
	}

	private Map<Integer, Integer> primeFactors(int numerator) {
		int i=2;
		
		Map<Integer, Integer> factors = new HashMap<Integer, Integer>();
		
		while(i < numerator/2) {
			int powerOfi = 0;
			while(numerator % i ==0) {
				numerator = numerator/i;
				powerOfi++;
			}
			
			if(powerOfi > 0) {
				factors.put(i, powerOfi);
			}
			
			i = nextPrimeNo(i+1);
		}
		
		return factors;
	}

	private int nextPrimeNo(int i) {
		for(int j =2; j<= i/2; j++) {
			if(i %j == 0) {
				return nextPrimeNo(i+1);
			}
		}
		
		return i;
	}*/
}
